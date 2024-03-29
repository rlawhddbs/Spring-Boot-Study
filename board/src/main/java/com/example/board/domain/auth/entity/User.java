package com.example.board.domain.auth.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Enumerated
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.role = Role.USER;
    }

}
