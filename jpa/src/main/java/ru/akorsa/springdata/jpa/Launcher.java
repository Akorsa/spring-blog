package ru.akorsa.springdata.jpa;

import ru.akorsa.springdata.jpa.config.SpringJpaConfiguration;
import ru.akorsa.springdata.jpa.config.SpringProperties;
import ru.akorsa.springdata.jpa.dev.SpringDevelopment;
import ru.akorsa.springdata.jpa.service.ContactEntityService;
import ru.akorsa.springdata.jpa.service.ContactJpaService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Launcher {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(SpringJpaConfiguration.class);
        ctx.refresh();

        ContactJpaService contactJpaService = (ContactJpaService) ctx.getBean("jpaContactService");
        ContactEntityService contactEntityService = (ContactEntityService) ctx.getBean("entityContactService");
        SpringProperties springProperties = ctx.getBean(SpringProperties.class);

        SpringDevelopment springDevelopment = new SpringDevelopment(springProperties, contactJpaService, contactEntityService);

//        springDevelopment.propertiesDemo();
//        springDevelopment.jpaDemo();

        springDevelopment.entityDemo();
    }
}
