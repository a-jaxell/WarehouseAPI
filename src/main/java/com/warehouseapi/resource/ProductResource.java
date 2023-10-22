package com.warehouseapi.resource;

import com.warehouseapi.entity.Product;
import com.warehouseapi.entity.ProductRecord;
import com.warehouseapi.exception.CategoryNotFoundException;
import com.warehouseapi.exception.ProductNotFoundException;
import com.warehouseapi.exception.ProductsNotFoundException;
import com.warehouseapi.interceptor.LogMethodCalls;
import com.warehouseapi.service.WarehouseService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Path("/products")
@LogMethodCalls
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {
    private WarehouseService warehouseService;
    public ProductResource(){}
    @Inject
    public ProductResource(WarehouseService warehouseService){ this.warehouseService = warehouseService; }
    @GET
    public Response getProducts() {
        List<ProductRecord> result;

        result = warehouseService.getProducts();
        if(result == null || result.isEmpty()){
            throw new ProductsNotFoundException();
        }
        return Response.ok(result, MediaType.APPLICATION_JSON_TYPE).build();
    }
    @POST
    public  Response addNewProduct(@Valid Product product){

        ProductRecord response;
        try{
            warehouseService.addNewProduct(product);
            response = ProductRecord.returnRecord(product);
        } catch (Exception e){
            throw new InternalServerErrorException();
        }
        return Response.status(Response.Status.CREATED).entity(response).type(MediaType.APPLICATION_JSON).build();
    }
    @GET
    @Path("/category")
    public Response getCategories() {
        List<String> result = warehouseService.getCategories();
        if(result == null || result.isEmpty()){
            throw new CategoryNotFoundException();
        }
        return Response.ok(result, MediaType.APPLICATION_JSON_TYPE).build();
    }
    @GET
    @Path("/category/{category}")
    public Response getProductsInCategory(@Valid @PathParam("category") String category) {
        List<ProductRecord> result;

        result = (List<ProductRecord>) warehouseService.getProductsPerCategory(category);
        if(result == null || result.isEmpty()){
            throw new ProductNotFoundException();
        }

        return Response.ok(result, MediaType.APPLICATION_JSON_TYPE).build();
    }
    @GET
    @Path("/{id}")
    public Response getProductFromId(@PathParam("id") String id) {
        Optional<ProductRecord> result;
            result = warehouseService.getProduct(UUID.fromString(id));
        if(result.isEmpty()){
            throw new ProductNotFoundException();
        }
        return Response.ok(result, MediaType.APPLICATION_JSON_TYPE).build();
    }
}