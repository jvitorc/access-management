package io.github.jvitorc.access.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;


public class HasSpecialCharacterValidator implements ConstraintValidator<HasSpecialCharacter, String> {

    private long min;
    private String regex;

    @Override
    public void initialize(HasSpecialCharacter constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.regex = constraintAnnotation.regex();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }

        long count = value.chars()
                .mapToObj(Character::toChars)
                .filter(c -> Pattern.matches(regex, String.valueOf(c)))
                .count();

        return count >= min;
    }


}
