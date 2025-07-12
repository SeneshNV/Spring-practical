package com.springweb.springWeb.controller;

import com.springweb.springWeb.model.Product;
import com.springweb.springWeb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping("/products")
    public List<Product> getProductDetails() {
        return service.getAllProducts();
    }

    @GetMapping("/products/{productId}")
    public Product getProdcutById(@PathVariable int productId){
        return service.getProductById(productId);
    }

    @PostMapping("/products")
    public String addProduct(@RequestBody Product product){
        System.out.printf(String.valueOf(product));
        service.addProduct(product);
        return "Add Successfully ☺";
    }

    @PatchMapping("/products")
    public void updateProduct(@RequestBody Product product){
        System.out.printf(String.valueOf(product));
        service.updateProduct(product);
    }

    @DeleteMapping("/products/{productId}")
    public void deleteProduct(@PathVariable int productId){
        service.deleteProduct(productId);
    }
}
