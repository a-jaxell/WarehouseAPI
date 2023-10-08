package com.warehouseapi.service;

import com.warehouse.entities.ProductCategory;
import com.warehouse.entities.ProductRecord;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Map;

@ApplicationScoped
public class WarehouseService implements WarehouseInterface{

    @Override
    public List<ProductRecord> getProducts() {
        return null;
    }

    @Override
    public Map<ProductCategory, Long> getProductsPerCategory(String category) {
        return null;
    }
}
