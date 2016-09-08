package ru.akorsa.springdata.mvc.controller;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.akorsa.springdata.jpa.dto.UserDTO;
import ru.akorsa.springdata.jpa.model.Authority;
import ru.akorsa.springdata.jpa.model.validators.UserCreateFormValidator;
import ru.akorsa.springdata.jpa.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;
    private final UserCreateFormValidator userCreateFormValidator;

    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    public RegisterController(UserService userService, UserCreateFormValidator userCreateFormValidator) {
        this.userService = userService;
        this.userCreateFormValidator = userCreateFormValidator;
    }

    @InitBinder("userDTO")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCreateFormValidator);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String registrationForm(@ModelAttribute UserDTO userDTO, HttpServletRequest request) {
        if (request.getUserPrincipal() != null)
            return "redirect:/";
        else
            return "register";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute("userDTO") UserDTO userDTO, BindingResult result,
                           RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "register";
        }
        userDTO.setAuthorities(Lists.newArrayList(new Authority("ROLE_USER")));
        try {
            userService.create(userDTO);
        } catch (DataIntegrityViolationException e) {
            logger.warn("Exception occurred when trying to save the user", e);
            result.reject("unknown.error", "An error has occurred and has been logged");
            return "register";
        }

        redirect.addFlashAttribute(ContactController.FLASH_MESSAGE_KEY_FEEDBACK, "Successfully registered");
        return "redirect:/contacts";
    }
}
