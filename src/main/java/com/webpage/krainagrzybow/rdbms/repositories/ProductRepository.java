package com.webpage.krainagrzybow.rdbms.repositories;

import com.webpage.krainagrzybow.rdbms.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}