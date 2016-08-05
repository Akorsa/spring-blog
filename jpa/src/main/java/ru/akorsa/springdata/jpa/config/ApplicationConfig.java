package ru.akorsa.springdata.jpa.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableConfigurationProperties
@EnableTransactionManagement
@ComponentScan(basePackages = "ru.akorsa.springdata.jpa")
@EnableJpaRepositories(basePackages = "ru.akorsa.springdata.jpa")
public class ApplicationConfig {
}
