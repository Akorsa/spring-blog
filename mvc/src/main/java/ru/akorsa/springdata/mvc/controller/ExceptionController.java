package ru.akorsa.springdata.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import ru.akorsa.springdata.jpa.exceptions.ContactNotFoundException;
import ru.akorsa.springdata.jpa.exceptions.UnknownResourceException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionController {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(ContactNotFoundException.class)
    public ModelAndView handleContactNotFoundException() {
        logger.info("In handleException");

        ModelAndView mav = new ModelAndView();
        mav.addObject("errortitle", "Contact Missing in Action!");
        mav.addObject("errorbody", "We'll find the rascal, don't you worry");
        mav.setViewName("error");
        return mav;
    }

    @ExceptionHandler(UnknownResourceException.class)
    public String handleUnknownResourceException(HttpServletRequest req) {
        if (req.getRequestURI().indexOf("favicon") == 0)
            logger.info("404:" + req.getRequestURI());
        return "404";

    }
}
