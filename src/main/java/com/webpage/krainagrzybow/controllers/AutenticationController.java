package com.webpage.krainagrzybow.controllers;

import com.webpage.krainagrzybow.requests.ChangePasswordRequest;
import com.webpage.krainagrzybow.requests.ChangeUserDetailsRequest;
import com.webpage.krainagrzybow.requests.LoginRequest;
import com.webpage.krainagrzybow.requests.RegistrationRequest;
import com.webpage.krainagrzybow.services.AutenticationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RequestMapping("/autentication")
public class AutenticationController {

    private final AutenticationService autenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> signUp(HttpServletRequest request2, @RequestBody RegistrationRequest request) {
        if (request.getUsername().isEmpty() || request.getPassword().isEmpty() || request.getEmail().isEmpty()) {
            return ResponseEntity.badRequest().body("All fields must be filled");
        }

        if (request.getUsername().length() < 6) {
            return ResponseEntity.badRequest().body("Username must be at least 6 characters long");
        }

        autenticationService.register(request.getUsername(), request.getPassword(), request.getEmail());
        autenticationService.logIn(request2, request.getUsername(), request.getPassword());

        return ResponseEntity.ok("Registration successful");
    }

    @PostMapping("/login")
    public ResponseEntity<String> signIn(HttpServletRequest request2, @RequestBody LoginRequest request) {
        try {
            autenticationService.logIn(request2, request.getUsername(), request.getPassword());
            return ResponseEntity.ok("Login successful");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Wrong username or password");
        }
    }

    @PostMapping("/changeUserDetails")
    public ResponseEntity<String> changeUserDetails(HttpServletRequest request2, @RequestBody ChangeUserDetailsRequest request) {
        try {
            autenticationService.changeUserDetails(request2, request.getNewUsername(), request.getPassword(), request.getNewEmail());
            return ResponseEntity.ok("Changes saved successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/changeUserPassword")
    public ResponseEntity<String> changeUserPassword(HttpServletRequest request2, @RequestBody ChangePasswordRequest request) {
        try {
            autenticationService.changeUserPassword(request2, request.getOldPassword(), request.getNewPassword());
            return ResponseEntity.ok("Changes saved successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
