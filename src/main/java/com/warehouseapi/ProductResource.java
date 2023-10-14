package com.warehouseapi;

import com.warehouse.entities.Product;
import com.warehouse.entities.ProductCategory;
import com.warehouse.entities.ProductRecord;
import com.warehouseapi.interceptor.Logging;
import com.warehouseapi.service.WarehouseService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Path("/products")
@Logging
public class ProductResource {
    Product product = new Product("name", ProductCategory.UTENSILS, 2);
    private static final Logger logger = LoggerFactory.getLogger(ProductResource.class);

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
        logger.info("Connection to : "+ uri.getAbsolutePath());
        if(name == null || name.equals("")){
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST)
                            .entity("name parameter is required")
                            .build()
            );
        }

        try{
            Product newProduct = new Product(name, Enum.valueOf(ProductCategory.class, productCategory.toUpperCase()), rating);
            response = ProductRecord.returnRecord(newProduct);
                    //warehouseService.addNewProduct(newProduct);
        } catch (Exception e) {
            logger.error("Error: " + e);
            // This need to be fixed so that this either sends the exception status or
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Error: "+ e.getMessage()).build();
        }
        logger.info("Send response: " + response);
        return Response.ok(response, MediaType.APPLICATION_JSON).build();
    }

}