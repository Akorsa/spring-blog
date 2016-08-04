package ru.akorsa.springdata.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import ru.akorsa.springdata.jpa.config.SpringJpaConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.akorsa.springdata.jpa.service.ContactJpaService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { SpringJpaConfiguration.class })
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class SpringJpaTests {

    @Autowired
    ContactJpaService contactService;

    @Test
    public void contextLoads() {
    }
}
