package io.davi.catalog.services;

import io.davi.catalog.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ProductServicesTests {
    @InjectMocks
    private ProductService service;
    @Mock
    private ProductRepository repository;

    private Long existingId;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
    }

    @Test
    public void deleteShouldDoNothingWhenIdExists() {
        Assertions.assertDoesNotThrow(() -> {
            service.delete(existingId);
        });

        Mockito.verify(repository,Mockito.times(1)).deleteById(existingId);
    }
}
