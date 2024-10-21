package com.tin.PokerHeadsUp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tin.PokerHeadsUp.domain.USER_ROLE;
import jakarta.persistence.*;
import lombok.Data;

@Entity
// Lombok reduced boilerplate code, provides getter setter method for our class
@Data

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;
    private String email;

    // when user is fetched by the clientside, password doesn't come in the request
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    // embedded means this class is embedded in the user entity not separate table(@Entity)
    @Embedded
    private TwoFactorAuth twoFactorAuth = new TwoFactorAuth();

    // default role
    private USER_ROLE role = USER_ROLE.ROLE_PLAYER;
    private double balance;
}
