package da2.dva.integradoratomcat.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Map;
import java.util.Objects;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(ElementType.FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = ValidadorColecciones.class)
@Documented
public @interface CheckColeccion {

    String coleccion();
    String message() default "ERROR EN LA PREGUNTA";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
