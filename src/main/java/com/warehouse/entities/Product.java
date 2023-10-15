package com.warehouse.entities;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class Product {
    @NotNull
    private final UUID id;

    @PastOrPresent
    private final LocalDateTime dateCreated;
    @Max(10)
    @PositiveOrZero
    private int rating;
    @NotBlank
    private String name;
    @NotNull
    private ProductCategory category;
    @FutureOrPresent
    private LocalDateTime dateLastModified;


    public Product(String name, ProductCategory category, int rating) {
        LocalDateTime date = LocalDateTime.now();
        this.id = UUID.randomUUID();
        this.name = name;
        this.rating = rating;
        this.category = category;
        this.dateCreated = date;
        this.dateLastModified = date;
    }

    // Only for testing
    public Product(String name, ProductCategory category, int rating, LocalDateTime dateCreated) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.rating = rating;
        this.category = category;
        this.dateCreated = dateCreated;
        this.dateLastModified = dateCreated;
    }

    // Only for testing
    public Product(UUID id, String name, ProductCategory category, int rating, LocalDateTime dateCreated) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.category = category;
        this.dateCreated = dateCreated;
        this.dateLastModified = dateCreated;
    }

    public Product(Product product) {
        this.id = product.id;
        this.name = product.name;
        this.rating = product.rating;
        this.category = product.category;
        this.dateCreated = product.dateCreated;
        this.dateLastModified = product.dateCreated;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public UUID getId() {
        return id;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory newCategory) {
        this.category = newCategory;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setModifiedDate() {
        this.dateLastModified = LocalDateTime.now();
    }

    public LocalDateTime getDateLastModified() {
        return dateLastModified;
    }

    public void update(String newName, ProductCategory newCategory, int newRating) {
        name = newName;
        category = newCategory;
        rating = newRating;
        dateLastModified = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    }

    public boolean isModified() {
        return !dateCreated.isEqual(dateLastModified);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", dateCreated=" + dateCreated +
                ", rating=" + rating +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", dateLastModified=" + dateLastModified +
                '}';
    }
}
