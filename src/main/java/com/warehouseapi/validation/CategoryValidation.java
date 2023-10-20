package com.warehouseapi.validation;

import com.warehouseapi.entity.ProductCategory;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Constraint(validatedBy = CategoryConstraint.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CategoryValidation {
    Class<? extends Enum<?>>[] category() default ProductCategory.class;
    String message() default ("Invalid product category");
    Class<?> [] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
