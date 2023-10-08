package com.warehouse.entities;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProductRecord(UUID id, String name, ProductCategory category, int rating, LocalDateTime dateCreated,
                            LocalDateTime dateModified) {

    public static ProductRecord returnRecord(Product product) {
        return new ProductRecord(product.getId(), product.getName(), product.getCategory(), product.getRating(), product.getDateCreated(), product.getDateLastModified());
    }
}
