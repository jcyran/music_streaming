package org.mj.audio.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_modelip")
public @Data class UserModelIP {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_model_seq") // auto-increment
    @SequenceGenerator(name = "user_model_seq", sequenceName = "user_model_seq", allocationSize = 1)
    private long id;

    @Column(name = "username",unique = true, nullable = false)
    private String username;

    @Column(name = "hash_password", nullable = false)
    private String hashPassword;

    @Column(name = "user_role", nullable = false)
    private String userRole;
}
