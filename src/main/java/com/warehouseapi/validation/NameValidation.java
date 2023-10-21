package com.warehouseapi.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.NotBlank;
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
@NotBlank
@ReportAsSingleViolation
public @interface NameValidation {
    String message() default ("Name cannot be blank");
    Class<?> [] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
