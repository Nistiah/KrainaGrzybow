package com.webpage.krainagrzybow.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/public")
    public ResponseEntity<String> sayHelloPublic() {
        return ResponseEntity.ok("Hello from public endpoint");
    }

    @GetMapping("/base")
    public ResponseEntity<String> sayHelloBase() {
        return ResponseEntity.ok("Hello from base endpoint");
    }

    @GetMapping("/cataloguer")
    public ResponseEntity<String> sayHelloCataloguer() {
        return ResponseEntity.ok("Hello from cataloguer endpoint");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> sayHelloAdmin() {
        return ResponseEntity.ok("Hello from admin endpoint");
    }
}