package io.davi.catalog.services;

import io.davi.catalog.dto.ProductDTO;
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
import org.springframework.data.domain.*;
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
    private ProductDTO productDto;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
        nonExistingId = 1000L;
        dependentId = 4L;
        product = Factory.createProduct();
        page = new PageImpl<>(List.of(product));
        productDto = new ProductDTO();

        Mockito.when(repository.findAll((Pageable) ArgumentMatchers.any()))
                .thenReturn(page);

        Mockito.when(repository.save(ArgumentMatchers.any()))
                .thenReturn(product);

        Mockito.when(repository.findById(existingId))
                .thenReturn(Optional.of(product));

        Mockito.doThrow(ResourceNotFoundException.class)
                .when(repository).findById(nonExistingId);

        Mockito.when(repository.getOne(existingId)).thenReturn(product);
        Mockito.doThrow(ResourceNotFoundException.class)
                .when(repository).getOne(nonExistingId);


        Mockito.when(repository.save(product))
                .thenReturn(product);

        Mockito.doNothing().when(repository).deleteById(existingId);
        Mockito.doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(nonExistingId);
        Mockito.doThrow(DataIntegrityViolationException.class).when(repository).deleteById(dependentId);
    }


    @Test
    public void updateShouldReturnProductDTOWhenIdIsValid() {
        ProductDTO obj = service.update(existingId, productDto);

        Assertions.assertEquals(existingId, obj.getId());
        Assertions.assertTrue(obj instanceof ProductDTO);

        Mockito.verify(repository, Mockito.times(1))
                .save(product);

    }

    @Test
    public void updateShouldThrowResourceNotFoundExceptionWhenIdIsInvalid() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.update(nonExistingId, productDto);
        });

        Mockito.verify(repository, Mockito.times(1))
                .getOne(nonExistingId);
    }


    @Test
    public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.findById(nonExistingId);
        });

        Mockito.verify(repository, Mockito.times(1))
                .findById(nonExistingId);
    }

    @Test
    public void findByIdShouldReturnProductDTO() {
        var object = service.findById(existingId);

        Assertions.assertNotNull(object);
        Assertions.assertEquals(object.getId(), existingId);
        Assertions.assertTrue(object instanceof ProductDTO);

        Mockito.verify(repository, Mockito.times(1))
                .findById(existingId);

    }

    @Test
    public void findAllPagedShouldReturnPage() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<ProductDTO> result = service.findAllPaged(pageable);

        Assertions.assertNotNull(result);
        Mockito.verify(repository, Mockito.times(1))
                .findAll(pageable);
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
