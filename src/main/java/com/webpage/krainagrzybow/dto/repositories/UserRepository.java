package com.webpage.krainagrzybow.dto.repositories;

import com.webpage.krainagrzybow.dto.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    ///TODO: do ogarniecia custom query
    Long findIdByEmail(String email); //???? jak to niby ma dzialac?
}
