package ru.akorsa.springdata.hibernate.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "contact", schema = "dev_hibernate", catalog = "")
public class ContactEntity {
    private int id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String email;
    private int version;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "first_name", nullable = false, length = 60)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = false, length = 40)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "birth_date", nullable = true)
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "version", nullable = false)
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

        ContactEntity that = (ContactEntity) o;

        if (id != that.id) return false;
        if (version != that.version) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (birthDate != null ? !birthDate.equals(that.birthDate) : that.birthDate != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + version;
        return result;
    }

    private Collection<ContactTelDetailEntity> contactTelDetailEntities;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contactEntity")
    public Collection<ContactTelDetailEntity> getContactTelDetailEntities() {
        return contactTelDetailEntities;
    }

    public void setContactTelDetailEntities(Collection<ContactTelDetailEntity> contactTelDetailEntities) {
        this.contactTelDetailEntities = contactTelDetailEntities;
    }

    private Set<HobbyEntity> hobbyEntities;

    @ManyToMany
    @JoinTable(name = "contact_hobby_detail", catalog = "dev_hibernate", schema = "", joinColumns = @JoinColumn(name = "contact_id", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "hobby_id", referencedColumnName = "hobby_id", nullable = false))
    public Set<HobbyEntity> getHobbyEntities() {
        return hobbyEntities;
    }

    public void setHobbyEntities(Set<HobbyEntity> hobbyEntities) {
        this.hobbyEntities = hobbyEntities;
    }
}
