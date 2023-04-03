package com.webpage.krainagrzybow.services;

import com.webpage.krainagrzybow.dto.models.Product;
import com.webpage.krainagrzybow.dto.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addNewProduct(String name, String description, String image, BigDecimal price) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setImage(image);
        product.setPrice(price);
        productRepository.save(product);
    }

    public void changePrice(Long id, BigDecimal price) {
        Product product = productRepository.findById(id).orElse(null);
        product.setPrice(price);
        productRepository.save(product);
    }
    public void setPromotion(Long id, BigDecimal promotion) {
        Product product = productRepository.findById(id).orElse(null);
        product.setPromotion(promotion);
        productRepository.save(product);
    }
    public void deletePromotion(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        product.setPromotion(null);
        productRepository.save(product);
    }

    public void changeDescription(Long id, String description) {
        Product product = productRepository.findById(id).orElse(null);
        product.setDescription(description);
        productRepository.save(product);
    }
    public void changeImage(Long id, String image) {
        Product product = productRepository.findById(id).orElse(null);
        product.setImage(image);
        productRepository.save(product);
    }
    public void changeName(Long id, String name) {
        Product product = productRepository.findById(id).orElse(null);
        product.setName(name);
        productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}


