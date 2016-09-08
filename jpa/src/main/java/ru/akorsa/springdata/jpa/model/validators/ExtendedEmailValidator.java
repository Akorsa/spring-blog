package ru.akorsa.springdata.jpa.model.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.*;

//@Email(message = "Please provide a valid email address")
@Pattern(regexp = ".+@.+\\..+", message = "{ExtendedEmailValidator.email}")
@Target( {ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
public @interface ExtendedEmailValidator {
    String message() default "{ExtendedEmailValidator.email}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
