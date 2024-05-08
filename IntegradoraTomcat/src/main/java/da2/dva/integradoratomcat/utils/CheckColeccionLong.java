package da2.dva.integradoratomcat.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(ElementType.FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = ValidadorColeccionLong.class)
@Documented
public @interface CheckColeccionLong {

    String coleccion();

    String message() default "Elemento inválido";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
