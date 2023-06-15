package com.webpage.krainagrzybow.controllers;

import com.webpage.krainagrzybow.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.webpage.krainagrzybow.services.UserService;
import com.webpage.krainagrzybow.services.ProductService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {


    private final ProductService productService;
    private final UserService userService;

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public  ResponseEntity<String>  addProduct(@RequestParam String name, @RequestParam String description, @RequestParam String price, @RequestParam String image, @RequestParam String promotion) {
        productService.addNewProduct(name, description, image, BigDecimal.valueOf(Double.parseDouble(price)), BigDecimal.valueOf(Double.parseDouble(promotion)));
        return ResponseEntity.ok("Product added successfully");
    }

    @RequestMapping(value = "/changeProduct", method = RequestMethod.POST)
    public ResponseEntity<String>  changeProduct(@RequestParam Long id, @RequestParam String name, @RequestParam String description, @RequestParam String price, @RequestParam String image, @RequestParam String promotion) {
        productService.changeProduct(id, name, description,image,  BigDecimal.valueOf(Double.parseDouble(price)), BigDecimal.valueOf(Double.parseDouble(promotion)));
        return ResponseEntity.ok("Product updated successfully");
    }

    @RequestMapping(value = "/deleteProduct", method = RequestMethod.POST)
    public ResponseEntity<String>  deleteProduct(@RequestParam Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public ResponseEntity<String>  uploadImage(@RequestParam("image") MultipartFile image) throws IOException {
        String filename = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
        String uniqueFilename = System.currentTimeMillis() + "-" + filename;
        String uploadDir = "src/main/resources/static/imgages";

        Path uploadPath = Path.of(uploadDir);

        Path destinationPath = uploadPath.resolve(uniqueFilename);
        Files.copy(image.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

        return ResponseEntity.ok("Image uploaded successfully");
    }

    @RequestMapping(value = "/changeUserRole", method = RequestMethod.POST)
    public ResponseEntity<String>  changeUserRole(@RequestParam Long id, @RequestParam String role) {
        userService.changeUserRole(id, Role.valueOf(role));
        return ResponseEntity.ok("User role changed successfully");
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public ResponseEntity<String>  deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }


}
