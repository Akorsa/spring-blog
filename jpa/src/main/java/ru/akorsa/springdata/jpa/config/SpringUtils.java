package ru.akorsa.springdata.jpa.config;

import ru.akorsa.springdata.jpa.model.Contact;
import ru.akorsa.springdata.jpa.model.ContactEntity;

import java.util.List;

public class SpringUtils {

    public static void printProperty(String header, String property) {
        System.out.println("\r\n" + header + " ------------------------------ ");
        System.out.println(property);
    }

    public static void listContacts(String header, List<Contact> contacts) {

        System.out.println("\r\n" + header + " ------------------------------ ");
        System.out.println();
        contacts.forEach(System.out::println);
        System.out.println();
    }

    public static void listContact(String header, Contact contact) {
        System.out.println("\r\n" + header + " ------------------------------ ");
        System.out.println();
        System.out.println(contact);
        System.out.println();
    }

    public static void listContactEntities(String header, List<ContactEntity> contacts) {

        System.out.println("\r\n" + header + " ------------------------------ ");
        System.out.println();
        contacts.forEach(System.out::println);
        System.out.println();
    }

    public static void listContactEntity(String header, ContactEntity contact) {
        System.out.println("\r\n" + header + " ------------------------------ ");
        System.out.println();
        System.out.println(contact);
        System.out.println();
    }

    public static void listContactsWithDetail(List<Contact> contacts) {
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

    public static void listContactEntitiesWithDetail(List<ContactEntity> contacts) {
        System.out.println("LISTING ENTITIES WITH DETAILS ---------------------------------");
        System.out.println();
        for (ContactEntity contact : contacts) {
            System.out.println(contact);
            if (contact.getContactTelDetailEntities() != null) {
                contact.getContactTelDetailEntities().forEach(System.out::println);
            }
            if (contact.getHobbyEntities() != null) {
                contact.getHobbyEntities().forEach(System.out::println);
            }
            System.out.println();
        }
    }
}
