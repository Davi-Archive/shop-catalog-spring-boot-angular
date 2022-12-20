package io.davi.catalog.tests;

import io.davi.catalog.dto.ProductDTO;
import io.davi.catalog.entities.Category;
import io.davi.catalog.entities.Product;

import java.time.Instant;

public class Factory {
    public static Product createProduct() {
        Product product =
                new Product(1L,
                        "Phone",
                        "Good Phone",
                        800.0,
                        "https://img.com/img.png",
                        Instant.parse("2020-10-20T03:00:00Z"));
        product.getCategories()
                .add(new Category(2l, "Eletronics"));
        return product;
    }

    public static ProductDTO createProductDTO() {
        Product product = createProduct();
        return new ProductDTO(product, product.getCategories());
    }

}
