package com.warehouseapi;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class WarehouseAPI extends Application {

    @Override
    public Set<Class <?>> getClasses() {
        Set<Class <?>> classes = new HashSet<>();
        classes.add(NotFoundExceptionMapper.class);
        return classes;
    }
}
