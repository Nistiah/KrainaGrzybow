package com.webpage.krainagrzybow.controllers;

import com.webpage.krainagrzybow.dtos.OrderDto;
import com.webpage.krainagrzybow.dtos.UserDto;
import com.webpage.krainagrzybow.mappers.OrderMapper;
import com.webpage.krainagrzybow.mappers.UserMapper;
import com.webpage.krainagrzybow.services.AutenticationService;
import com.webpage.krainagrzybow.services.OrderService;
import com.webpage.krainagrzybow.services.ProductService;
import com.webpage.krainagrzybow.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RequestMapping("/client")
public class UserController {
    private final UserService userService;
    private final ProductService productService;
    private final UserMapper userMapper;
    private final OrderMapper orderMapper;
    private final OrderService orderService;
    private final AutenticationService autenticationService;

    @RequestMapping(value = "/myAccount", method = RequestMethod.GET)
    public ResponseEntity<UserDto> showMyAccountPage(Model model, @RequestParam(required = false) String error, @RequestParam(required = false) String success) {
        if (autenticationService.getLoggedUser() != null) {
            return ResponseEntity.ok(userMapper.mapToDto(autenticationService.getLoggedUser()));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public ResponseEntity<OrderDto> showCartPage(Model model) {
        if (autenticationService.getLoggedUser() != null) {
            return ResponseEntity.ok(orderMapper.mapToDto(orderService.getShoppingCart(autenticationService.getLoggedUser().getId())));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @RequestMapping(value = "/addToCart", method = RequestMethod.POST)
    public ResponseEntity<String> addToCart(@RequestParam("productId") Long productId) {
        if (autenticationService.getLoggedUser() != null) {
            orderService.addToCart(productId);
            return ResponseEntity.ok("Product added to cart");
        } else {
            return ResponseEntity.badRequest().body("You need to be logged in to add product to cart");
        }
    }

    @RequestMapping(value = "/removeFromCart", method = RequestMethod.POST)
    public ResponseEntity<String> removeItemFromCart(@RequestParam("productId") Long productId) {
        if (autenticationService.getLoggedUser() != null) {
            orderService.removeFromCart(productId);
            return ResponseEntity.ok("Product removed from cart");
        } else {
            return ResponseEntity.badRequest().body("You need to be logged in to add product to cart");
        }
    }

    @RequestMapping(value = "/wishlist", method = RequestMethod.GET)
    public ResponseEntity<OrderDto> showWishlistPage(Model model) {
        if (autenticationService.getLoggedUser() != null) {
            return ResponseEntity.ok(orderMapper.mapToDto(orderService.getWishList(autenticationService.getLoggedUser().getId())));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @RequestMapping(value = "/addToWishList", method = RequestMethod.POST)
    public ResponseEntity<String> addToWishList(@RequestParam("productId") Long productId, Model model, HttpServletRequest request) {
        if (autenticationService.getLoggedUser() != null) {
            orderService.addToWishList(productId);
            return ResponseEntity.ok("Product added to wishlist");
        } else {
            return ResponseEntity.badRequest().body("You need to be logged in to add product to wishlist");
        }
    }

    @RequestMapping(value = "/removeFromWishlist", method = RequestMethod.POST)
    public ResponseEntity<String> removeItemFromWishlist(@RequestParam("productId") Long productId, Model model) {
        if (autenticationService.getLoggedUser() != null) {
            orderService.removeFromWishList(productId);
            return ResponseEntity.ok("Product removed from wishlist");
        } else {
            return ResponseEntity.badRequest().body("You need to be logged in to remove product from wishlist");
        }
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public ResponseEntity<String> order(@RequestParam("orderId") Long productId) {
        if (autenticationService.getLoggedUser() != null) {
            orderService.order(productId);
            return ResponseEntity.ok("Successfully ordered");
        } else {
            return ResponseEntity.badRequest().body("You need to be logged in to place order");
        }
    }
}
