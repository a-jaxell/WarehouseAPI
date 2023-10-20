package com.warehouseapi.validation;

import com.warehouse.entities.ProductCategory;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CategoryConstraint implements ConstraintValidator<CategoryValidation, String> {
    @Override
    public void initialize(CategoryValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            ProductCategory.valueOf(s.toUpperCase());
            return true;
        } catch (IllegalArgumentException e){
            return false;
        }
    }
}
