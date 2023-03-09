package com.example.board.domain.auth.entity.repository;

import com.example.board.domain.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
