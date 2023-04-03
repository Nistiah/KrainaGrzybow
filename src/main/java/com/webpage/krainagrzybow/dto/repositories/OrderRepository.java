package com.webpage.krainagrzybow.dto.repositories;

import com.webpage.krainagrzybow.dto.models.Order;
import com.webpage.krainagrzybow.dto.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
