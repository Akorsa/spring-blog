package ru.akorsa.springdata.jpa;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.akorsa.springdata.jpa.common.SpringUI;
import ru.akorsa.springdata.jpa.config.ApplicationConfig;

public class Launcher {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ApplicationConfig.class);
        ctx.refresh();
        SpringUI ui = ctx.getBean(SpringUI.class);
        ui.init();

    }

}
