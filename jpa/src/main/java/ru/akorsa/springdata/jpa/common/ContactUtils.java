package ru.akorsa.springdata.jpa.common;

import ru.akorsa.springdata.jpa.dto.ContactDTO;
import ru.akorsa.springdata.jpa.dto.ContactPhoneDTO;
import ru.akorsa.springdata.jpa.dto.HobbyDTO;
import ru.akorsa.springdata.jpa.model.Contact;
import ru.akorsa.springdata.jpa.model.Hobby;
import ru.akorsa.springdata.jpa.model.User;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: daveburke
 * Date: 4/15/15
 * Time: 12:06 PM
 */
public class ContactUtils {

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
        for (Contact contact : contacts) {
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

    public static void listUsersWithDetail(List<User> users) {
        System.out.println("LISTING ENTITIES WITH DETAILS ---------------------------------");
        System.out.println();
        for (User user : users) {
            System.out.println(user);
            if (user.getAuthorities() != null) {
                user.getAuthorities().forEach(System.out::println);
            }
            //            if (user.getUserProfile() != null) {
            //                System.out.println(user.getUserProfile());
            //            }
            System.out.println();
        }
    }

    public static void listUser(String header, User user) {
        System.out.println("\r\n" + header + " ------------------------------ ");
        System.out.println();
        System.out.println(user);
        System.out.println();
    }

    public static List<ContactDTO> contactsToContactsDTOs(List<Contact> contacts) {
        return contacts
                .stream()
                .map(ContactUtils::contactToContactDTO)
                .collect(Collectors.toList());
    }

    public static ContactDTO contactToContactDTO(Contact contact) {
        ContactDTO dto = new ContactDTO();

        dto.setContactId(contact.getContactId());
        dto.setFirstName(contact.getFirstName());
        dto.setLastName(contact.getLastName());
        dto.setBirthDate(contact.getBirthDate());
        dto.setEmail(contact.getEmail());
        dto.setCreatedByUser(contact.getCreatedByUser());
        dto.setCreationTime(contact.getCreationTime());
        dto.setModifiedByUser(contact.getModifiedByUser());
        dto.setModificationTime(contact.getModificationTime());
        if (contact.getContactPhones() != null) {
            dto.setContactPhones(contact.getContactPhones()
                    .stream()
                    .map(ContactPhoneDTO::new)
                    .collect(Collectors.toSet()));
        }
        if (contact.getHobbies() != null) {
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

    public static Long randomNegativeId() {
        Random rand = new Random();
        return -1 * ((long) rand.nextInt(1000));
    }

    public static Long randomContactId() {
        Random rand = new Random();
        return (long) (rand.nextInt(10) +1);
    }

}
