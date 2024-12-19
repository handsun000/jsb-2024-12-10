package com.mysite.sbb.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SiteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50, unique = true)
    private String username;

    @Column(columnDefinition = "TEXT")
    private String password;

    @Column(unique = true)
    private String email;

}
