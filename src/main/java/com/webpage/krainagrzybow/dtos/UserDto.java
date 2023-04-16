package com.webpage.krainagrzybow.dtos;

import com.webpage.krainagrzybow.enums.Role;
import com.webpage.krainagrzybow.rdbms.models.Order;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private List<Order> orders;

    @NotNull
    private Role role;

    @NotNull
    private String phoneNumber;

}
