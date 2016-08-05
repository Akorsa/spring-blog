package ru.akorsa.springdata.jpa.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Component;
import ru.akorsa.springdata.jpa.model.ContactEntity;
import ru.akorsa.springdata.jpa.model.ContactTelDetailEntity;
import ru.akorsa.springdata.jpa.service.ContactService;

import java.util.List;

@Component
public class SpringUI {

    @Autowired
    SpringProperties springProperties;

    @Autowired
    ContactService contactService;

    public void init() {
        propertiesDemo();
        entityDemo();
    }

    public void propertiesDemo() {
        SpringUtils.printProperty(
                "springProperties.getToken()",
                springProperties.getToken());
    }

    // endregion

    // region Add Contact

    public void AddContact() {
        ContactEntity contact = new ContactEntity();
        contact.setFirstName("Michael");
        contact.setLastName("Jackson");
        java.util.Date utilDate = new java.util.Date();
        contact.setBirthDate(new java.sql.Date(utilDate.getTime()));
        ContactTelDetailEntity contactTelDetail =
                new ContactTelDetailEntity("Home", "1111111111");
        contact.addContactTelDetailEntity(contactTelDetail);
        contactTelDetail = new ContactTelDetailEntity("Mobile", "2222222222");
        contact.addContactTelDetailEntity(contactTelDetail);
//        contactService.save(contact);
    }

    // endregion

    // region Spring Data JPA Demos


    public void entityDemo() {
        SpringUtils.listContactEntities("ENTITIES FIND ALL",
                contactService.findAll());
        SpringUtils.listContactEntities("ENTITIES FIND BY FIRST NAME",
                contactService.findByFirstName("Barry"));
        SpringUtils.listContactEntities("ENTITIES FIND BY FIRST AND LAST NAME",
                contactService.findByFirstNameAndLastName("Tad", "Grant"));

        List<ContactEntity> contacts = contactService.getContactsWithDetail();
        SpringUtils.listContactEntitiesWithDetail(contacts);

    }


    public void showContact(Long ID) {
        ContactEntity contact = contactService.findById(ID);
        System.out.println(contact.getFirstName());
    }
    // endregion


}
