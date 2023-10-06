package com.lisa.apiproduct.repository;

import com.lisa.apiproduct.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("from Product p   where p.amount = 1")
    List<Product> getAllProductsWithQuantityIsEqualOne();

}
