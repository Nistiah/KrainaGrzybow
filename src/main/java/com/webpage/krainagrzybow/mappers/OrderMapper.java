package com.webpage.krainagrzybow.mappers;

import com.webpage.krainagrzybow.dtos.OrderDto;
import com.webpage.krainagrzybow.rdbms.models.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public final OrderProductMapper orderProductMapper = new OrderProductMapper();

    public OrderDto mapToDto(Order order) {
        return OrderDto
                .builder()
                .id(order.getId())
                .orderProducts(order.getAllOrderProducts().stream().map(orderProductMapper::mapToDto).toList())
                .orderStatus(order.getStatus())
                .build();
    }
}
