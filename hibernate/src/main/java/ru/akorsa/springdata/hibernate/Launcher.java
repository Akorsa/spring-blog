package ru.akorsa.springdata.hibernate;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.akorsa.springdata.hibernate.config.SpringHbnConfiguration;
import ru.akorsa.springdata.hibernate.config.SpringProperties;
import ru.akorsa.springdata.hibernate.dev.SpringDevelopment;
import ru.akorsa.springdata.hibernate.service.ContactService;

public class Launcher {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(SpringHbnConfiguration.class);
        ctx.refresh();

        ContactService contactService = (ContactService) ctx.getBean("hbnContactService");
        SpringProperties springProperties = ctx.getBean(SpringProperties.class);

        SpringDevelopment springDevelopment = new SpringDevelopment(springProperties, contactService);

        springDevelopment.propertiesDemo();
        springDevelopment.hibernateDemo();

    }
}
