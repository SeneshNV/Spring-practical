package com.springwebnew.springwebnew.controller;

import com.springwebnew.springwebnew.model.Product;
import com.springwebnew.springwebnew.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;
    private String keyword;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/")
    public String greet() {
        logger.info("Greeting endpoint called");
        return "Hello Senesh";
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        logger.info("Fetching all products");
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/products/{pid}")
    public ResponseEntity<Product> getProductById(@PathVariable int pid) {
        logger.info("Fetching product with ID: {}", pid);
        Product product = productService.getProductById(pid);

        if (product != null) {
            logger.debug("Product found: {}", product.getName());
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            logger.warn("Product with ID {} not found", pid);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/products")
    public ResponseEntity<?> addProduct(
            @RequestPart Product product,
            @RequestPart MultipartFile imageFile
    ) {
        logger.info("Adding new product: {}", product.getName());

        try {
            Product created = productService.addProduct(product, imageFile);
            logger.debug("Product added successfully with ID: {}", created.getId());
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Failed to add product", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products/{pid}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int pid) {
        logger.info("Fetching image for product ID: {}", pid);
        Product product = productService.getProductById(pid);

        if (product == null || product.getImageData() == null) {
            logger.warn("Image not found for product ID: {}", pid);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(product.getImageData());
    }

    @PutMapping("/product/{pid}")
    public ResponseEntity<String> updateProduct(
            @PathVariable int pid,
            @RequestPart Product product,
            @RequestPart MultipartFile imageFile
    ) {
        logger.info("Updating product with ID: {}", pid);
        Product existingProduct = productService.getProductById(pid);

        if (existingProduct != null && existingProduct.isAvailable()) {
            try {
                productService.updateProduct(pid, product, imageFile);
                logger.debug("Product ID {} updated successfully", pid);
                return new ResponseEntity<>("Update Product Successfully", HttpStatus.OK);
            } catch (IOException e) {
                logger.error("Failed to update product ID {}", pid, e);
                return new ResponseEntity<>("Fail to Update", HttpStatus.BAD_REQUEST);
            }
        } else {
            logger.warn("Cannot update product - not found or unavailable: ID {}", pid);
            return new ResponseEntity<>("Fail to Update", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/product/{pid}")
    public ResponseEntity<String> deleteProduct(@PathVariable int pid) {
        logger.info("Deleting product with ID: {}", pid);
        Product product = productService.getProductById(pid);

        if (product != null && product.isAvailable()) {
            String message = productService.deleteProduct(pid);
            logger.debug("Product ID {} deleted", pid);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            logger.warn("Product ID {} not found or already unavailable", pid);
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/product/search")
    public ResponseEntity <List<Product>> searchProducts(@RequestParam String keyword){
        System.out.println("searching with " + keyword);
        List<Product> product = productService.getSearchProducts(keyword);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
