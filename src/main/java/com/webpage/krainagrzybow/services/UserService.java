package com.webpage.krainagrzybow.services;

import com.webpage.krainagrzybow.dto.models.User;
import com.webpage.krainagrzybow.dto.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void addNewUser(String name, String email, String password, String role) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        userRepository.save(user);
    }
    public User saveUser(User user) {
        return userRepository.save(user);
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
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}