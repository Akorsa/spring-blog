package ru.akorsa.springdata.jpa.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "hobby", schema = "dev_hibernate", catalog = "")
public class HobbyEntity {
    private Set<ContactEntity> contactEntities;

    @Id
    @Column(name = "hobby_id", nullable = false, length = 20)
    public String getHobbyId() {
        return hobbyId;
    }

    public void setHobbyId(String hobbyId) {
        this.hobbyId = hobbyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HobbyEntity that = (HobbyEntity) o;

        if (hobbyId != null ? !hobbyId.equals(that.hobbyId) : that.hobbyId != null) return false;

        return true;
    }

    public String toString() {
        return "Hobby: " + getHobbyId();
    }

    @Override
    public int hashCode() {
        return hobbyId != null ? hobbyId.hashCode() : 0;
    }

    @ManyToMany(mappedBy = "hobbyEntities")
    public Set<ContactEntity> getContactEntities() {
        return contactEntities;
    }

    public void setContactEntities(Set<ContactEntity> contactEntities) {
        this.contactEntities = contactEntities;
    }

    private String hobbyId;
}
