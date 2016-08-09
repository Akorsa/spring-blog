package ru.akorsa.springdata.jpa.service.impl;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.akorsa.springdata.jpa.dto.ContactDTO;
import ru.akorsa.springdata.jpa.model.Contact;
import ru.akorsa.springdata.jpa.repository.ContactRepository;
import ru.akorsa.springdata.jpa.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.akorsa.springdata.jpa.service.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("contactService")
@Transactional
public class ContactServiceImpl implements ContactService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContactService.class);

    @Autowired
    private ContactRepository contactRepository;

    @PersistenceContext
    private EntityManager em;

    @Transactional(value = "jpaTransactionManager", readOnly=true)
    public List<Contact> findAll() {
        return Lists.newArrayList(contactRepository.findAll());
    }

    @Transactional(value = "jpaTransactionManager", readOnly=true)
    public List<Contact> findByFirstName(String firstName) {
        return contactRepository.findByFirstName(firstName);
    }

    @Transactional(value = "jpaTransactionManager", readOnly = true)
    public List<Contact> findByFirstNameAndLastName(
            String firstName, String lastName) {
        return contactRepository.findByFirstNameAndLastName(
                firstName, lastName);
    }

    @Transactional(value = "jpaTransactionManager", readOnly = true)
    public List<Contact> getContactsWithDetail() {
        return contactRepository.findAllWithDetail();
    }

    @Transactional(value = "jpaTransactionManager", readOnly=true)
    public Contact getContactByIdWithDetail(Long ID) {
        return contactRepository.findByContactIdWithDetail(ID);
    }


    public Contact findById(Long ID) { return contactRepository.findOne(ID); }

    public Contact getContactByEmail(String email) {
        return contactRepository.findByEmail(email);
    }

    public void save(Contact contact) { contactRepository.save(contact); }

    @Transactional(rollbackFor = NotFoundException.class)
    @Override
    public Contact update(ContactDTO updated) throws NotFoundException {
        LOGGER.info("Updating contact with information: {}", updated);

        Contact found = findById(updated.getContactId());

        //Update the contact information
        found.update(updated.getFirstName(), updated.getLastName(), updated.getEmail());
        //Update the address information
//        found.updateAddress(updated.getStreetAddress(), updated.getPostCode(), updated.getPostOffice(), updated.getState(), updated.getCountry());

        return found;
    }
}
