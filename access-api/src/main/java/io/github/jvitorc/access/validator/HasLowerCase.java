package io.github.jvitorc.access.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = HasLowerCaseValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface HasLowerCase {
    long min() default 1L;
    String message() default "app.validation.constraints.HasLowerCase.message";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
