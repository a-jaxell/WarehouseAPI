package com.warehouseapi.service;

import com.warehouse.entities.ProductCategory;
import com.warehouse.entities.ProductRecord;

import java.util.List;
import java.util.Map;

public interface WarehouseInterface {
    List<ProductRecord> getProducts();
    Map<ProductCategory, Long> getProductsPerCategory(String category);
}
