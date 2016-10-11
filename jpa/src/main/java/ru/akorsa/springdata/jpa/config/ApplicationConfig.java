package ru.akorsa.springdata.jpa.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.akorsa.springdata.jpa.model.auditors.CurrentTimeDateTimeService;
import ru.akorsa.springdata.jpa.model.auditors.DateTimeService;

@Configuration
@EnableConfigurationProperties
@EnableTransactionManagement
@ComponentScan(basePackages = "ru.akorsa.springdata.jpa")
@EnableJpaRepositories(basePackages = "ru.akorsa.springdata.jpa")
@PropertySource("classpath:/META-INF/spring/application.properties")
public class ApplicationConfig {

    @Bean
    DateTimeService currentTimeDateTimeService() {
        return new CurrentTimeDateTimeService();
    }
}
