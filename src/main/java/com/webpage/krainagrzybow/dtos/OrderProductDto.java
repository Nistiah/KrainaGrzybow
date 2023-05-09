package com.webpage.krainagrzybow.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductDto {

    private BigDecimal price;
    private String productName;
    private Long productId;
}
