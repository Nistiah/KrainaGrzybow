package com.webpage.krainagrzybow.services;

import com.webpage.krainagrzybow.dtos.OrderDto;
import com.webpage.krainagrzybow.dtos.UserDto;
import com.webpage.krainagrzybow.enums.Role;
import com.webpage.krainagrzybow.mappers.ProductMapper;
import com.webpage.krainagrzybow.mappers.UserMapper;
import com.webpage.krainagrzybow.rdbms.models.User;
import com.webpage.krainagrzybow.rdbms.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public boolean addNewUser(String name, String email, String password, Role role) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        userRepository.save(user);
        return true;
    }

    public UserDto findUserByEmail(String email) {
        return userMapper.mapToDto(userRepository.findByEmail(email));
    }

    public UserDto findUserById(Long id) {
        return userMapper.mapToDto(userRepository.findById(id).orElse(null));
    }

    public boolean changeName(Long id, String name) {
        User user = userRepository.findById(id).orElse(null);
        user.setName(name);
        userRepository.save(user);
        return true;
    }

    public boolean changePhoneNumber(Long id, String phoneNumber) {
        User user = userRepository.findById(id).orElse(null);
        user.setPhoneNumber(phoneNumber);
        userRepository.save(user);
        return true;
    }

    public boolean saveUser(User user) {
        userRepository.save(user);
        return true;

    }
    public Long countUsers() {
        return userRepository.count();
    }
    public Long getUserIdByEmail(String email) {
        return userRepository.findIdByEmail(email);
    }
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
    public List<UserDto> findAllUsers() {
        return userRepository.findAll().stream().map(userMapper::mapToDto).toList();
    }




}