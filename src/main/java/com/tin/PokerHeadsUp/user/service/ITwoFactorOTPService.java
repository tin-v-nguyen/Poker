package com.tin.PokerHeadsUp.user.service;

import com.tin.PokerHeadsUp.user.model.TwoFactorOTP;
import com.tin.PokerHeadsUp.user.model.User;

public interface ITwoFactorOTPService {

    TwoFactorOTP createTwoFactorOTP(User user, String otp, String jwt);

    TwoFactorOTP findByUser(Long userId);

    TwoFactorOTP findById(String id);

    boolean verifyTwoFactorOTP(TwoFactorOTP twoFactorOTP, String otp);

    void deleteTwoFactorOTP(TwoFactorOTP twoFactorOTP);
}
