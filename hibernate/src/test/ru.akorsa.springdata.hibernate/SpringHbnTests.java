package ru.akorsa.springdata.hibernate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.akorsa.springdata.hibernate.config.SpringHbnConfiguration;
import ru.akorsa.springdata.hibernate.service.ContactService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { SpringHbnConfiguration.class})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class SpringHbnTests {

    @Autowired
    ContactService contactService;

    @Test
    public void contextLoads() {
    }
}


