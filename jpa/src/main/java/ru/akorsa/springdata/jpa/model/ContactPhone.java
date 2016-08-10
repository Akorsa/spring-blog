package ru.akorsa.springdata.jpa.model;
import ru.akorsa.springdata.jpa.dto.ContactPhoneDTO;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "contact_phones")
public class ContactPhone {
    private Long contactPhoneId;
    private String phoneType;
    private String phoneNumber;
    private int version;
    private Contact contact;

    public static final int MAX_LENGTH_PHONE_TYPE = 20;
    public static final int MAX_LENGTH_PHONE_NUMBER = 20;

    public ContactPhone() {}

    public ContactPhone(ContactPhoneDTO contactPhoneDTO) {
        this.phoneType = contactPhoneDTO.getPhoneType();
        this.phoneNumber = contactPhoneDTO.getPhoneNumber();
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "contact_phone_id", nullable = false, insertable = true, updatable = true)
    public Long getContactPhoneId() {
        return contactPhoneId;
    }

    public void setContactPhoneId(Long contactPhoneId) {
        this.contactPhoneId = contactPhoneId;
    }

    @Basic
    @Column(name = "phone_type", nullable = false, insertable = true, updatable = true, length = MAX_LENGTH_PHONE_TYPE)
    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    @Basic
    @Column(name = "phone_number", nullable = false, insertable = true, updatable = true, length = MAX_LENGTH_PHONE_NUMBER)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "version", nullable = false, insertable = true, updatable = true, columnDefinition = "int default 0")
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactPhone that = (ContactPhone) o;

        if (contactPhoneId != that.contactPhoneId) return false;
        if (version != that.version) return false;
        if (phoneType != null ? !phoneType.equals(that.phoneType) : that.phoneType != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        Long result = contactPhoneId;
        result = 31 * result + (phoneType != null ? phoneType.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        return (int)((result >> 32) ^ result);
    }

    @ManyToOne
    @JoinColumn(name = "contact_id", referencedColumnName = "contact_id", nullable = false)
    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "   Details: " +
                "id=" + contactPhoneId +
                ", phoneType='" + phoneType + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", version=" + version;
    }

    public void update(final String phoneType, final String phoneNumber) {
        this.phoneType = phoneType;
        this.phoneNumber = phoneNumber;
    }

    public static Builder getBuilder(Contact contact, String phoneType, String phoneNumber) {
        return new Builder(contact, phoneType, phoneNumber);
    }

    public static class Builder {

        private ContactPhone built;

        public Builder(Contact contact, String phoneType, String phoneNumber) {
            built = new ContactPhone();
            built.contact = contact;
            built.phoneType = phoneType;
            built.phoneNumber = phoneNumber;
        }

        public ContactPhone build() {
            return built;
        }
    }


}
