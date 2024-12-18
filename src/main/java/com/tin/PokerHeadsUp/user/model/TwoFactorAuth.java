package com.tin.PokerHeadsUp.user.model;

import com.tin.PokerHeadsUp.user.domain.VerificationType;
import lombok.Data;

// will be used as an embedded class in User entity so no @Entity

// Data will provide getters setters, equals, hashcode, and toString
@Data
public class TwoFactorAuth {
    private boolean isEnabled = false;
    private VerificationType verificationType;
    private String sendTo;
}
