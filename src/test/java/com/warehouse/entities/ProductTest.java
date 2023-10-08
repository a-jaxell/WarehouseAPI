package com.warehouse.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.warehouse.entities.ProductCategory.CHEF_KNIVES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest {
    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product("Santoku 17cm", CHEF_KNIVES, 5);
    }

    @Test
    @DisplayName("Getter for name should work")
    void testNameGetter() {
        assertEquals("Santoku 17cm", product.getName());
    }

    @Test
    @DisplayName("The rating getter should return the rating in product.rating")
    void testRatingGetter() {
        //arrange

        // act
        int actual = product.getRating();

        //assert
        assertEquals(5, actual);
    }

    @Test
    void testRatingBeingWithinRangeOfZeroToTen() {
        new Product("Test", CHEF_KNIVES, 11);
        int actual = product.getRating();
        assertThat(actual).isGreaterThanOrEqualTo(0);
        assertThat(actual).isLessThanOrEqualTo(10);
    }
}
