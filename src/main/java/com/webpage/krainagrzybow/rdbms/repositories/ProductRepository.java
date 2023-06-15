package com.webpage.krainagrzybow.rdbms.repositories;

import com.webpage.krainagrzybow.rdbms.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByDescriptionContainingAndIsDeleted(String description, Pageable pageable, boolean isDeleted);

    Page<Product> findAllByIsDeleted(boolean isDeleted, Pageable pageable);

    List<Product> findAllByIsDeleted(boolean isDeleted, Sort sort);
}