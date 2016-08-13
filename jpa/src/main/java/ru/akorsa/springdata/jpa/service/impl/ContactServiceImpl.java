package ru.akorsa.springdata.jpa.service.impl;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.akorsa.springdata.jpa.dto.ContactDTO;
import ru.akorsa.springdata.jpa.dto.ContactPhoneDTO;
import ru.akorsa.springdata.jpa.dto.HobbyDTO;
import ru.akorsa.springdata.jpa.model.Contact;
import ru.akorsa.springdata.jpa.model.ContactPhone;
import ru.akorsa.springdata.jpa.model.Hobby;
import ru.akorsa.springdata.jpa.repository.ContactPhoneRepository;
import ru.akorsa.springdata.jpa.repository.ContactRepository;
import ru.akorsa.springdata.jpa.repository.HobbyRepository;
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

    @Autowired
    private HobbyRepository hobbyRepository;

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


    public Contact findContactById(Long ID) { return contactRepository.findOne(ID); }

    public Contact getContactByEmail(String email) {
        return contactRepository.findByEmail(email);
    }

    @Transactional(rollbackFor = NotFoundException.class)
    @Override
    public Contact deleteById(Long id) throws NotFoundException {
        LOGGER.info("Deleting contact by id: {}",id);

        Contact deleted = findContactById(id);
        contactRepository.delete(deleted);

        LOGGER.info("Deleted contact: {}", deleted);
        return deleted;
    }

    @Transactional(rollbackFor = NotFoundException.class)
    @Override
    public Contact update(ContactDTO contactDTO) throws NotFoundException {
        LOGGER.info("Updating contact with information: {}", contactDTO);

        Contact found = findContactById(contactDTO.getContactId());

        //Update the contact information
        found.update(contactDTO.getFirstName(), contactDTO.getLastName(), contactDTO.getEmail());

        //Update the contact phone
        if (found.getContactPhones() != null) {
            for (ContactPhoneDTO contactPhoneDTO : contactDTO.getContactPhones()) {
                ContactPhone contactPhone = contactPhoneRepository.findByContactPhoneId(contactPhoneDTO.getContactPhoneId());
                if (contactPhone != null) {
                    contactPhone.update(contactPhoneDTO.getPhoneType(),contactPhoneDTO.getPhoneNumber());
                }
            }
        }

        if (contactDTO.getHobbies() != null) {
            saveNewHobbiesToDatabase(contactDTO);
        }

        if (contactDTO.getHobbies() != null) {
            for (HobbyDTO hobbyDTO : contactDTO.getHobbies()) {
                Hobby hobby = hobbyRepository.findByHobbyTitleIgnoreCase(hobbyDTO.getHobbyTitle());

                if (!found.getHobbies().contains(hobby))
                    found.getHobbies().add(hobby);
            }
        }
        return found;
    }

    @Transactional(rollbackFor = NotFoundException.class)
    @Override
    public Contact removeHobby(ContactDTO contactDTO, Long hobbyId) throws NotFoundException {
        LOGGER.info("Removing contact hobby with information:{}", contactDTO);

        Contact found = findContactById(contactDTO.getContactId());
        Hobby hobby = hobbyRepository.findOne(hobbyId);

        if(found.getHobbies().contains(hobby))
            found.getHobbies().remove(hobby);

        return found;
    }

    @Transactional
    @Override
    public Contact add(ContactDTO contactDTO) {
        LOGGER.info("Adding new contact with information: {}", contactDTO);

        //Creates an instance of a Contact by using the builder pattern
        Contact contact = Contact.getBuilder(contactDTO.getFirstName(),
                contactDTO.getLastName(), contactDTO.getEmail())
                .birthDate(contactDTO.getBirthDate())
                .build();

        Contact saved = contactRepository.save(contact);

        if(contactDTO.getContactPhones() != null) {
            for (ContactPhoneDTO contactPhoneDTO : contactDTO.getContactPhones()) {
                ContactPhone contactPhone = ContactPhone.getBuilder(saved,
                        contactPhoneDTO.getPhoneType(),
                        contactPhoneDTO.getPhoneNumber())
                        .build();

                contactPhoneRepository.save(contactPhone);
            }
        }

        if (contactDTO.getHobbies() != null) {
            saveNewHobbiesToDatabase(contactDTO);
        }

        em.refresh(saved);

        if (contactDTO.getHobbies() != null) {
            for (HobbyDTO hobbyDTO : contactDTO.getHobbies()) {
                Hobby hobby = hobbyRepository.findByHobbyTitleIgnoreCase(hobbyDTO.getHobbyTitle());
                saved.getHobbies().add(hobby);
            }
        }
        return saved;
    }

    @Transactional(value = "jpaTransactionManager", readOnly = true)
    public List<ContactPhone> findContactPhonesByContactId(Long contactId) {
        return contactPhoneRepository.findByContact_ContactId(contactId);
    }

    @Transactional
    @Override
    public Hobby addNewHobby(HobbyDTO hobbyDto) {
        LOGGER.info("Adding new hobby with information: {}", hobbyDto);

        //Creates an instance of a Hobby
        Hobby hobby = new Hobby(hobbyDto.getHobbyTitle());
        return hobbyRepository.save(hobby);

    }

    @Transactional(rollbackFor = NotFoundException.class)
    @Override
    public Hobby updateHobbyTitle(HobbyDTO hobbyDto) throws NotFoundException {
        LOGGER.info("Updating hobby with information: {}", hobbyDto);

        Hobby found = hobbyRepository.findOne(hobbyDto.getHobbyId());

        //Update the hobby titles
        found.update(hobbyDto.getHobbyTitle());
        return found;
    }

    @Override
    public List<Hobby> findAllContacts() {
        return Lists.newArrayList(hobbyRepository.findAll());
    }

    @Transactional(value = "jpaTransactionManager", readOnly = true)
    @Override
    public Hobby findByHobbyTitle(String hobbyTitle) {
        return hobbyRepository.findByHobbyTitleIgnoreCase(hobbyTitle);
    }

    private void saveNewHobbiesToDatabase(ContactDTO added) {
        for (HobbyDTO hobbyDTO : added.getHobbies()) {
            Hobby hobby = hobbyRepository.findByHobbyTitleIgnoreCase(hobbyDTO.getHobbyTitle());
            if (hobby == null) {
                hobby = new Hobby(hobbyDTO.getHobbyTitle());
                hobbyRepository.save(hobby);
            }
        }
    }
}
