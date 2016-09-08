package ru.akorsa.springdata.jpa.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("file:C:\\temp\\1.properties")
@ConfigurationProperties(prefix = "external")
public class ApplicationSettings {

    private Boolean isDemoSite;

    public Boolean getIsDemoSite() {
        return isDemoSite;
    }

    public void setIsDemoSite(Boolean isDemoSite) {
        this.isDemoSite = isDemoSite;
    }
}
