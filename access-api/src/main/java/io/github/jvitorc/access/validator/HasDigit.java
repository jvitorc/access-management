package io.github.jvitorc.access.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = HasDigitValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface HasDigit {
    long min() default 1L;
    String message() default "app.validation.constraints.HasDigt.message";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
