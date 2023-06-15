package com.webpage.krainagrzybow.services;

import com.webpage.krainagrzybow.dtos.OrderDto;
import com.webpage.krainagrzybow.enums.Status;
import com.webpage.krainagrzybow.mappers.OrderMapper;
import com.webpage.krainagrzybow.rdbms.models.Order;
import com.webpage.krainagrzybow.rdbms.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    public Order getShoppingCart(Long userId) {
        return orderRepository.findByUserIdAndStatus(userId, Status.SHOPPING_CART);
    }

    public Order getWishList(Long userId) {
        return orderRepository.findByUserIdAndStatus(userId, Status.WHISHLIST);
    }



}

