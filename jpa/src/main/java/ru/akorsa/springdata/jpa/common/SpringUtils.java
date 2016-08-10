package ru.akorsa.springdata.jpa.common;

import ru.akorsa.springdata.jpa.dto.ContactDTO;
import ru.akorsa.springdata.jpa.dto.ContactPhoneDTO;
import ru.akorsa.springdata.jpa.model.Contact;
import ru.akorsa.springdata.jpa.model.ContactPhone;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: daveburke
 * Date: 4/15/15
 * Time: 12:06 PM
 */
public class SpringUtils {

    public static void printProperty(String header, String property) {
        System.out.println("\r\n" + header + " ------------------------------ ");
        System.out.println(property);
    }


    public static void listContact(String header, Contact contact) {

        System.out.println("\r\n" + header + " ------------------------------ ");
        System.out.println();
        System.out.println(contact);
        System.out.println();
    }

    public static void listContacts(String header, List<Contact> contacts) {
        System.out.println("\r\n" + header + " ------------------------------ ");
        System.out.println();
        contacts.forEach(System.out::println);
        System.out.println();
    }

    public static void listContactWithDetail(Contact contact) {
        System.out.println("SINGLE CONTACT WITH DETAILS ---------------------------------");
        System.out.println();
        System.out.println(contact);
        if (contact.getContactPhones() != null) {
            contact.getContactPhones().forEach(System.out::println);
        }
        if (contact.getHobbies() != null) {
            contact.getHobbies().forEach(System.out::println);
        }
        System.out.println();
    }


    public static void listContactsWithDetail(List<Contact> contacts) {
        System.out.println("LISTING ENTITIES WITH DETAILS ---------------------------------");
        System.out.println();
        for (Contact  contact : contacts) {
            System.out.println(contact);
            if (contact.getContactPhones() != null) {
                contact.getContactPhones().forEach(System.out::println);
            }
            if (contact.getHobbies() != null) {
                contact.getHobbies().forEach(System.out::println);
            }
            System.out.println();
        }
    }

    public static ContactDTO contactToContactDTO(Contact model) {
        ContactDTO dto = new ContactDTO();

        dto.setContactId(model.getContactId());
        dto.setFirstName(model.getFirstName());
        dto.setBirthDate(model.getBirthDate());
        dto.setLastName(model.getLastName());
        dto.setEmail(model.getEmail());
        if (model.getContactPhones() != null) {
            Set<ContactPhone> contactPhones = model.getContactPhones();
            contactPhones.stream()
                    .filter(contactPhone -> contactPhone.getPhoneType().equals("Mobile"))
                    .forEach(contactPhone -> contactPhone.setPhoneNumber("1-407-100-9999"));
            Set<ContactPhoneDTO> results = contactPhones
                    .stream()
                    .map(ContactPhoneDTO::new)
                    .collect(Collectors.toSet());
            dto.setContactPhones(results);
            results.forEach(System.out::println);
            System.out.println(dto.toString());
        }
        return dto;
    }

}
