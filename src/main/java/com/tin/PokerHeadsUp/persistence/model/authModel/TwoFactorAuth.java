package com.tin.PokerHeadsUp.persistence.model.authModel;

import com.tin.PokerHeadsUp.domain.VerificationType;
import jakarta.persistence.Embeddable;
import lombok.Data;

// will be used as an embedded class in User entity so no @Entity

// Data will provide getters setters, equals, hashcode, and toString
@Embeddable
@Data
public class TwoFactorAuth {
    private boolean isEnabled = false;
    private VerificationType verificationType;
    private String sendTo;
}
