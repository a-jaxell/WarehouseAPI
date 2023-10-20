package com.warehouseapi.validation;

import com.warehouseapi.resource.ProductResource;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    private final static Logger logger = LoggerFactory.getLogger(ProductResource.class);
    @Override
    public Response toResponse(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();

        for(ConstraintViolation<?> violation : violations){
            logger.error("TEST");
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
