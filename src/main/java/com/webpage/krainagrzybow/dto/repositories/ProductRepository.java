package com.webpage.krainagrzybow.dto.repositories;

import com.webpage.krainagrzybow.dto.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}