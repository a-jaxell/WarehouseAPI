package com.warehouse;

import com.warehouse.entities.Product;
import com.warehouse.entities.ProductCategory;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

public class FakeProducts {

    public CopyOnWriteArrayList<Product> fakeProducts = new CopyOnWriteArrayList<>(Arrays.asList(
            new Product("Microplane", ProductCategory.UTENSILS, 4),
            new Product("Global_20_cm", ProductCategory.CHEF_KNIVES, 2),
            new Product("Slickepott_L", ProductCategory.SPATULAS, 4),
            new Product("Slickepott_S", ProductCategory.SPATULAS, 6),
            new Product("ClickClack", ProductCategory.TONGS, 9),
            new Product("Wusthof_27_cm", ProductCategory.CHEF_KNIVES, 8),
            // 7 & 8 are identical to serve as check for duplicates.
            new Product("Sieve", ProductCategory.UTENSILS, 3),
            new Product("Sieve", ProductCategory.UTENSILS, 3),
            new Product("FingerSnipper4K", ProductCategory.MANDOLINS, 8)
    ));

    public CopyOnWriteArrayList<Product> list() {
        return fakeProducts;
    }

}
