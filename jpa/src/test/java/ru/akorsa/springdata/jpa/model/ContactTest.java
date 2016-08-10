package ru.akorsa.springdata.jpa.model;

import ru.akorsa.springdata.jpa.TestUtil;
import ru.akorsa.springdata.jpa.config.ApplicationConfig;
import ru.akorsa.springdata.jpa.enums.DataConfigProfile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.akorsa.springdata.jpa.model.Contact;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = false)
@ContextConfiguration(classes = ApplicationConfig.class)
@Transactional
@ActiveProfiles(DataConfigProfile.H2)
public class ContactTest {
    private static final String EMAIL = "foo.bar@bar.com";
    private static final String FIRST_NAME = "Foo";
    private static final String LAST_NAME = "Bar";
    private static final Date BIRTH_DATE = TestUtil.date(1969, 6, 9);

    @Test
    public void buildWithMandatoryValues() {
        Contact build = Contact.getBuilder(FIRST_NAME, LAST_NAME, EMAIL).build();

        assertNull(build.getContactId());

        assertEquals(FIRST_NAME, build.getFirstName());
        assertEquals(LAST_NAME, build.getLastName());
        assertEquals(EMAIL, build.getEmail());
        assertNull(build.getBirthDate());
    }

    @Test
    public void buildWithMandatoryValuesAndBirthDate() {
        Contact build = Contact.getBuilder(FIRST_NAME, LAST_NAME, EMAIL)
                .birthDate(BIRTH_DATE)
                .build();

        assertNull(build.getContactId());

        assertEquals(FIRST_NAME, build.getFirstName());
        assertEquals(LAST_NAME, build.getLastName());
        assertEquals(EMAIL, build.getEmail());
        assertEquals(BIRTH_DATE, build.getBirthDate());

    }
}
