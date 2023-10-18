package com.warehouseapi.resource;

import com.warehouse.entities.Product;
import com.warehouse.entities.ProductCategory;
import com.warehouse.entities.ProductRecord;
import com.warehouseapi.interceptor.LogMethodCalls;
import com.warehouseapi.service.WarehouseService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

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
            throw new NotFoundException("No products available");
        }
        return Response.ok(result, MediaType.APPLICATION_JSON_TYPE).build();
    }
    @POST
    public  Response addNewProduct(
            @Context UriInfo uri,
            @QueryParam("name") String name,
            @QueryParam("productCategory") String productCategory,
            @QueryParam("rating") int rating){

        ProductRecord response;
        if(name == null || name.isEmpty()){
            throw new BadRequestException(
                    Response.status(Response.Status.BAD_REQUEST)
                            .entity("name parameter is required")
                            .build()
            );
        }
        if(productCategory == null || productCategory.isEmpty()){
            throw new BadRequestException(
                    Response.status(Response.Status.BAD_REQUEST)
                            .entity("productCategory is required ")
                            .build()
            );
        }
        try{
            Product newProduct = new Product(name, Enum.valueOf(ProductCategory.class, productCategory.toUpperCase()), rating);
            warehouseService.addNewProduct(newProduct);
            response = ProductRecord.returnRecord(newProduct);
        } catch (Exception e){
            throw new InternalServerErrorException();
        }
        return Response.status(Response.Status.CREATED).entity(response).type(MediaType.APPLICATION_JSON).build();
    }
    @GET
    @Path("/category")
    public Response getCategories() {
        List result;
        try{
            result = stream(ProductCategory.values()).collect(Collectors.toList());
        } catch (Exception e){
            throw new NotFoundException(e);
        }
        return Response.ok(result, MediaType.APPLICATION_JSON_TYPE).build();
    }
    @GET
    @Path("/category/{category}")
    public Response getProductsInCategory(@PathParam("category") String category) {
        List<ProductRecord> result;

        result = (List<ProductRecord>) warehouseService.getProductsPerCategory(category);
        if(result == null || result.isEmpty()){
            throw new NotFoundException("There is no such category");
        }

        return Response.ok(result, MediaType.APPLICATION_JSON_TYPE).build();
    }
    @GET
    @Path("/{id}")
    public Response getProductFromId(@PathParam("id") String id) {
        Optional<ProductRecord> result;
            result = warehouseService.getProduct(UUID.fromString(id));
        if(id == null || id.isEmpty()){
            throw new NotFoundException();
        }
        return Response.ok(result, MediaType.APPLICATION_JSON_TYPE).build();
    }
}