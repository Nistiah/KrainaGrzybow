package com.webpage.krainagrzybow.controllers;

import com.webpage.krainagrzybow.dto.models.Product;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@ConfigurationProperties("items")
public class ProductController {
  @RequestMapping("/itemsList")
  public List<Product> showItems() {
    return products;
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
