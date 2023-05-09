package com.webpage.krainagrzybow.controllers;

import com.webpage.krainagrzybow.dtos.ProductDto;
import com.webpage.krainagrzybow.rdbms.models.Product;
import com.webpage.krainagrzybow.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
//@ConfigurationProperties("items")
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;

  @GetMapping("/itemsList")
  public ResponseEntity<List<ProductDto>> sayHelloAll(Pageable pageable) {
    return ResponseEntity.ok(productService.getAllProducts(pageable));
  }


}
