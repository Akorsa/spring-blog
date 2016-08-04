package ru.akorsa.springdata.jpa.dev;

import ru.akorsa.springdata.jpa.model.Contact;
import ru.akorsa.springdata.jpa.model.ContactEntity;
import ru.akorsa.springdata.jpa.model.ContactTelDetail;
import ru.akorsa.springdata.jpa.config.SpringProperties;
import ru.akorsa.springdata.jpa.config.SpringUtils;
import ru.akorsa.springdata.jpa.service.ContactEntityService;
import ru.akorsa.springdata.jpa.service.ContactJpaService;

import java.util.Date;
import java.util.List;

public class SpringDevelopment {

    SpringProperties springProperties;
    ContactJpaService contactJpaService;
    ContactEntityService contactEntityService;

    public SpringDevelopment(SpringProperties springProperties, ContactJpaService contactJpaService, ContactEntityService contactEntityService) {
        this.springProperties = springProperties;
        this.contactJpaService = contactJpaService;
        this.contactEntityService = contactEntityService;
    }

    public void propertiesDemo() {
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

    public void jpaDemo() {
        SpringUtils.listContacts("JPA FIND ALL",
                contactJpaService.findAll());
        SpringUtils.listContacts("JPA FIND BY FIRST NAME",
                contactJpaService.findByFirstName("Barry"));
        SpringUtils.listContacts("JPA FIND BY FIRST AND LAST NAME",
                contactJpaService.findByFirstNameAndLastName("Tad", "Grant"));

        List<Contact> contacts = contactJpaService.getContactsWithDetail();
        SpringUtils.listContactsWithDetail(contacts);
    }

    public void entityDemo() {
        SpringUtils.listContactEntities("ENTITIES FIND ALL",
                contactEntityService.findAll());
        SpringUtils.listContactEntities("ENTITIES FIND BY FIRST NAME",
                contactEntityService.findByFirstName("Barry"));
        SpringUtils.listContactEntities("ENTITIES FIND BY FIRST AND LAST NAME",
                contactEntityService.findByFirstNameAndLastName("Tad", "Grant"));

        List<ContactEntity> contacts = contactEntityService.getContactsWithDetail();
        SpringUtils.listContactEntitiesWithDetail(contacts);

    }
}
