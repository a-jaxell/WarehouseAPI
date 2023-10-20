package com.warehouseapi.service;

import com.warehouseapi.entity.Product;
import com.warehouseapi.entity.ProductCategory;
import com.warehouseapi.entity.ProductRecord;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface WarehouseService {
    List<ProductRecord> getProducts();
    Optional<ProductRecord> getProduct(UUID id);
    Map<ProductCategory, Long> getProductsPerCategory(String category);
    ProductRecord addNewProduct(Product product);
    List<String> getCategories();
}
