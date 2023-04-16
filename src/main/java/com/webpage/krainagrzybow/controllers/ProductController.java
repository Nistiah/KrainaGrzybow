package com.webpage.krainagrzybow.controllers;

import com.webpage.krainagrzybow.rdbms.models.Product;
import com.webpage.krainagrzybow.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
//@ConfigurationProperties("items")
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;

  @GetMapping("/itemsList")
  public ResponseEntity<String> sayHelloAll() {
    return ResponseEntity.ok(productService.getAllProducts().toString());
  }
  public List<Product> getItems() {
    return products;
  }

  public void setItems(List<Product> products) {
    this.products = products;
  }


  private List<Product> products = new ArrayList<>();


//  @Controller
//  public static class HomeController {
//      @RequestMapping("/")
//      public String welcome() {
//          return "home";
//      }
//  }
}
