package com.springwebnew.springwebnew.controller;

import com.springwebnew.springwebnew.model.Product;
import com.springwebnew.springwebnew.service.ProductService;
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

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/")
    public String greet(){
        return "Hello Senesh";
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/products/{pid}")
    public ResponseEntity<Product> getProductById(@PathVariable int pid){

        Product product = productService.getProductById(pid);

        if(product != null)
            return new ResponseEntity<>(product, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/products")
    public ResponseEntity<?> addProduct(
            @RequestPart Product product,
            @RequestPart MultipartFile imageFile
    ){

        try {
            Product product1 = productService.addProduct(product, imageFile);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products/{pid}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int pid){
        Product product = productService.getProductById(pid);
        byte[] imageFile = product.getImageData();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);
    }

    @PutMapping("/product/{pid}")
    public ResponseEntity<String> updateProduct(
            @PathVariable int pid,
            @RequestPart Product product,
            @RequestPart MultipartFile imageFile
    ){
        Product product1 = productService.getProductById(pid);

        if(product1 != null && product1.isAvailable()){
            try {
                product1 = productService.updateProduct(pid, product, imageFile);
            } catch (IOException e) {
                return new ResponseEntity<>("Fail to Update", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("Update Product Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Fail to Update", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/product/{pid}")
    public ResponseEntity<String> deleteProduct(@PathVariable int pid) {
        Product product1 = productService.getProductById(pid);

        if (product1 != null && product1.isAvailable()) {
            String message = productService.deleteProduct(pid);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
    }

}
