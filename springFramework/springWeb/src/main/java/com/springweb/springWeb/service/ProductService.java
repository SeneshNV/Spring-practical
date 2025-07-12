package com.springweb.springWeb.service;

import com.springweb.springWeb.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    public List<Product> getAllProducts() {

        return List.of(
            new Product(1, "Laptop", 1000),
            new Product(2, "Smartphone", 500),
            new Product(3, "Tablet", 300)
        );
    }
}
