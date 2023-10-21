package com.warehouseapi.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraintvalidation.SupportedValidationTarget;
import jakarta.validation.constraintvalidation.ValidationTarget;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
@Constraint(
        validatedBy = {}
)
@SupportedValidationTarget({ValidationTarget.ANNOTATED_ELEMENT})
@Target({FIELD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Max(10)
@PositiveOrZero
@ReportAsSingleViolation
public @interface RatingValidation {
    String message() default ("Rating has to be beteween 0 and 10 inclusive");
    Class<?> [] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
