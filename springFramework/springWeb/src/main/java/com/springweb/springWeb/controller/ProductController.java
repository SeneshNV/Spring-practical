package com.springweb.springWeb.controller;

import com.springweb.springWeb.model.Product;
import com.springweb.springWeb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService service;

    @RequestMapping("/products")
    public List<Product> getProductDetails() {
        return service.getAllProducts();
    }

    public Product getProdcutById(int productId){
        return service.getProductById(productId);
    }
}
