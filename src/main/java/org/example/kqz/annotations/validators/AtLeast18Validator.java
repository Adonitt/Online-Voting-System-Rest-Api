package org.example.kqz.annotations.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.kqz.annotations.AtLeast18YearsOld;

import java.time.LocalDate;
import java.time.Period;

public class AtLeast18Validator implements ConstraintValidator<AtLeast18YearsOld, LocalDate> {

    @Override
    public boolean isValid(LocalDate birthDate, ConstraintValidatorContext context) {
        return birthDate != null && !birthDate.isAfter(LocalDate.now().minusYears(18));
    }
}

