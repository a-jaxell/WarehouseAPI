package com.warehouseapi.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.warehouseapi.exception.CategoryNotFoundException;

public enum ProductCategory {
    CHEF_KNIVES("chef_knives"),
    WHISKS("whisks"),
    TONGS("tongs"),
    SPATULAS("spatulas"),
    MANDOLINS("mandolins"),
    UTENSILS("utensils"),
    UNDEFINED("undefined");

    @JsonValue
    private String value;

    @JsonCreator
    public  static ProductCategory fromValue(String value){
        for (ProductCategory category:values()){
            if(category.value.equalsIgnoreCase(value)) {
                return category;
            }
        }
        throw new CategoryNotFoundException();
    }
    private ProductCategory(String value){
        this.value = value;
    }
    public String getValue(){
        return value;
    }
}
