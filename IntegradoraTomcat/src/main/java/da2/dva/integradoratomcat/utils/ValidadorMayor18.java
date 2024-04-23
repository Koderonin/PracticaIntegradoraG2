package da2.dva.integradoratomcat.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class ValidadorMayor18 implements ConstraintValidator<CheckMayor18, LocalDate> {
    @Override
    public boolean isValid(LocalDate fecNac, ConstraintValidatorContext context) {
        if(fecNac == null) {
            return true;
        }
        Period periodo = Period.between(fecNac, LocalDate.now());
        return periodo.getYears() >= 18;
    }
}

