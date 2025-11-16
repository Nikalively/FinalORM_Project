package com.education.finalorm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "profiles")
public class Profile extends BaseEntity {

    @Column(name = "bio", length = 1000)
    private String bio;

    @Column(name = "avatar_url", length = 500)
    private String avatarUrl;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}