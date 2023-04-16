package com.webpage.krainagrzybow.mappers;

import com.webpage.krainagrzybow.dtos.OrderDto;
import com.webpage.krainagrzybow.rdbms.models.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    private OrderDto mapToDto(Order order) {
        return OrderDto
                .builder()
                .id(order.getId())
                .user(order.getUser())
                .orderDate(order.getOrderDate())
                .orderStatus(order.getStatus())
                .orderProducts(order.getOrderProducts())
                .build();
    }
}
