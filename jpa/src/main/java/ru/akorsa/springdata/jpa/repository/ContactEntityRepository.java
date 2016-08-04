package ru.akorsa.springdata.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import ru.akorsa.springdata.jpa.model.ContactEntity;

import java.util.List;

public interface ContactEntityRepository extends CrudRepository<ContactEntity, Long> {
    List<ContactEntity> findByFirstName(String firstName);
    List<ContactEntity> findByFirstNameAndLastName(String firstName, String lastName);
    List<ContactEntity> findAllWithDetail();
}
