package com.webpage.krainagrzybow.rdbms.repositories;


import com.webpage.krainagrzybow.rdbms.models.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}