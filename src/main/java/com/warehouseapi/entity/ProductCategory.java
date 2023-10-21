package com.warehouseapi.entity;

import com.fasterxml.jackson.annotation.JsonValue;

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

    private ProductCategory(String value){
        this.value = value;
    }
    public String getValue(){
        return value;
    }
}
