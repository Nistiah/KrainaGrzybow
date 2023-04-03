package com.webpage.krainagrzybow.controllers;

import com.webpage.krainagrzybow.dto.models.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String welcome() {
        return "home";
    }

    @ModelAttribute("products")
    public List<Product> products() {
        List<Product> products = new ArrayList<>();


        return products;
    }
}
