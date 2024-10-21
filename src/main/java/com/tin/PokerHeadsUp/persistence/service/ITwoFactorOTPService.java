package com.tin.PokerHeadsUp.persistence.service;

import com.tin.PokerHeadsUp.persistence.model.authModel.TwoFactorOTP;
import com.tin.PokerHeadsUp.persistence.model.authModel.User;

public interface ITwoFactorOTPService {

    TwoFactorOTP createTwoFactorOTP(User user, String otp, String jwt);

    TwoFactorOTP findByUser(Long userId);

    TwoFactorOTP findById(String id);

    boolean verifyTwoFactorOTP(TwoFactorOTP twoFactorOTP, String otp);

    void deleteTwoFactorOTP(TwoFactorOTP twoFactorOTP);
}
