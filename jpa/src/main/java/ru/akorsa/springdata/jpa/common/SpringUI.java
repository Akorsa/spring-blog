package ru.akorsa.springdata.jpa.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.akorsa.springdata.jpa.service.ContactService;

@Component
public class SpringUI {

    @Autowired
    ApplicationSettings applicationSettings;

    @Autowired
    ContactService contactService;

    public void init() {
        //propertiesDemo();
        entityDemo();
    }

    public void propertiesDemo() {
        SpringUtils.printProperty(
                "ApplicationSettings.getIsDemoSite()",
                applicationSettings.getIsDemoSite().toString());
    }

    // endregion

    // region Add Contact

    public void AddContact() {
       /* ContactEntity contact = new ContactEntity();
        contact.setFirstName("Michael");
        contact.setLastName("Jackson");
        java.util.Date utilDate = new java.util.Date();
        contact.setBirthDate(new java.sql.Date(utilDate.getTime()));
              ContactPhone contactPhone =
                new ContactPhone("Home", "1111111111");
       contact.addContactPhone(contactPhone);
        contactPhone = new ContactPhone("Mobile", "2222222222");
       contact.addContactPhone(contactPhone);
       contactService.save(contact);*/
    }

    // endregion

    // region Spring Data JPA Demos


    public void entityDemo() {
        /*SpringUtils.listContacts("ENTITIES FIND ALL",
                contactService.findAll());
        SpringUtils.listContacts("ENTITIES FIND BY FIRST NAME",
                contactService.findByFirstName("Barry"));
        SpringUtils.listContacts("ENTITIES FIND BY FIRST AND LAST NAME",
                contactService.findByFirstNameAndLastName("Tad", "Grant"));

        SpringUtils.listContact("SINGLE CONTACT: ", contactService.getContactByEmail("Nam.nulla@pedenonummyut.edu"));
        SpringUtils.listContactsWithDetail(contactService.getContactsWithDetail());

        SpringUtils.listContactWithDetail(contactService.getContactByIdWithDetail(2L));

        SpringUtils.listContacts("FIND BY FIRST NAME", contactService.findByFirstName("Summer"));

        SpringUtils.listContactWithDetail(contactService.getContactByIdWithDetail(1L));*/

       /*SpringUtils.contactToContactDTO(contactService.getContactByIdWithDetail(2L));

       try {
           contactService.update(SpringUtils.contactToContactDTO(contactService.getContactByIdWithDetail(2L)));
       } catch (NotFoundException e) {
           e.printStackTrace();
       }*/
        /*try {
            contactService.update(SpringUtils.contactToContactDTO(contactService.getContactByIdWithDetail(2L)));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }*/
    }

}
