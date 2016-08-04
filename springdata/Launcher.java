package ru.akorsa.springdata;

import ru.akorsa.springdata.config.SpringHbnConfiguration;
import ru.akorsa.springdata.config.SpringJpaConfiguration;
import ru.akorsa.springdata.config.SpringProperties;
import ru.akorsa.springdata.dev.SpringDevelopment;
import ru.akorsa.springdata.hbn.service.ContactService;
import ru.akorsa.springdata.jpa.service.ContactJpaService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Launcher {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(SpringHbnConfiguration.class);
        ctx.register(SpringJpaConfiguration.class);
        ctx.refresh();

        ContactService contactService = (ContactService) ctx.getBean("hbnContactService");
        ContactJpaService contactJpaService = (ContactJpaService) ctx.getBean("jpaContactService");
        SpringProperties springProperties = ctx.getBean(SpringProperties.class);

        SpringDevelopment springDevelopment = new SpringDevelopment(springProperties, contactJpaService, contactService);

        springDevelopment.propertiesDemo();
        springDevelopment.hibernateDemo();
        springDevelopment.jpaDemo();

    }
}
