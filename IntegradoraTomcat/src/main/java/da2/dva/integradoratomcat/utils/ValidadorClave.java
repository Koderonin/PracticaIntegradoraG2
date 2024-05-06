package da2.dva.integradoratomcat.utils;

import da2.dva.integradoratomcat.model.entities.Usuario;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidadorClave  implements ConstraintValidator<CheckClave, Usuario> {
    @Override
    public boolean isValid(Usuario datosFormulario, ConstraintValidatorContext context) {
        String clave = datosFormulario.getClave();
        String confirmarClave = datosFormulario.getConfirmClave();
        return clave.equals(confirmarClave);
    }
}
