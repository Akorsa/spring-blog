package ru.akorsa.springdata.jpa.config;

import ru.akorsa.springdata.jpa.model.Contact;

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
}