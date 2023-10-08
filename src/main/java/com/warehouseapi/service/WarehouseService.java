package com.warehouseapi.service;

import com.warehouse.entities.ProductRecord;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class WarehouseService implements WarehouseInterface{

    @Override
    public List<ProductRecord> getProducts() {
        return null;
    }
}
