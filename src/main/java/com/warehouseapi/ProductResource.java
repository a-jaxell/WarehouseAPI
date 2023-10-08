package com.warehouseapi;

import com.warehouse.entities.Product;
import com.warehouse.entities.ProductCategory;
import com.warehouse.entities.ProductRecord;
import com.warehouseapi.service.WarehouseService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/products")
public class ProductResource {
    Product product = new Product("name", ProductCategory.UTENSILS, 2);

    private WarehouseService warehouseService;
    public ProductResource(){}
    @Inject
    public ProductResource(WarehouseService warehouseService){ this.warehouseService = warehouseService; };
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts() {
        List<ProductRecord> result;
        try{
            result = warehouseService.getProducts();
        }catch (Exception e) {
            return Response.serverError().entity("Error: "+ e).build();
        }
        return Response.ok(result, MediaType.APPLICATION_JSON_TYPE).build();
    }
}