package com.webpage.krainagrzybow.services;

import com.webpage.krainagrzybow.enums.Role;
import com.webpage.krainagrzybow.mappers.UserMapper;
import com.webpage.krainagrzybow.rdbms.models.User;
import com.webpage.krainagrzybow.rdbms.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public void changeUserRole(Long id, Role role) {
        User user = userRepository.findById(id).orElse(null);
        user.setRole(role);
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}