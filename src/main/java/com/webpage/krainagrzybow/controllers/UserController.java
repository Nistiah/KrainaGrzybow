package com.webpage.krainagrzybow.controllers;

import com.webpage.krainagrzybow.dtos.OrderDto;
import com.webpage.krainagrzybow.dtos.UserDto;
import com.webpage.krainagrzybow.mappers.OrderMapper;
import com.webpage.krainagrzybow.mappers.UserMapper;
import com.webpage.krainagrzybow.rdbms.models.Order;
import com.webpage.krainagrzybow.services.AutenticationService;
import com.webpage.krainagrzybow.services.OrderService;
import com.webpage.krainagrzybow.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RequestMapping("/client")
public class UserController {

    private final UserService userService;

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
        }else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @RequestMapping(value = "/wishlist", method = RequestMethod.GET)
    public ResponseEntity<OrderDto> showWishlistPage(Model model) {
        if (autenticationService.getLoggedUser() != null) {
            return ResponseEntity.ok(orderMapper.mapToDto(orderService.getWishList(autenticationService.getLoggedUser().getId())));
        }else {
            return ResponseEntity.badRequest().body(null);
        }
    }


}
