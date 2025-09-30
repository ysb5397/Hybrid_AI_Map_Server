package com.ysb5397.hybrid_map.user.repository;

import com.ysb5397.hybrid_map.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
