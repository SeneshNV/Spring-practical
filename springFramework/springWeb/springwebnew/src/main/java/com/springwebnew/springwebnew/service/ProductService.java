package com.springwebnew.springwebnew.service;

import com.springwebnew.springwebnew.model.Product;
import com.springwebnew.springwebnew.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int pid) {
        return productRepository.findById(pid).orElse(null);
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());

        return productRepository.save(product);
    }

    public Product updateProduct(int pid, Product product, MultipartFile imageFile) throws IOException {
        product.setImageData(imageFile.getBytes());
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        return productRepository.save(product);
    }

    public String deleteProduct(int pid) {
        productRepository.deleteById(pid);
        return "Product deleted successfully";
    }

    public List<Product> getSearchProducts(String keyword) {
        return productRepository.searchProducts(keyword);
    }
}
