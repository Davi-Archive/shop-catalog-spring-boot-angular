package io.davi.catalog.repositories;

import io.davi.catalog.entities.Category;
import io.davi.catalog.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
