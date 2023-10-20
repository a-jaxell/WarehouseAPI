package com.warehouseapi.service;

import com.warehouseapi.entity.Product;
import com.warehouseapi.entity.ProductCategory;
import com.warehouseapi.entity.ProductRecord;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class WarehouseServiceApi implements WarehouseService {

    @Override
    public List<ProductRecord> getProducts() {
        return null;
    }

    @Override
    public Optional<ProductRecord> getProduct(UUID id) {
        return Optional.empty();
    }

    @Override
    public Map<ProductCategory, Long> getProductsPerCategory(String category) {
        return null;
    }

    @Override
    public ProductRecord addNewProduct(Product product) {
        return null;
    }

    @Override
    public List<String> getCategories() {
        return null;
    }
}
