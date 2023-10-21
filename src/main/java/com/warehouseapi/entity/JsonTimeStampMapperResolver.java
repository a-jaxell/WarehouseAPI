package com.warehouseapi.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.ws.rs.ext.ContextResolver;
import jakarta.ws.rs.ext.Provider;

@Provider
public class JsonTimeStampMapperResolver implements ContextResolver<ObjectMapper> {

    private final ObjectMapper mapper;

    public JsonTimeStampMapperResolver(){
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
    }
    @Override
    public ObjectMapper getContext(Class<?> aClass) {
        return mapper;
    }
}
