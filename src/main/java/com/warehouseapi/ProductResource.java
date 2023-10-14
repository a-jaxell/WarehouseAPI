package com.warehouseapi;

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

        try{
            Product newProduct = new Product(name, Enum.valueOf(ProductCategory.class, productCategory.toUpperCase()), rating);
            response = ProductRecord.returnRecord(newProduct);
                    //warehouseService.addNewProduct(newProduct);
        } catch (Exception e) {
            // This need to be fixed so that this either sends the exception status or
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error: "+ e.getMessage()).build();
        }
        return Response.ok(response, MediaType.APPLICATION_JSON).build();
    }

}