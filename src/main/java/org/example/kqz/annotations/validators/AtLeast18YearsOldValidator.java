package org.example.kqz.annotations.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.kqz.annotations.AtLeast18YearsOld;

import java.time.LocalDate;
import java.time.Period;

public class AtLeast18YearsOldValidator implements ConstraintValidator<AtLeast18YearsOld, LocalDate> {

    @Override
    public boolean isValid(LocalDate birthdate, ConstraintValidatorContext context) {
        if (birthdate == null) {
            return true;
        }
        return Period.between(birthdate, LocalDate.now()).getYears() >= 18;
    }
}
