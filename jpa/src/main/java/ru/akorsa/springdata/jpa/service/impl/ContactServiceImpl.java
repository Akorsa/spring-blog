package ru.akorsa.springdata.jpa.service.impl;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.akorsa.springdata.jpa.dto.ContactDTO;
import ru.akorsa.springdata.jpa.dto.ContactPhoneDTO;
import ru.akorsa.springdata.jpa.model.Contact;
import ru.akorsa.springdata.jpa.model.ContactPhone;
import ru.akorsa.springdata.jpa.repository.ContactPhoneRepository;
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

    @Autowired
    private ContactPhoneRepository contactPhoneRepository;

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

    @Transactional(rollbackFor = NotFoundException.class)
    @Override
    public Contact deleteById(Long id) throws NotFoundException {
        LOGGER.info("Deleting contact by id: {}",id);

        Contact deleted = findById(id);
        contactRepository.delete(deleted);

        LOGGER.info("Deleted contact: {}", deleted);
        return deleted;
    }

    @Transactional(rollbackFor = NotFoundException.class)
    @Override
    public Contact update(ContactDTO updated) throws NotFoundException {
        LOGGER.info("Updating contact with information: {}", updated);

        Contact found = findById(updated.getContactId());

        //Update the contact information
        found.update(updated.getFirstName(), updated.getLastName(), updated.getEmail());

        //Update the contact phone
        if (found.getContactPhones() != null) {
            for (ContactPhoneDTO contactPhoneDTO : updated.getContactPhones()) {
                ContactPhone contactPhone = contactPhoneRepository.findByContactPhoneId(contactPhoneDTO.getContactPhoneId());
                if (contactPhone != null) {
                    contactPhone.update(contactPhoneDTO.getPhoneType(),contactPhoneDTO.getPhoneNumber());
                }
            }
        }
        return found;
    }

    @Transactional
    @Override
    public Contact add(ContactDTO added) {
        LOGGER.info("Adding new contact with information: {}", added);

        //Creates an instance of a Contact by using the builder pattern
        Contact contact = Contact.getBuilder(added.getFirstName(),
                added.getLastName(), added.getEmail())
                .birthDate(added.getBirthDate())
                .build();

        Contact saved = contactRepository.save(contact);

        if(added.getContactPhones() != null) {
            for (ContactPhoneDTO contactPhoneDTO : added.getContactPhones()) {
                ContactPhone contactPhone = ContactPhone.getBuilder(saved,
                        contactPhoneDTO.getPhoneType(),
                        contactPhoneDTO.getPhoneNumber())
                        .build();

                contactPhoneRepository.save(contactPhone);
            }
        }
        em.refresh(saved);
        return saved;
    }

    @Transactional(value = "jpaTransactionManager", readOnly = true)
    public List<ContactPhone> findContactPhonesByContactId(Long contactId) {
        return contactPhoneRepository.findByContact_ContactId(contactId);
    }
}
