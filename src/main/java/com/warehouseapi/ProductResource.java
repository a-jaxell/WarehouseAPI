package com.warehouseapi;

import com.warehouse.entities.Product;
import com.warehouse.entities.ProductCategory;
import com.warehouse.entities.ProductRecord;
import com.warehouseapi.service.WarehouseService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Path("/products")
public class ProductResource {
    Product product = new Product("name", ProductCategory.UTENSILS, 2);

    private WarehouseService warehouseService;
    public ProductResource(){}
    @Inject
    public ProductResource(WarehouseService warehouseService){ this.warehouseService = warehouseService; }
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
    @GET
    @Path("/{category}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsInCategory(@PathParam("category") String category) {
        List<ProductRecord> result;
        try{
            result = (List<ProductRecord>) warehouseService.getProductsPerCategory(category);
        }catch (Exception e) {
            return Response.serverError().entity("Error: "+ e).build();
        }
        return Response.ok(result, MediaType.APPLICATION_JSON_TYPE).build();
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductFromId(@PathParam("id") String id) {
        Optional<ProductRecord> result;
        try{
            result = warehouseService.getProduct(UUID.fromString(id));
        }catch (Exception e) {
            return Response.serverError().entity("Error: "+ e).build();
        }
        return Response.ok(result, MediaType.APPLICATION_JSON_TYPE).build();
    }
}