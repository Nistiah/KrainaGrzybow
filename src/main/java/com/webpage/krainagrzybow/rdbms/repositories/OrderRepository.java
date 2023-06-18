package com.webpage.krainagrzybow.rdbms.repositories;

import com.webpage.krainagrzybow.enums.Status;
import com.webpage.krainagrzybow.rdbms.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByUserIdAndStatus(Long id, Status status);

    Optional<Order> findByStatusAndUserId(Status shoppingCart, Long userId);
}
