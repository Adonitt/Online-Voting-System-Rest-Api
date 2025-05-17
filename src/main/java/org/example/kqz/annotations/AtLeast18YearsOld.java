package org.example.kqz.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.kqz.annotations.validators.AtLeast18Validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AtLeast18Validator.class)
public @interface AtLeast18YearsOld {
    String message() default "User must be at least 18 years old to register";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

