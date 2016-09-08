package ru.akorsa.springdata.jpa.model.validators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.akorsa.springdata.jpa.dto.UserDTO;
import ru.akorsa.springdata.jpa.service.UserService;

@Component
public class UserCreateFormValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserCreateFormValidator.class);
    private final UserService userService;

    @Autowired
    public UserCreateFormValidator(UserService userService) {
        this.userService = userService;
    }

    public boolean supports(Class<?> clazz) {
        return clazz.equals(UserDTO.class);
    }

    public void validate(Object target, Errors errors) {
        LOGGER.debug("Validating {}", target);
        UserDTO form = (UserDTO) target;
        validatePasswords(errors, form);
        validateEmail(errors, form);
        validateUsername(errors, form);
    }

    private void validatePasswords(Errors errors, UserDTO form) {
        if (!form.getPassword().equals(form.getRepeatedPassword())) {
            errors.reject("password.no_match", "Passwords do not match");
        }
    }

    private void validateEmail(Errors errors, UserDTO form) {
        if (userService.getByEmail(form.getEmail()).isPresent()) {
            errors.reject("email.exists", "User with this email already exists");
        }
    }

    private void validateUsername(Errors errors, UserDTO form) {
        if (userService.getUserByUsername(form.getUsername()) != null) {
            errors.reject("user.exists", "User with this username already exists");
        }
    }
}
