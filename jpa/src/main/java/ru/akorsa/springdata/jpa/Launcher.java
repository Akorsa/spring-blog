package ru.akorsa.springdata.jpa;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.SpringVersion;
import ru.akorsa.springdata.jpa.common.ContactUI;
import ru.akorsa.springdata.jpa.config.ApplicationConfig;

public class Launcher {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ApplicationConfig.class);
        ctx.refresh();
        System.out.println("version: " + SpringVersion.getVersion());
        ContactUI ui = ctx.getBean(ContactUI.class);
        ui.init();

    }

}
