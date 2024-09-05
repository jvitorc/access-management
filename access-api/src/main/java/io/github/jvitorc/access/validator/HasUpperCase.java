package io.github.jvitorc.access.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = HasUpperCaseValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface HasUpperCase {
    long min() default 1L;
    String message() default "app.validation.constraints.HasUpperCase.message";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
