package com.webpage.krainagrzybow.dtos;

import com.webpage.krainagrzybow.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private LocalDate orderDate;
    private List<OrderProductDto> orderProducts;
    private Status orderStatus;
}
