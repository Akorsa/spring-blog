package ru.akorsa.springdata.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.akorsa.springdata.jpa.common.ApplicationSettings;
import ru.akorsa.springdata.mvc.security.CurrentUser;
import ru.akorsa.springdata.mvc.security.CurrentUserDetailsService;

@ControllerAdvice
public class GeneralConroller {

    @Autowired
    private CurrentUserDetailsService userDetailsService;

    @Autowired
    private ApplicationSettings applicationSettings;

    @ModelAttribute("currentUser")
    public CurrentUser getCurrentUser(Authentication authentication) {
        if (authentication == null) {
            return null;
        } else {
            return userDetailsService.loadUserByUsername(authentication.getName());
        }
    }

    @ModelAttribute("appSettings")
    public ApplicationSettings getApplicationSettings() {
        return applicationSettings;
    }
}
