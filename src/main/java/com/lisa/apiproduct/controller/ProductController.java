package com.lisa.apiproduct.controller;

import com.lisa.apiproduct.model.Product;
import com.lisa.apiproduct.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return new ResponseEntity<>(productRepository.save(product), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Product> allProducts() {
        return productRepository.findAll();
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> getProduct(@PathVariable(value = "id") Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product
                .map(productFound -> new ResponseEntity<>(productFound, HttpStatus.OK))
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") Long id, @RequestBody Product product) {
        return productRepository.findById(id)
                .map(productFound -> {
                    productFound.setName(product.getName() == null ? productFound.getName() : product.getName());
                    productFound.setDescription(product.getDescription() == null ? productFound.getDescription() : product.getDescription());
                    productFound.setPrice(product.getPrice() == null ? productFound.getPrice() : product.getPrice());
                    productFound.setAmount(product.getAmount() == null ? productFound.getAmount() : product.getAmount());

                    Product updateProduct = productRepository.save(productFound);
                    return new ResponseEntity<>(updateProduct, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    productRepository.deleteById(id);
                    return new ResponseEntity<>(HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

}
