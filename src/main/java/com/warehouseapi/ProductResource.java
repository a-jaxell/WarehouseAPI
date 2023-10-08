package com.warehouseapi;

import com.warehouseapi.service.WarehouseService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/products")
public class ProductResource {

    private WarehouseService warehouseService;
    public ProductResource(){}
    @Inject
    public ProductResource(WarehouseService warehouseService){ this.warehouseService = warehouseService; };
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }
}