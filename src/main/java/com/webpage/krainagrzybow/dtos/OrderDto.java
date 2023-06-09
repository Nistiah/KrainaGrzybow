package com.webpage.krainagrzybow.dtos;

import com.webpage.krainagrzybow.enums.Status;
import com.webpage.krainagrzybow.rdbms.models.OrderProduct;
import com.webpage.krainagrzybow.rdbms.models.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

//    @NotNull
    private Long id;

//    @NotNull
//    private User user;

//    @NotNull
    private LocalDate orderDate;

    private List<OrderProductDto> orderProducts;

//    @NotNull
    private Status orderStatus;







}
