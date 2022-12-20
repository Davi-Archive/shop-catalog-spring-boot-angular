package io.davi.catalog.resources;

import io.davi.catalog.dto.ProductDTO;
import io.davi.catalog.services.ProductService;
import io.davi.catalog.services.exceptions.ResourceNotFoundException;
import io.davi.catalog.tests.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ProductResource.class)
public class ProductResourcesTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService service;
    private PageImpl<ProductDTO> page;
    private ProductDTO productDTO;

    private Long existingId;
    private Long nonExistingId;

    @BeforeEach
    void setUp() throws Exception {

        existingId = 1L;
        nonExistingId = 2L;

        productDTO = Factory.createProductDTO();
        page = new PageImpl<>(List.of(productDTO));
        Mockito.when(service.findAllPaged(ArgumentMatchers.any()))
                .thenReturn(page);

        Mockito.when(service.findById(existingId))
                .thenReturn(productDTO);
        Mockito.when(service.findById(nonExistingId))
                .thenThrow(ResourceNotFoundException.class);


    }

    @Test
    public void findByIdShouldReturnProductWhenIdExists() throws Exception {
        ResultActions result = mockMvc
                .perform(get("/products/{id}", existingId)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.name").exists());
        result.andExpect(jsonPath("$.description").exists());
    }

    @Test
    public void findByIdShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
        ResultActions result = mockMvc.perform(get("/products/{id}", nonExistingId)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());
    }

    @Test
    public void findAllShouldReturnPage() throws Exception {
        ResultActions result =
                mockMvc.perform(get("/products")
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }

}
