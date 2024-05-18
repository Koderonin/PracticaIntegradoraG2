package da2.dva.integradoratomcat.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidadorEmail.class})
public @interface CheckEmail {

    String message() default "{CheckDocumento.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
