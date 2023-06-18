package com.webpage.krainagrzybow.controllers;

import com.webpage.krainagrzybow.dtos.ProductDto;
import com.webpage.krainagrzybow.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long productId) {
        ProductDto product = productService.getProductDtoById(productId);
        return ResponseEntity.ok(product);
    }

    //glowna strona z produktami
    @GetMapping("/MycologicalSea")
    public ResponseEntity<Page<ProductDto>> getAllProducts(@RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 4);
        Page<ProductDto> productsPage = productService.getAllProductsDto(pageable);
        return ResponseEntity.ok(productsPage);
    }

    ///Patrigo, wątpie czy ci sie przyda, ale mamy zaimplementowany upload obrazkow, wiec pewnie jakis uchwyt?
    ///tu ci zwraca stringi z nazwami obrazkow w folderze
    @GetMapping("/images")
    public ResponseEntity<List<String>> getAllImagesNames() {
        List<String> imagesNames = productService.getAllImages();
        return ResponseEntity.ok(imagesNames);
    }
    //Są też metody zwracające liczbe produktów w koszyku i wishliscie, ale nie wiem czy ci sie przydadzą, jakby co to pisz
}
