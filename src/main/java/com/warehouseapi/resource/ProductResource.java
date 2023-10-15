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

@Path("/products")
@LogMethodCalls
public class ProductResource {
    private WarehouseService warehouseService;
    public ProductResource(){}
    @Inject
    public ProductResource(WarehouseService warehouseService){ this.warehouseService = warehouseService; }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts() {
        List<ProductRecord> result;

        result = warehouseService.getProducts();
        if(result.isEmpty()){
            throw new NotFoundException("No products available");
        }
        return Response.ok(result, MediaType.APPLICATION_JSON_TYPE).build();
    }
    @GET
    @Path("/category/{category}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsInCategory(@PathParam("category") String category) {
        List<ProductRecord> result;

        result = (List<ProductRecord>) warehouseService.getProductsPerCategory(category);
        if(result.isEmpty()){
            throw new NotFoundException("Unable to find any matching products");
        }

        return Response.ok(result, MediaType.APPLICATION_JSON_TYPE).build();
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductFromId(@PathParam("id") String id) {
        Optional<ProductRecord> result;
            result = warehouseService.getProduct(UUID.fromString(id));
        if(result.isEmpty()){
            throw new NotFoundException("Did not find any matching products");
        }
        return Response.ok(result, MediaType.APPLICATION_JSON_TYPE).build();
    }
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
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
        Product newProduct = new Product(name, Enum.valueOf(ProductCategory.class, productCategory.toUpperCase()), rating);
        response = ProductRecord.returnRecord(newProduct);
        try{
            warehouseService.addNewProduct(newProduct);
        } catch (Exception e){
            throw new InternalServerErrorException();
        }
        return Response.status(Response.Status.CREATED).entity(response).type(MediaType.APPLICATION_JSON).build();
    }

}