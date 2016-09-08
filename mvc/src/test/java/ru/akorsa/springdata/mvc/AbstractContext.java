package ru.akorsa.springdata.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import ru.akorsa.springdata.jpa.config.ApplicationConfig;
import ru.akorsa.springdata.jpa.enums.DataConfigProfile;
import ru.akorsa.springdata.mvc.config.SecurityConfig;
import ru.akorsa.springdata.mvc.config.WebConfig;

@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(classes = {WebConfig.class,
        ApplicationConfig.class, SecurityConfig.class})
@WebAppConfiguration
@Transactional
@ActiveProfiles(DataConfigProfile.H2)
public class AbstractContext {

    @Autowired
 	protected WebApplicationContext context;
}
