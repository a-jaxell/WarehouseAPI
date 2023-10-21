package com.warehouseapi.validation;

import com.warehouseapi.entity.ResponseError;
import com.warehouseapi.resource.ProductResource;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    private final static Logger logger = LoggerFactory.getLogger(ProductResource.class);
    @Override
    public Response toResponse(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        List<ResponseError> responseErrors = new ArrayList<>();

        for(ConstraintViolation<?> violation : violations){
            String message = violation.getMessage();
            String field = violation.getPropertyPath().toString();
            responseErrors.add(new ResponseError(message, field));
            logger.error("Validation error for: {} - {}", field, message);
        }
        Map<String, List<ResponseError>> response = new HashMap<>();
        response.put("errors",responseErrors);
        return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
    }
}
