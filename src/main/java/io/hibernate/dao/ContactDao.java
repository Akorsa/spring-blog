package io.hibernate.dao;

import java.util.List;

public interface ContactDao {
    // Find all contacts
    List<Contact> findAll();

    // Find all contacts with telephone and hobbies
    List<Contact> findAllWithDetail();

    // Find a contact with details by id
    Contact findById(Long id);

    // Insert or update a contact
    Contact save(Contact contact);

    // Delete a contact
    void delete(Contact contact);
}
