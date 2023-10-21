package com.warehouseapi.validation;

import com.warehouseapi.entity.ProductCategory;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CategoryConstraint implements ConstraintValidator<CategoryValidation, ProductCategory> {

    @Override
    public void initialize(CategoryValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ProductCategory productCategory, ConstraintValidatorContext constraintValidatorContext) {
        if(productCategory == null){
            return false;
        }
        String categoryValue = productCategory.getValue();

        for(ProductCategory category : ProductCategory.values()){
            if(category.getValue().equalsIgnoreCase(categoryValue)){
                return true;
            };
        }
    return false;
    }
}
