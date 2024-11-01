package com.tin.PokerHeadsUp.controller.response;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private String jwt;
    private boolean status;
    private String message;
    private boolean twoFactorAuthEnabled;
    private String session;
}
