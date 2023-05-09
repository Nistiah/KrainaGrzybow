package com.webpage.krainagrzybow.controllers;

import com.webpage.krainagrzybow.dtos.OrderDto;
import com.webpage.krainagrzybow.dtos.UserDto;
import com.webpage.krainagrzybow.rdbms.models.Order;
import com.webpage.krainagrzybow.services.OrderService;
import com.webpage.krainagrzybow.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/client")
public class UserController {

    private final UserService userService;

    private final OrderService orderService;

    @Controller
    @RequestMapping("/client/account")
    public class ProfileController {

        @GetMapping("/accountData/{userId}")
        public ResponseEntity<UserDto> getAccountData(@PathVariable Long userId) {
            try {
                return ResponseEntity.ok(userService.findUserById(userId));
            } catch (Exception e) {
                System.out.println("dupsko");
                e.printStackTrace();
                return ResponseEntity.notFound().build();
            }
        }

        @GetMapping("/test")
        public ResponseEntity<String> test() {
            return ResponseEntity.ok("test");
        }

        @PostMapping("/changeName")
        public ResponseEntity<String> changeName(@RequestParam Long userId, @RequestParam String newName) {
            try {
                userService.changeName(userId, newName);
                return ResponseEntity.ok("Name changed");
            } catch (Exception e) {
                return ResponseEntity.notFound().build();
            }
        }

        @PostMapping("/changePhoneNumber")
        public ResponseEntity<String> changePhoneNumber(@RequestParam Long userId, @RequestParam String newPhoneNumber) {
            try {
                userService.changePhoneNumber(userId, newPhoneNumber);
                return ResponseEntity.ok("Phone number changed");
            } catch (Exception e) {
                return ResponseEntity.notFound().build();
            }
        }
    }

    //TODO: implement cart
    @Controller
    @RequestMapping("/client/cart")
    public class CartController {

        @GetMapping("/cartData/{userId}")
        public ResponseEntity<OrderDto> getCartData(@PathVariable Long userId) {
            try {
                return ResponseEntity.ok(orderService.getCartByUserId(userId));
            } catch (Exception e) {
                System.out.println("dupsko");
                e.printStackTrace();
                return ResponseEntity.notFound().build();
            }
        }

        @GetMapping("/test")
        public ResponseEntity<String> test() {
            return ResponseEntity.ok("test");
        }


    }
}
