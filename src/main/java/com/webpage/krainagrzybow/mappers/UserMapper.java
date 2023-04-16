package com.webpage.krainagrzybow.mappers;


import com.webpage.krainagrzybow.dtos.UserDto;
import com.webpage.krainagrzybow.rdbms.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto mapToDto(User user) {
        return UserDto
                .builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .orders(user.getOrders())
                .role(user.getRole())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

}
