package da2.dva.integradoratomcat.utils;

import da2.dva.integradoratomcat.services.ServicioUsuario;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidadorEmail implements ConstraintValidator<CheckLicencia, String> {
    @Autowired
    ServicioUsuario servicioUsuario;
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !servicioUsuario.devuelveUsuarios().containsKey(email);
    }
}