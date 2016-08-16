package ru.akorsa.springdata.jpa.common;

import ru.akorsa.springdata.jpa.dto.ContactDTO;
import ru.akorsa.springdata.jpa.dto.ContactPhoneDTO;
import ru.akorsa.springdata.jpa.dto.HobbyDTO;
import ru.akorsa.springdata.jpa.model.Contact;
import ru.akorsa.springdata.jpa.model.ContactPhone;
import ru.akorsa.springdata.jpa.model.Hobby;

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

    public static ContactDTO contactToContactDTO(Contact contact) {
        ContactDTO dto = new ContactDTO();

        dto.setContactId(contact.getContactId());
        dto.setFirstName(contact.getFirstName());
        dto.setLastName(contact.getLastName());
        dto.setBirthDate(contact.getBirthDate());
        dto.setEmail(contact.getEmail());
        if (contact.getContactPhones() != null) {
            dto.setContactPhones(contact.getContactPhones()
            .stream()
            .map(ContactPhoneDTO::new)
            .collect(Collectors.toSet()));
        }
        if(contact.getHobbies() != null) {
            dto.setHobbies(contact.getHobbies()
            .stream()
            .map(HobbyDTO::new)
            .collect(Collectors.toSet()));
        }
        return dto;
    }

    public static HobbyDTO hobbyToHobbyDTO(Hobby hobby) {
        HobbyDTO hobbyDTO = new HobbyDTO();
        hobbyDTO.setHobbyId(hobby.getHobbyId());
        hobbyDTO.setHobbyTitle(hobby.getHobbyTitle());
        return hobbyDTO;
    }

    public static HobbyDTO createHobbyDTO(String hobbyTitle) {
        HobbyDTO hobbyDTO = new HobbyDTO();
        hobbyDTO.setHobbyTitle(hobbyTitle);
        return hobbyDTO;
    }

}
