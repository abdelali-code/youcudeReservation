package com.youcode.reservation.CustomValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConfirmPasswordValidator implements
        ConstraintValidator<ConfirmPasswordInt, String> {

    @Override
    public void initialize(ConfirmPasswordInt constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
