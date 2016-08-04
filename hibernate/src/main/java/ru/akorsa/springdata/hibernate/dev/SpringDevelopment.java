package ru.akorsa.springdata.hibernate.dev;

import ru.akorsa.springdata.hibernate.config.SpringProperties;
import ru.akorsa.springdata.hibernate.config.SpringUtils;
import ru.akorsa.springdata.hibernate.service.ContactService;
import ru.akorsa.springdata.hibernate.model.Contact;
import ru.akorsa.springdata.hibernate.model.ContactTelDetail;

import java.util.Date;
import java.util.List;

public class SpringDevelopment {

    SpringProperties springProperties;
    ContactService contactService;

    public SpringDevelopment(SpringProperties springProperties,  ContactService contactService) {
        this.springProperties = springProperties;
        this.contactService = contactService;
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
        contactService.updateContact(contact);
    }

    public void hibernateDemo() {
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
