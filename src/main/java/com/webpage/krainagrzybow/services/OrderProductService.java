package com.webpage.krainagrzybow.services;

import com.webpage.krainagrzybow.rdbms.models.Order;
import com.webpage.krainagrzybow.rdbms.models.OrderProduct;
import com.webpage.krainagrzybow.rdbms.repositories.OrderProductRepository;
import com.webpage.krainagrzybow.rdbms.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProductService {
    private final OrderProductRepository orderProductRepository;

    private final OrderRepository orderRepository;

    //legacy code, uzywany tylko w testach (Appconfig)
    public void addOrderProductToOrder( OrderProduct orderProduct,Order order) {
        order.addOrderProduct(orderProduct);
        orderRepository.save(order);
    }
}

