package da2.dva.integradoratomcat.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(ElementType.TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = ValidadorClave.class)
@Documented
public @interface CheckClave {

    String message() default "{password.invalidConfirmation}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}