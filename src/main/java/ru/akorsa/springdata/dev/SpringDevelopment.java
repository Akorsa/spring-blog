package ru.akorsa.springdata.dev;

import ru.akorsa.springdata.model.Contact;
import ru.akorsa.springdata.model.ContactTelDetail;
import ru.akorsa.springdata.hbn.service.ContactService;
import ru.akorsa.springdata.config.SpringProperties;
import ru.akorsa.springdata.config.SpringUtils;
import ru.akorsa.springdata.jpa.service.ContactJpaService;

import java.util.Date;
import java.util.List;

public class SpringDevelopment {

    public void propertiesDemo(SpringProperties springProperties) {
        SpringUtils.printProperty(
                "springProperties.getToken()",
                springProperties.getToken());
    }

    public void addContact() {
        Contact contact = new Contact();
        contact.setFirstName("Michael");
        contact.setLastName("Jackson");
        contact.setBirthDate(new Date());
        ContactTelDetail contactTelDetail =
                new ContactTelDetail("Home", "1111111111");
        contact.addContactTelDetail(contactTelDetail);
        contactTelDetail = new ContactTelDetail("Mobile", "222222");
        contact.addContactTelDetail(contactTelDetail);
    }

    public void jpaDemo(ContactJpaService contactJpaService) {
        SpringUtils.listContacts("JPA FIND ALL",
                contactJpaService.findAll());
        SpringUtils.listContacts("JPA FIND BY FIRST NAME",
                contactJpaService.findByFirstName("Barry"));
        SpringUtils.listContacts("JPA FIND BY FIRST AND LAST NAME",
                contactJpaService.findByFirstNameAndLastName("Tad", "Grant"));
    }

    public void hibernateDemo(ContactService contactService) {
        Contact contact = contactService.getContact(1L);
        SpringUtils.listContact("HIBERNATE CONTACT(1L)", contact);

        List<Contact> contacts = contactService.getContacts();
        SpringUtils.listContacts("HIBERNATE CONTACTS", contacts);

        contacts = contactService.getContactsWithDetail();
        listHbnContactsWithDetail(contacts);
    }

    private void listHbnContactsWithDetail(List<Contact> contacts) {
        System.out.println("LISTING CONTACTS WITH DETAILS ---------------------------------");
        System.out.println();
        for (Contact contact : contacts) {
            System.out.println(contact);
            if (contact.getContactTelDetails() != null) {
                contact.getContactTelDetails().forEach(System.out::println);
            }
            if (contact.getHobbies() != null) {
                contact.getHobbies().forEach(System.out::println);
            }
            System.out.println();
        }
    }
}
