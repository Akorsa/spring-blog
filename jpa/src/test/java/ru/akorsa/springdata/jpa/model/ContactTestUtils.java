package ru.akorsa.springdata.jpa.model;

import ru.akorsa.springdata.jpa.TestUtil;
import ru.akorsa.springdata.jpa.dto.ContactDTO;
import ru.akorsa.springdata.jpa.dto.ContactPhoneDTO;
import ru.akorsa.springdata.jpa.dto.HobbyDTO;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ContactTestUtils {

    private static final String CHARACTER = "a";

    private static final Long CONTACT_ID = 100L;
    private static final String EMAIL = "foo.bar@bar.com";
    private static final String FIRST_NAME = "Foo";
    private static final String LAST_NAME = "Bar";
    private static final Date BIRTH_DATE = TestUtil.date(1969, 6, 9);
    public static final String HOBBY_TITLE = "Quilting";

    private static final Set<ContactPhoneDTO> CONTACT_PHONE_DTOS =
            createContactPhoneDTOs();
    public static final HobbyDTO JOUSTING_HOBBY_DTO = createHobbyDTO();
    private static final Set<HobbyDTO> HOBBY_DTOS = createHobbyDTOs();

    private static Set<HobbyDTO> createHobbyDTOs() {
        return new HashSet<HobbyDTO>() {{
            add(new HobbyDTO("Jogging"));
            add(new HobbyDTO("Movies"));
            add(new HobbyDTO("Hip-hopping"));
        }};
    }

    private static HobbyDTO createHobbyDTO() {
        return new HobbyDTO("Jousting");
    }

    private static Set<ContactPhoneDTO> createContactPhoneDTOs() {

        return new HashSet<ContactPhoneDTO>() {{
            add(new ContactPhoneDTO("Mobile", "717-244-2222"));
            add(new ContactPhoneDTO("Business", "717-244-3333"));
        }};
    }

    public static ContactDTO newContactDTO() {
        ContactDTO dto = new ContactDTO();
        dto.setFirstName(FIRST_NAME);
        dto.setLastName(LAST_NAME);
        dto.setBirthDate(BIRTH_DATE);
        dto.setEmail(EMAIL);
        dto.setContactPhones(CONTACT_PHONE_DTOS);
        dto.setHobbies(HOBBY_DTOS);
        return dto;
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

        if (contact.getHobbies() != null) {
            dto.setHobbies(contact.getHobbies()
                    .stream()
                    .map(HobbyDTO::new)
                    .collect(Collectors.toSet()));
        }
        return dto;
    }

    public static ContactDTO addHobbyToContactDTO (ContactDTO contactDTO){
        contactDTO.getHobbies().add(JOUSTING_HOBBY_DTO);
        return contactDTO;
    }

    public static HobbyDTO newHobbyDTO() {
        HobbyDTO hobbyDTO = new HobbyDTO();
        hobbyDTO.setHobbyTitle(HOBBY_TITLE);
        return hobbyDTO;
    }

}
