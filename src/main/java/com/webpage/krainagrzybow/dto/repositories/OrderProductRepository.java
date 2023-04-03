package com.webpage.krainagrzybow.dto.repositories;


import com.webpage.krainagrzybow.dto.models.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}