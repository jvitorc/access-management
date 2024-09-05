package io.github.jvitorc.access.validator;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.PARAMETER})
@Documented
public @interface ValidatorService {
    Class<?>[] groups() default {};
}

