package ru.akorsa.springdata.jpa.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.akorsa.springdata.jpa.model.Contact;

import java.util.List;

public interface ContactRepository extends CrudRepository<Contact, Long> {
    List<Contact> findByFirstName(String firstName);

    List<Contact> findByFirstNameAndLastName(String firstName, String lastName);

    Contact findByEmail(String email);

    @Query("select distinct c from Contact c left join fetch " +
            "c.contactPhones p left join fetch c.hobbies h where c.contactId = ?1")
    Contact findByContactIdWithDetail(Long ID);

    @Query("select distinct c from Contact c left join fetch " +
            "c.contactPhones p left join fetch c.hobbies h")
    List<Contact> findAllWithDetail();

    @Query("select c from Contact c where c.lastName like %:lastName%")
    List<Contact> searchByLastName(@Param("lastName") String lastName);

}
