package io.github.jvitorc.access.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = HasSpecialCharacterValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface HasSpecialCharacter {
    long min() default 1L;
    String regex() default ".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]+.*";
    String message() default "app.validation.constraints.HasSpecialCharacter.message";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
