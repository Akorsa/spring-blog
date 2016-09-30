package ru.akorsa.springdata.jpa.service;

import ru.akorsa.springdata.jpa.config.ApplicationConfig;
import ru.akorsa.springdata.jpa.dto.ContactDTO;
import ru.akorsa.springdata.jpa.dto.ContactPhoneDTO;
import ru.akorsa.springdata.jpa.dto.HobbyDTO;
import ru.akorsa.springdata.jpa.enums.DataConfigProfile;
import ru.akorsa.springdata.jpa.exceptions.ContactNotFoundException;
import ru.akorsa.springdata.jpa.model.Contact;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.akorsa.springdata.jpa.model.ContactPhone;
import ru.akorsa.springdata.jpa.model.ContactTestUtils;
import ru.akorsa.springdata.jpa.model.Hobby;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(classes = ApplicationConfig.class)
@Transactional
@ActiveProfiles(DataConfigProfile.H2)
public class ContactServiceTest {

    final String FIRST_NAME_CONTACT_ID_4L = "ROBIN";

    @Autowired
    private ContactService contactService;

    @Test
    public void findByFirstName() throws ContactNotFoundException {
        Contact contact = contactService.findContactById(1L);
        assertEquals(contact.getFirstName(), "Summer");
    }

    @Test
    public void findByContactIdWithDetail() throws ContactNotFoundException {
        Contact contact = contactService.getContactByIdWithDetail(1L);
        assertTrue(contact.getContactPhones().size() == 2);
    }

    @Test
    public void addContact() {

        // Retrieve all contacts to compare size before and after record addition
        List<Contact> contacts = contactService.findAll();
        int originalContactCount = contacts.size();

        ContactDTO contactDTO = ContactTestUtils.newContactDTO();
        Contact contact = contactService.add(contactDTO);
        assertThat(contact.getContactId(), is(11L));

        // We entered a ContactDTO with 2 Phone Records - Database Count
        List<ContactPhone> contactPhones =
                contactService.findContactPhonesByContactId(contact.getContactId());
        int phoneCount = contactPhones.size();
        assertEquals(phoneCount, 2);

        int hobbyCount = contact.getHobbies().size();
        assertEquals(hobbyCount, 3);

        // Confirm Contact contains the new phone records
        try {
            contact = contactService.findContactById(contact.getContactId());
        } catch (ContactNotFoundException e) {
            e.printStackTrace();
        }
        phoneCount = contact.getContactPhones().size();
        assertEquals(phoneCount, 2);

        // Confirm new Contact is retrieved when viewing all contacts
        contacts = contactService.findAll();
        int finalContactCount = contacts.size();
        assertThat(finalContactCount, is(greaterThan(originalContactCount)));
    }

    @Test
    public void deleteContact() throws ContactNotFoundException {
        List<Contact> contacts = contactService.findAll();
        int originalContactCount = contacts.size();

        Contact contact = contactService.deleteById(3L);
        contacts = contactService.findAll();

        int finalContactCount = contacts.size();
        assertThat(finalContactCount, is(lessThan(originalContactCount)));

        // Confirm no contact phones in database for deleted contact
        List<ContactPhone> contactPhones = contactService.findContactPhonesByContactId(3L);
        int phoneCount = contactPhones.size();
        assertEquals(phoneCount, 0);

    }

    @Test
    public void updateContact() throws ContactNotFoundException {

        // Contact with ID=4 in H2Database Robin Sullivan, 2 Phones
        Contact contact = contactService.findContactById(4L);
        ContactDTO contactDTO = ContactTestUtils.contactToContactDTO(contact);
        contactDTO = ContactTestUtils.addHobbyToContactDTO(contactDTO);
        assertEquals(contactDTO.getFirstName().toUpperCase(), FIRST_NAME_CONTACT_ID_4L);
        assertEquals(contactDTO.getHobbies().size(), 3);

        // Contact's first phone: Mobile "1-234-628-6511" -> "1-234-628-9999"
        assertThat(getFirstContactPhone(contactDTO), endsWith("6511"));

        // Contact's last name: Sullivan -> Sullivananny
        contactDTO.setLastName("Sullivananny");

        // Update Contact's first phone number
        contactDTO
                .getContactPhones()
                .stream()
                .filter(t -> t.getPhoneType().toUpperCase().equals("MOBILE"))
                .findFirst()
                .get()
                .setPhoneNumber("1-234-628-9999");

        contactService.update(contactDTO);

        // reload Contact from Repository to confirm records are updated
        contact = contactService.findContactById(4L);
        contactDTO = ContactTestUtils.contactToContactDTO(contact);
        assertThat(contactDTO.getLastName(), endsWith("nanny"));
        assertThat(getFirstContactPhone(contactDTO), endsWith("9999"));
    }

    private String getFirstContactPhone(ContactDTO contactDTO) {
        return contactDTO
                .getContactPhones()
                .stream()
                .filter(t -> t.getPhoneType().toUpperCase().equals("MOBILE"))
                .findFirst()
                .get()
                .getPhoneNumber();
    }

    @Test
    public void addHobby()
            throws ContactNotFoundException {

        List<Hobby> hobbies = contactService.findAllHobbies();
        int originalHobbyCount = hobbies.size();

        Contact contact = contactService.findContactById(5L);
        int originalContactHobbyCount = contact.getHobbies().size();

        HobbyDTO hobbyDTO = ContactTestUtils.newHobbyDTO();
        Hobby hobby = contactService.addHobby(hobbyDTO);

        contact.getHobbies().add(hobby);
        int finalContactHobbyCount = contact.getHobbies().size();
        assertThat(finalContactHobbyCount, is(greaterThan(originalContactHobbyCount)));

        hobbies = contactService.findAllHobbies();
        int finalHobbyCount = hobbies.size();
        assertThat(finalHobbyCount, is(greaterThan(originalHobbyCount)));

        // Confirm findByHobbyTitle not null

        hobby = contactService.findByHobbyTitle(ContactTestUtils.HOBBY_TITLE.toUpperCase());
        assertEquals(hobby.getHobbyTitle(), ContactTestUtils.HOBBY_TITLE);
    }

    @Test
    public void addHobbyToContact() throws
            ContactNotFoundException {
        Contact contact = contactService.findContactById(5L);
        ContactDTO contactDTO = ContactTestUtils.contactToContactDTO(contact);
        assertEquals(contactDTO.getHobbies().size(), 2);

        contactDTO.getHobbies().add(ContactTestUtils.JOUSTING_HOBBY_DTO);
        contact = contactService.update(contactDTO);
        assertEquals(contact.getHobbies().size(), 3);

    }

    @Test
    public void removeHobbyFromContact() throws
            ContactNotFoundException {

        Contact contact = contactService.findContactById(1L);
        ContactDTO contactDTO = ContactTestUtils.contactToContactDTO(contact);
        assertEquals(contactDTO.getHobbies().size(), 2);

        Long hobbyId = contactDTO.getHobbies().iterator().next().getHobbyId();
        contact = contactService.removeHobby(contactDTO, hobbyId);
        assertEquals(contact.getHobbies().size(), 1);

    }

    @Test
    public void addContactPhoneToContactByUpdate() throws ContactNotFoundException {
        Contact contact = contactService.findContactById(5L);
        ContactDTO contactDTO = ContactTestUtils.contactToContactDTO(contact);
        assertEquals(contactDTO.getContactPhones().size(), 2);

        contactDTO.getContactPhones().add(ContactTestUtils.HOME_CONTACT_PHONE_DTO);
        contact = contactService.update(contactDTO);
        assertEquals(contact.getContactPhones().size(), 3);
    }

    @Test
    public void addContactPhoneToContact() throws
            ContactNotFoundException {
        Contact contact = contactService.findContactById(5L);
        assertEquals(contact.getContactPhones().size(), 2);

        ContactPhoneDTO contactPhoneDTO = ContactTestUtils.HOME_CONTACT_PHONE_DTO;
        contactPhoneDTO.setContactId(5L);
        ContactPhone contactPhone = contactService.addContactPhone(contactPhoneDTO);
        contact.addContactPhone(contactPhone);
        assertEquals(contact.getContactPhones().size(), 3);

        Contact contactRevisited = contactService.findContactById(5L);
        assertEquals(contactRevisited.getContactPhones().size(), 3);
    }
}
