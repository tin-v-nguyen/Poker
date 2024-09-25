package com.tin.PokerHeadsUp.model;

import com.tin.PokerHeadsUp.domain.VerificationType;
import lombok.Data;

// will be used as an embedded class in User entity so no @Entity
@Data
public class TwoFactorAuth {
    private boolean isEnabled = false;
    private VerificationType sendTo;
}
