package com.tin.PokerHeadsUp.user.service;

import com.tin.PokerHeadsUp.user.domain.VerificationType;
import com.tin.PokerHeadsUp.user.model.User;

public interface IUserService {
    User findUserByJwt(String jwt) throws Exception;
    User findUserByEmail(String email) throws Exception;
    User findUserById(Long id) throws Exception;

    User enableTwoFactorAuthentication(VerificationType verificationType, String sendTo, User user);
    User disableTwoFactorAuthentication(User user);

    User changePassword(User user, String newPassword);
}
