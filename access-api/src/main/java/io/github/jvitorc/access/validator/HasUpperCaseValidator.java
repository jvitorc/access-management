package io.github.jvitorc.access.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class HasUpperCaseValidator implements ConstraintValidator<HasUpperCase, String> {

    private long min;

    @Override
    public void initialize(HasUpperCase constraintAnnotation) {
        this.min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }

        long count = value.chars().filter(Character::isUpperCase).count();
        return count >= min;
    }
}
