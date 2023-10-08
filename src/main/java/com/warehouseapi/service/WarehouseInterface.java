package com.warehouseapi.service;

import com.warehouse.entities.ProductRecord;

import java.util.List;

public interface WarehouseInterface {
    List<ProductRecord> getProducts();
}
