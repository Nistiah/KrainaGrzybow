package com.webpage.krainagrzybow.controllers;

import com.webpage.krainagrzybow.dtos.ProductDto;
import com.webpage.krainagrzybow.rdbms.models.Product;
import com.webpage.krainagrzybow.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/itemsList")
    public ResponseEntity<List<ProductDto>> sayHelloAll(Pageable pageable) {
        return ResponseEntity.ok(productService.getAllProducts(pageable));
    }

    @GetMapping("/itemsList/{id}")
    public ResponseEntity<ProductDto> sayHelloAll(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    //TODO: dostep tylko dla admina
    @PostMapping("/addNewProduct")
    public ResponseEntity<String> addNewItem(@RequestParam String name,
                                             @RequestParam String description,
                                             @RequestParam String image,
                                             @RequestParam BigDecimal price,
                                             @RequestParam BigDecimal promotion) {
        boolean success = productService.addNewProduct(name, description, image, price, promotion);
        if (success) {
            return ResponseEntity.ok("Item added successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to add item");
        }
    }
    @PostMapping("/changePrice")
    public ResponseEntity<String> changePrice(@RequestParam Long id,
                                              @RequestParam BigDecimal price) {
        productService.changePrice(id, price);
        return ResponseEntity.ok("Price changed successfully");
    }

    @PostMapping("/setPromotion")
    public ResponseEntity<String> setPromotion(@RequestParam Long id,
                                               @RequestParam BigDecimal promotion) {
        productService.setPromotion(id, promotion);
        return ResponseEntity.ok("Promotion set successfully");
    }

    @PostMapping("/deletePromotion")
    public ResponseEntity<String> deletePromotion(@RequestParam Long id) {
        productService.deletePromotion(id);
        return ResponseEntity.ok("Promotion deleted successfully");
    }

    @PostMapping("/changeDescription")
    public ResponseEntity<String> changeDescription(@RequestParam Long id,
                                                    @RequestParam String description) {
        productService.changeDescription(id, description);
        return ResponseEntity.ok("Description changed successfully");
    }

    @PostMapping("/changeImage")
    public ResponseEntity<String> changeImage(@RequestParam Long id,
                                              @RequestParam String image) {
        productService.changeImage(id, image);
        return ResponseEntity.ok("Image changed successfully");
    }

    @PostMapping("/changeName")
    public ResponseEntity<String> changeName(@RequestParam Long id,
                                             @RequestParam String name) {
        productService.changeName(id, name);
        return ResponseEntity.ok("Name changed successfully");
    }

    @PostMapping("/deleteProduct")
    public ResponseEntity<String> deleteProduct(@RequestParam Long id) {
        return ResponseEntity.ok("Product deleted successfully");
    }

}
