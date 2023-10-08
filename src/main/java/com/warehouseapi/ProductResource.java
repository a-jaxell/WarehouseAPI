package com.warehouseapi;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/products")
public class ProductResource {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }
}