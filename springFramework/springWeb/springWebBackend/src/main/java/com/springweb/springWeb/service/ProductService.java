package com.springweb.springWeb.service;

import com.springweb.springWeb.model.Product;
import com.springweb.springWeb.repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    public ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

//    List<Product> products = new ArrayList<>(Arrays.asList(
//            new Product(1, "Laptop", 1000),
//            new Product(2, "Smartphone", 500),
//            new Product(3, "Tablet", 300)
//    ));

    public List<Product> getAllProducts() {
//        return products;
        return productRepo.findAll();
    }

    public Product getProductById(int productId) {
//        return products.stream()
//                .filter(p -> p.getProductId() == productId)
//                .findFirst()
////                .orElseThrow(() -> new RuntimeException("Product not found"));
//                .orElse(new Product(productId, "no iteam", 0));

        return productRepo.findById(productId).orElse(new Product(0, "", 0));
    }

    public void addProduct(Product product){
//        products.add(product);
        productRepo.save(product);
    }

    public void updateProduct(Product product) {
//        int index=0;
//        for(int i=0; i<products.size(); i++){
//            if(products.get(i).getProductId() == product.getProductId())
//                index = i;
//        }
//        products.set(index, product);

        productRepo.save(product);
    }

    public void deleteProduct(int productId) {
//        int index=0;
//        for(int i=0; i<products.size(); i++){
//            if(products.get(i).getProductId() == productId)
//                index = i;
//        }
//        products.remove(index);
        productRepo.deleteById(productId);
    }
}
