package ru.akorsa.springdata.jpa.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import ru.akorsa.springdata.jpa.model.Contact;

import javax.persistence.GeneratedValue;
import java.util.Date;
import java.util.Set;

public class ContactDTO {

    private Long contactId;
    private Set<ContactPhoneDTO> contactPhones;

    @Email
    @Length(max = Contact.MAX_LENGTH_EMAIL_ADDRESS)
    private String email;

    @NotEmpty
    @Length(max = Contact.MAX_LENGTH_FIRST_NAME)
    private String firstName;

    @NotEmpty
    @Length(max = Contact.MAX_LENGTH_LAST_NAME)
    private String lastName;

    private Date birthDate;

    public ContactDTO() {

    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<ContactPhoneDTO> getContactPhones() {
        return contactPhones;
    }

    public void setContactPhones(Set<ContactPhoneDTO> contactPhones) {
        this.contactPhones = contactPhones;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
