package com.tin.PokerHeadsUp.user.utils;

import com.tin.PokerHeadsUp.user.config.OtpConstant;

import java.util.Random;

public class OtpUtils {
    public static String generateOTP() {
        Random random = new Random();
        StringBuilder otp = new StringBuilder(OtpConstant.OTP_LENGTH);
        for (int i = 0; i < OtpConstant.OTP_LENGTH; i++) {
            otp.append(random.nextInt(10));
        }
        return otp.toString();
    }
}
