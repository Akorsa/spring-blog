package ru.akorsa.springdata.jpa.dto;

import ru.akorsa.springdata.jpa.model.ContactPhone;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class ContactPhoneDTO {

    private Long contactPhoneId;
    private Long contactId;

    @NotEmpty
    @Length(max = ContactPhone.MAX_LENGTH_TEL_TYPE)
    private String telType;

    @NotEmpty
    @Length(max = ContactPhone.MAX_LENGTH_TEL_NUMBER)
    private String telNumber;

    public ContactPhoneDTO() {

    }

    public ContactPhoneDTO(ContactPhone contactPhone) {
        this.contactPhoneId = contactPhone.getContactPhoneId();
        this.contactId = contactPhone.getContact().getContactId();
        this.telType = contactPhone.getTelType();
        this.telNumber = contactPhone.getTelNumber();
    }

    public Long getContactPhoneId() {
        return contactPhoneId;
    }

    public void setContactPhoneId(Long contactPhoneId) {
        this.contactPhoneId = contactPhoneId;
    }


    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public String getTelType() {
        return telType;
    }

    public void setTelType(String telType) {
        this.telType = telType;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
