package com.lisa.apiproduct.service;

import com.lisa.apiproduct.model.Product;
import com.lisa.apiproduct.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Product(1L, "Camisa", "Uma camisa legal e colorida", 29.90, 1);

        productRepository.save(product);
    }

    @Test
    public void returnAllProductsWithAtLeastOne() {
        List<Product> products = Collections.singletonList(product);
        when(productRepository.getAllProductsWithQuantityIsEqualOne()).thenReturn(products);

        List<Product> productList = productService.allProductsWithAmouytnIsEqualOne();

        Assertions.assertEquals(products, productList);
    }

}
