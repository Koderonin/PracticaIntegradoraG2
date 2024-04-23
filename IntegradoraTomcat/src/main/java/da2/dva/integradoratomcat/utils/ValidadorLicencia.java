package da2.dva.integradoratomcat.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidadorLicencia implements ConstraintValidator<CheckLicencia, Boolean> {
    @Override
    public boolean isValid(Boolean value, ConstraintValidatorContext context) {
        if(value == null) {
            return false;
        }
        return value;
    }
}