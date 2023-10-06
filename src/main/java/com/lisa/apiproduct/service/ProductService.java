package com.lisa.apiproduct.service;

import com.lisa.apiproduct.model.Product;
import com.lisa.apiproduct.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> allProductsWithAmouytnIsEqualOne() {
        return productRepository.getAllProductsWithQuantityIsEqualOne();
    }

}
