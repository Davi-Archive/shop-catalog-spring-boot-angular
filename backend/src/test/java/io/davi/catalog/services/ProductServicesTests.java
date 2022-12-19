package io.davi.catalog.services;

import io.davi.catalog.entities.Product;
import io.davi.catalog.repositories.ProductRepository;
import io.davi.catalog.services.exceptions.DatabaseException;
import io.davi.catalog.services.exceptions.ResourceNotFoundException;
import io.davi.catalog.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.jsf.FacesContextUtils;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class ProductServicesTests {
    @InjectMocks
    private ProductService service;
    @Mock
    private ProductRepository repository;

    private Long existingId;
    private Long nonExistingId;
    private Long dependentId;
    private PageImpl<Product> page;
    private Product product;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
        nonExistingId = 1000L;
        dependentId = 4L;
        product = Factory.createProduct();
        page = new PageImpl<>(List.of(product));

        Mockito.when(repository.findAll((Pageable) ArgumentMatchers.any()))
                .thenReturn(page);

        Mockito.when(repository.save(ArgumentMatchers.any()))
                .thenReturn(product);

        Mockito.when(repository.findById(existingId))
                .thenReturn(Optional.of(product));

        Mockito.when(repository.findById(nonExistingId))
                .thenReturn(Optional.empty());


        Mockito.doNothing().when(repository).deleteById(existingId);
        Mockito.doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(nonExistingId);
        Mockito.doThrow(DataIntegrityViolationException.class).when(repository).deleteById(dependentId);
    }

    @Test
    public void deleteShouldDoNothingWhenIdExists() {
        Assertions.assertDoesNotThrow(() -> {
            service.delete(existingId);
        });

        Mockito.verify(repository, Mockito.times(1)).deleteById(existingId);
    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.delete(nonExistingId);
        });

        Mockito.verify(repository, Mockito.times(1)).deleteById(nonExistingId);
    }


    @Test
    public void deleteShouldThrowDatabaseExceptionWhenDependantId() {
        Assertions.assertThrows(DatabaseException.class, () -> {
            service.delete(dependentId);
        });

        Mockito.verify(repository, Mockito.times(1)).deleteById(dependentId);
    }
}
