package com.tin.PokerHeadsUp.persistence.repository;

import com.tin.PokerHeadsUp.persistence.model.authModel.TwoFactorOTP;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TwoFactorOTPRepository extends JpaRepository<TwoFactorOTP, String> {
    TwoFactorOTP findByUserId(Long userId);
}
