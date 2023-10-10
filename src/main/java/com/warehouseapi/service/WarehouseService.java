package com.warehouseapi.service;

import com.warehouse.entities.Product;
import com.warehouse.entities.ProductCategory;
import com.warehouse.entities.ProductRecord;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface WarehouseService {
    List<ProductRecord> getProducts();
    Optional<ProductRecord> getProduct(UUID id);
    Map<ProductCategory, Long> getProductsPerCategory(String category);
    ProductRecord addNewProduct(Product product);
}
