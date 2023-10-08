package com.warehouse.service;

import com.warehouse.FakeProducts;
import com.warehouse.entities.Product;
import com.warehouse.entities.ProductCategory;
import com.warehouse.entities.ProductRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.MapAssert.assertThatMap;

class WarehouseTest {
    private Product product;
    private Warehouse warehouse;
    private Warehouse filledWarehouse;
    private LocalDateTime fixedDate;
    private LocalDateTime fixedDate2;
    private LocalDateTime fixedDate3;

    @BeforeEach
    void setUp() {
        Clock clock = Clock.fixed(Instant.ofEpochSecond(1631510400, 1000L), ZoneId.of("UTC"));
        Clock clock2 = Clock.fixed(Instant.ofEpochSecond(1633504400, 1000L), ZoneId.of("UTC"));
        Clock clock3 = Clock.fixed(Instant.ofEpochSecond(1637524400, 1000L), ZoneId.of("UTC"));

        fixedDate = LocalDateTime.now(clock);
        fixedDate2 = LocalDateTime.now(clock2);
        fixedDate3 = LocalDateTime.now(clock3);
        product = new Product("Microplane", ProductCategory.UTENSILS, 5);
        warehouse = new Warehouse();
        filledWarehouse = new Warehouse(new FakeProducts().list());
    }

    @Test
    void shouldAddNewProductToWarehouse() {
        assertThat(warehouse.getProducts()).isNullOrEmpty();
        warehouse.addNewProduct(product);
        assertThat(warehouse.getProducts()).isNotEmpty();
    }

    @Test
    void shouldGetAllProducts() {
        warehouse.addNewProduct(product);
        List<ProductRecord> productList = warehouse.getProducts();
        assertThat(productList)
                .isInstanceOf(List.class)
                .hasSize(1)
                .extracting(ProductRecord::id)
                .contains(product.getId());

    }

    @Test
    void testGettingProductWithId_ShouldReturnProductRecord() {
        warehouse.addNewProduct(product);
        UUID id = product.getId();
        Optional<ProductRecord> actual = warehouse.getProduct(id);
        assertThat(product)
                .isNotEqualTo(actual.get())
                .extracting(Product::getId)
                .isEqualTo(actual.get().id());
    }

    @Test
    void testGettingProductWithWrongId_ShouldThrowException() {
        UUID id = product.getId();
        UUID wrongId = UUID.randomUUID();

        assertThat(id)
                .isInstanceOf(UUID.class)
                .extracting(UUID::getClass)
                .isEqualTo(wrongId.getClass())
                .isNotEqualTo(wrongId);

        assertThatThrownBy(() -> warehouse.getProduct(wrongId))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testModifyingProduct_ShouldSucceed() throws InterruptedException {
        //Calling alternate constructor for ability to set a fixed date.

        UUID id = UUID.randomUUID();
        Product product = new Product(id, "Cheese_Grater", ProductCategory.UTENSILS, 5, LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        Product resultProduct = new Product(id, "Cheese_Slicer", ProductCategory.CHEF_KNIVES, 1, LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        ProductRecord expected = ProductRecord.returnRecord(resultProduct);

        warehouse.addNewProduct(product);
        warehouse.modifyProduct(id, "Cheese_Slicer", ProductCategory.CHEF_KNIVES, 1);

        Optional<ProductRecord> modifiedProduct = warehouse.getProduct(id);

        assertThat(modifiedProduct.get())
                .isNotNull()
                .isEqualTo(expected);

    }

    @Test
    void testSortingProductsAlphabetically_ShouldSucceed() {

        List<ProductRecord> expected = filledWarehouse.sortByName();

        List<ProductRecord> actual = filledWarehouse.getProducts().stream()
                .sorted(Comparator.comparing(ProductRecord::name))
                .toList();
        List<ProductRecord> notActual = filledWarehouse.getProducts();

        assertThat(expected)
                .isEqualTo(actual)
                .isNotEqualTo(notActual);
    }

    @Test
    void shouldReturnAllProductsCreatedAfterACertainDate() {

        filledWarehouse.addNewProduct(new Product("Sauce_Stirrer", ProductCategory.WHISKS, 1, fixedDate3));
        filledWarehouse.addNewProduct(new Product("Cheese_Grater", ProductCategory.UTENSILS, 5, fixedDate2));
        filledWarehouse.addNewProduct(new Product("Corn_Grabber", ProductCategory.TONGS, 2, fixedDate));

        List<ProductRecord> actual = filledWarehouse.getProductsCreatedAfter(fixedDate2);

        assertThat(actual).allMatch(product1 -> product1.dateCreated().isAfter(fixedDate2));
    }

    @Test
    void shouldReturnAllModifiedProducts() {

        Predicate<ProductRecord> isModified = productRecord -> !productRecord.dateCreated().isEqual(productRecord.dateModified());

        warehouse.addNewProduct(new Product("Sauce_Stirrer", ProductCategory.WHISKS, 1, fixedDate3));
        warehouse.addNewProduct(new Product("Cheese_Grater", ProductCategory.UTENSILS, 5, fixedDate2));
        warehouse.addNewProduct(new Product("Corn_Grabber", ProductCategory.TONGS, 2, fixedDate));

        UUID productOneId = warehouse.getProducts().get(0).id();

        warehouse.modifyProduct(productOneId, "Sauce_swirler", ProductCategory.WHISKS, 8);
        List<ProductRecord> actual = warehouse.getProductsModified();

        assertThat(warehouse.getProducts()).contains(warehouse.getProduct(productOneId).get());
        assertThat(actual).allMatch(isModified);
    }

    @Test
    void shouldReturnAllCategoriesWithMinimumOneProduct() {

        List<ProductCategory> expected = filledWarehouse.getPopulatedCategories();
        assertThat(ProductCategory.WHISKS).isNotIn(expected);
    }

    @Test
    void shouldReturnAmountOfProductsForGivenCategory() {

        Map<ProductCategory, Long> expected = filledWarehouse.getProductsPerCategory();
        assertThat(expected)
                .doesNotContainEntry(ProductCategory.WHISKS, 0L)
                .containsEntry(ProductCategory.UTENSILS, 3L);

    }

    @Test
    void shouldReturnAmountOfProducts_WhenGivenACategory() {
        String category = "WHISKS";
        String category2 = "uTenSILS";
        String category3 = "tongs";
        Map<ProductCategory, Long> expected = filledWarehouse.getProductsPerCategory(category);
        Map<ProductCategory, Long> expected2 = filledWarehouse.getProductsPerCategory(category2);
        Map<ProductCategory, Long> expected3 = filledWarehouse.getProductsPerCategory(category3);

        assertThat(expected).doesNotContainEntry(ProductCategory.WHISKS, 0L);
        assertThat(expected2).containsEntry(ProductCategory.UTENSILS, 3L);
        assertThat(expected3).containsEntry(ProductCategory.TONGS, 1L);
    }

    @Test
    void shouldReturnMapWithFirstLetterAsKeyAndNumberOfProducts_whenGivenListOfProducts() {

        Map<String, Long> actual = filledWarehouse.numberPerFirstLetter();

        assertThatMap(actual)
                .isNotNull()
                .isNotEmpty()
                .containsEntry("S", 4L);
    }

    @Test
    void shouldReturnTrendingNewProducts_whenGivenListOfProducts() {
        Product hotProduct = new Product("New_Hot_product", ProductCategory.UTENSILS, 10);
        Product oldHotProduct = new Product("Old_Hot_product", ProductCategory.TONGS, 10, fixedDate);
        filledWarehouse.addNewProduct(hotProduct);
        filledWarehouse.addNewProduct(oldHotProduct);

        List<ProductRecord> actual = filledWarehouse.getNewTrendingProducts();

        assertThat(actual)
                .isNotNull()
                .contains(ProductRecord.returnRecord(hotProduct))
                .doesNotContain(ProductRecord.returnRecord(oldHotProduct));
    }

    @Test
    void shouldSortDatesByMostRecent() {

        LocalDateTime today = fixedDate;

        Product newProduct = new Product("New_product", ProductCategory.UTENSILS, 10, today.minusDays(5));
        Product newProduct2 = new Product("New_product2", ProductCategory.TONGS, 10, today.minusDays(30));
        Product newProduct3 = new Product("New_product3", ProductCategory.WHISKS, 10, today.minusDays(1));
        Product newProduct4 = new Product("New_product4", ProductCategory.MANDOLINS, 9, today.minusDays(2));
        filledWarehouse.addNewProduct(newProduct);
        filledWarehouse.addNewProduct(newProduct2);
        filledWarehouse.addNewProduct(newProduct3);
        filledWarehouse.addNewProduct(newProduct4);

        List<ProductRecord> actual = filledWarehouse.getNewTrendingProducts(fixedDate);
        Comparator<ProductRecord> comparator = Comparator.comparing(ProductRecord::dateCreated, Comparator.reverseOrder());

        assertThat(actual)
                .isNotNull()
                .contains(ProductRecord.returnRecord(newProduct))
                .doesNotContain(ProductRecord.returnRecord(newProduct2))
                .contains(ProductRecord.returnRecord(newProduct3))
                .doesNotContain(ProductRecord.returnRecord(newProduct4))
                .isSortedAccordingTo(comparator);
    }
}
