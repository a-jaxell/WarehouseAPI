package com.warehouseapi.exception;

import com.warehouseapi.interceptor.LogMethodCalls;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.Collections;
import java.util.Map;

@Provider
@LogMethodCalls
public class StatusExceptionMapper implements ExceptionMapper<WebApplicationException> {
    @Override
    public Response toResponse(WebApplicationException e) {
        int status = e.getResponse().getStatus();
        String errorMessage = e.getMessage();
        Map<String, String> error = Collections.singletonMap("error", errorMessage);
        return Response.status(status).entity(error).build();
    }
}
