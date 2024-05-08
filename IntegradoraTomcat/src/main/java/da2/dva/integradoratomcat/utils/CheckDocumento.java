package da2.dva.integradoratomcat.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidadorCheckDocumento.class})
public @interface CheckDocumento {

    String message() default "{CheckDocumento.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
