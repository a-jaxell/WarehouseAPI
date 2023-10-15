package com.warehouse.entities;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProductRecord(@NotNull UUID id, @NotBlank String name, @NotNull ProductCategory category, @PositiveOrZero @Max(10) int rating, @PastOrPresent LocalDateTime dateCreated,
                            @FutureOrPresent LocalDateTime dateModified) {

    public static ProductRecord returnRecord(@Valid Product product) {
        return new ProductRecord(product.getId(), product.getName(), product.getCategory(), product.getRating(), product.getDateCreated(), product.getDateLastModified());
    }
}
