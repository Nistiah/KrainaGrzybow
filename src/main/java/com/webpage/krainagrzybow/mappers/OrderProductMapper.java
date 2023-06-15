package com.webpage.krainagrzybow.mappers;

import com.webpage.krainagrzybow.dtos.OrderProductDto;
import com.webpage.krainagrzybow.rdbms.models.OrderProduct;
import org.springframework.stereotype.Component;

@Component
public class OrderProductMapper {
    public OrderProductDto mapToDto(OrderProduct orderProduct) {
        return OrderProductDto
                .builder()
                .price(orderProduct.getProduct().getPrice())
                .productName(orderProduct.getProduct().getName())
                .productId(orderProduct.getProduct().getId())
                .build();
    }
}
