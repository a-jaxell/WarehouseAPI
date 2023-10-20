package com.warehouseapi.entity;

public enum ProductCategory {
    CHEF_KNIVES("chef_knives"),
    WHISKS("whisks"),
    TONGS("tongs"),
    SPATULAS("spatulas"),
    MANDOLINS("mandolins"),
    UTENSILS("utensils"),
    UNDEFINED("undefined");

    private String value;

    private ProductCategory(String value){
        this.value = value;
    }
    public String getValue(){
        return value;
    }
}
