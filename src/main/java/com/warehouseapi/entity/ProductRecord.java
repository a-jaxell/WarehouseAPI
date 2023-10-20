package com.warehouseapi.entity;

import com.warehouseapi.validation.CategoryValidation;
import com.warehouseapi.validation.NameValidation;
import com.warehouseapi.validation.RatingValidation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProductRecord(@NotNull UUID id, @NameValidation String name, @CategoryValidation ProductCategory category, @RatingValidation int rating, LocalDateTime dateCreated,
                            LocalDateTime dateModified) {

    public static ProductRecord returnRecord(@Valid Product product) {
        return new ProductRecord(product.getId(), product.getName(), product.getCategory(), product.getRating(), product.getDateCreated(), product.getDateLastModified());
    }
}
