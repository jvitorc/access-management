package io.github.jvitorc.access.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class HasLowerCaseValidator implements ConstraintValidator<HasLowerCase, String> {

    private long min;

    @Override
    public void initialize(HasLowerCase constraintAnnotation) {
        this.min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }

        long count = value.chars().filter(Character::isLowerCase).count();
        return count >= min;
    }
}
