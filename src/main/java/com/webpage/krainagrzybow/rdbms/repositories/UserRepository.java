package com.webpage.krainagrzybow.rdbms.repositories;

import com.webpage.krainagrzybow.rdbms.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Long findIdByEmail(String email);

    User findByEmail(String email);

    User findByUsername(String username);

    User findById(long id);
    boolean existsByEmail(String mail);

}
