package ru.akorsa.springdata.hibernate.service.impl;

import ru.akorsa.springdata.hibernate.dao.ContactDao;
import ru.akorsa.springdata.hibernate.model.Contact;
import ru.akorsa.springdata.hibernate.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.util.Assert.notNull;

@Service(value = "hbnContactService")
@Transactional(value = "hibernateTransactionManager")
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactDao contactDao;

    @Override
    public void createContact(Contact contact) {
        notNull(contact, "contact can't be null");
        contactDao.create(contact);
    }

    @Override
    public List<Contact> getContacts() {
        return contactDao.getAll();
    }

    @Override
    public List<Contact> getContactsWithDetail() {
        return contactDao.findAllWithDetail();
    }

    @Override
    public List<Contact> getContactsByEmail(String email) {
        return contactDao.findByEmail(email);
    }

    @Override
    public Contact getContact(Long id) {
        notNull(id, "id can't be null");
        return contactDao.findById(id);
    }

    @Override
    public void updateContact(Contact contact) {
        notNull(contact, "contact can't be null");
        contactDao.update(contact);
    }

    @Override
    public void deleteContact(Long id) {
        notNull(id, "id can't be null");
        contactDao.deleteById(id);
    }
}
