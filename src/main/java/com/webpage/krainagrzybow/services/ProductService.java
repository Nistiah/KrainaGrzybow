package com.webpage.krainagrzybow.services;

import com.webpage.krainagrzybow.dtos.ProductDto;
import com.webpage.krainagrzybow.mappers.ProductMapper;
import com.webpage.krainagrzybow.rdbms.models.Product;
import com.webpage.krainagrzybow.rdbms.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public boolean addNewProduct(String name, String description, String image, BigDecimal price, BigDecimal promotion) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setImage(image);
        product.setPrice(price);
        product.setPromotion(promotion);
        productRepository.save(product);
        return true;
    }

    public void changeProduct(Long id, String name, String description, String image, BigDecimal price, BigDecimal promotion) {
        Product product = productRepository.findById(id).orElse(null);
        product.setName(name);
        product.setDescription(description);
        product.setImage(image);
        product.setPrice(price);
        product.setPromotion(promotion);
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        product.setDeleted(true);
        productRepository.save(product);
    }

    public ProductDto getProductDtoById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        return productMapper.mapToDto(product);
    }

    public Page<ProductDto> getAllProductsDto(Pageable pageable) {
        return productRepository.findAllByIsDeleted(false, pageable).map(productMapper::mapToDto);
    }



//
//
//    public void changePrice(Long id, BigDecimal price) {
//        Product product = productRepository.findById(id).orElse(null);
//        product.setPrice(price);
//        productRepository.save(product);
//    }
//
//    public void setPromotion(Long id, BigDecimal promotion) {
//        Product product = productRepository.findById(id).orElse(null);
//        product.setPromotion(promotion);
//        productRepository.save(product);
//    }
//
//    public void deletePromotion(Long id) {
//        Product product = productRepository.findById(id).orElse(null);
//        product.setPromotion(null);
//        productRepository.save(product);
//    }
//
//    public void changeDescription(Long id, String description) {
//        Product product = productRepository.findById(id).orElse(null);
//        product.setDescription(description);
//        productRepository.save(product);
//    }
//
//    public void changeImage(Long id, String image) {
//        Product product = productRepository.findById(id).orElse(null);
//        product.setImage(image);
//        productRepository.save(product);
//    }
//
//    public void changeName(Long id, String name) {
//        Product product = productRepository.findById(id).orElse(null);
//        product.setName(name);
//        productRepository.save(product);
//    }
//
//    public List<ProductDto> getAllProducts(Pageable pageable) {
//
//        return productRepository.findAll(pageable).stream().map(productMapper::mapToDto).toList();
//    }
//
//    public ProductDto getProductById(Long id) {
//        return productRepository.findById(id).map(productMapper::mapToDto).orElse(null);
//    }
//
//    public boolean saveProduct(Product product) {
//        productRepository.save(product);
//        return true;
//    }
//
//    public boolean deleteProductById(Long id) {
//        productRepository.deleteById(id);
//        return true;
//    }
}


