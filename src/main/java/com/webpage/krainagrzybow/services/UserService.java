package com.webpage.krainagrzybow.services;

import com.webpage.krainagrzybow.enums.Role;
import com.webpage.krainagrzybow.rdbms.models.User;
import com.webpage.krainagrzybow.rdbms.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void addNewUser(String name, String email, String password, Role role) {
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