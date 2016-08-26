package ru.akorsa.springdata.jpa.common;

import org.hibernate.validator.constraints.Email;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.*;

@Email(message = "Please provide a valid email address")
@Pattern(regexp = ".+@.+\\..+", message = "Please provide a valid email address")
@Target( {ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
public @interface ExtendedEmailValidator {
    String message() default "Please provide a valid email address";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}