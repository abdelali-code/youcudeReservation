package com.youcode.reservation.CustomValidation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ConfirmPasswordValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfirmPasswordInt {
    String message() default "password does not match";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
