package io.github.jvitorc.access.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class HasDigitValidator implements ConstraintValidator<HasDigit, String> {

    private long min;

    @Override
    public void initialize(HasDigit constraintAnnotation) {
        this.min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }

        long count = value.chars().filter(Character::isDigit).count();
        return count >= min;
    }
}
