package ru.akorsa.springdata.jpa;

import ru.akorsa.springdata.jpa.config.SpringApplication;
import ru.akorsa.springdata.jpa.config.ProductionConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Launcher {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ProductionConfiguration.class);
        ctx.refresh();
        SpringApplication app = ctx.getBean(SpringApplication.class);
        app.init();

    }

}
