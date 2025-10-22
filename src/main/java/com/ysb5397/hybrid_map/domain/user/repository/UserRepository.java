package com.ysb5397.hybrid_map.domain.user.repository;

import com.ysb5397.hybrid_map.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
