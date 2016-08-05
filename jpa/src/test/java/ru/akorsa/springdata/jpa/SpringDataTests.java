package ru.akorsa.springdata.jpa;

import ru.akorsa.springdata.jpa.common.SpringProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.akorsa.springdata.jpa.config.ApplicationConfig;
import ru.akorsa.springdata.jpa.enums.DataConfigProfile;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(classes = ApplicationConfig.class)
@Transactional
@ActiveProfiles(DataConfigProfile.H2)
public class SpringDataTests {

    @Autowired
    SpringProperties springProperties;

    @Test
    public void contextLoads() {
        assertNotNull(springProperties.getToken());

    }

}

