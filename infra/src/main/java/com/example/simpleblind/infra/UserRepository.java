package com.example.simpleblind.infra;

import com.example.simpleblind.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
