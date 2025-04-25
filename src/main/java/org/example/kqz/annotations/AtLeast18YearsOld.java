package org.example.kqz.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.kqz.annotations.validators.AtLeast18YearsOldValidator;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AtLeast18YearsOldValidator.class)
@Documented

public @interface AtLeast18YearsOld {
    String message() default "Admin must be at least 18 years old";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
