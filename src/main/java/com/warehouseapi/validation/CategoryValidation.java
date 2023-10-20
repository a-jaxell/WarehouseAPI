package com.warehouseapi.validation;

import com.warehouse.entities.ProductCategory;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.*;

@Constraint(validatedBy = CategoryConstraint.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@NotNull
public @interface CategoryValidation {
    Class<? extends Enum<?>>[] category() default ProductCategory.class;
    String message() default ("Invalid product category");
    Class<?> [] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
