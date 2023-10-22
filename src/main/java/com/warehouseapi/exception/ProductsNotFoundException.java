package com.warehouseapi.exception;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class ProductsNotFoundException extends WebApplicationException {
    public ProductsNotFoundException(){
        super("Products not found", Response.Status.NOT_FOUND);
    }
}
