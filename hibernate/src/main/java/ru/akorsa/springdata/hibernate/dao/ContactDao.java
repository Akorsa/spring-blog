package ru.akorsa.springdata.hibernate.dao;


import ru.akorsa.springdata.hibernate.model.Contact;

import java.util.List;

public interface ContactDao extends Dao<Contact> {

    List<Contact> findByEmail(String email);
    List<Contact> findAllWithDetail();
    Contact findById(Long id);
}
