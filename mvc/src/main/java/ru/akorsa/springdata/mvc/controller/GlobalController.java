package ru.akorsa.springdata.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import ru.akorsa.springdata.jpa.common.ApplicationSettings;
import ru.akorsa.springdata.jpa.exceptions.ContactNotFoundException;
import ru.akorsa.springdata.jpa.exceptions.UnknownResourceException;
import ru.akorsa.springdata.jpa.model.CurrentUser;
import ru.akorsa.springdata.mvc.security.CurrentUserDetailsService;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalController {

    private static final Logger logger = LoggerFactory.getLogger(GlobalController.class);

    protected static final String ERROR_404_VIEW = "errors/404";
    protected static final String ERROR_GENERIC_VIEW = "errors/generic";

    @Autowired
    private CurrentUserDetailsService userDetailsService;

    @Autowired
    private ApplicationSettings applicationSettings;

    @ModelAttribute("currentUser")
    public CurrentUser getCurrentUser(Authentication authentication) {
        return (authentication == null) ? null : userDetailsService.loadUserByUsername(authentication.getName());
    }

    @ModelAttribute("appSettings")
    public ApplicationSettings getApplicationSettings() {
        return applicationSettings;
    }

    @ExceptionHandler(ContactNotFoundException.class)
    public ModelAndView handleContactNotFoundException() {
        logger.info("In handleException");

        ModelAndView mav = new ModelAndView();
        mav.addObject("errortitle", "Contact Missing in Action!");
        mav.addObject("errorbody", "We'll find the rascal, don't you worry");
        mav.setViewName(ERROR_GENERIC_VIEW);
        return mav;
    }

    @ExceptionHandler(UnknownResourceException.class)
    public String handleUnknownResourceException(HttpServletRequest req) {
        if (req.getRequestURI().indexOf("favicon") == 0)
            logger.info("404:" + req.getRequestURI());
        return ERROR_404_VIEW;

    }
}
