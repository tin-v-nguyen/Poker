package com.tin.PokerHeadsUp.user.service;

import com.tin.PokerHeadsUp.user.config.JwtProvider;
import com.tin.PokerHeadsUp.user.domain.VerificationType;
import com.tin.PokerHeadsUp.user.model.TwoFactorAuth;
import com.tin.PokerHeadsUp.user.model.User;
import com.tin.PokerHeadsUp.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User register(User user) throws Exception {
        Optional<User> userExists = userRepository.findByEmail(user.getEmail());
        if(userExists.isPresent()) {
            throw new Exception("Email is already associated with an account, please log in");
        }
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        // Need to Encrypt password
        newUser.setPassword(user.getPassword());
        newUser.setFullName(user.getFullName());
        User savedUser = userRepository.save(newUser);
        return savedUser;
    }

    @Override
    public User findUserByJwt(String jwt) throws Exception {
        String email = JwtProvider.getEmailFromToken(jwt);
        return findUserByEmail(email);
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new Exception("User not found");
        }
        return user.get();
    }

    @Override
    public User findUserById(Long id) throws Exception {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new Exception("User not found");
        }
        return user.get();
    }

    @Override
    public User enableTwoFactorAuthentication(VerificationType verificationType, String sendTo, User user) {
        TwoFactorAuth twoFactorAuth = new TwoFactorAuth();
        twoFactorAuth.setEnabled(true);
        twoFactorAuth.setVerificationType(verificationType);
        twoFactorAuth.setSendTo(sendTo);

        user.setTwoFactorAuth(twoFactorAuth);
        return userRepository.save(user);
    }

    @Override
    public User disableTwoFactorAuthentication(User user) {
        TwoFactorAuth twoFactorAuth = new TwoFactorAuth();
        twoFactorAuth.setEnabled(false);
        twoFactorAuth.setSendTo(null);
        twoFactorAuth.setVerificationType(null);
        user.setTwoFactorAuth(twoFactorAuth);

        return userRepository.save(user);
    }

    @Override
    public User changePassword(User user, String newPassword) {
        user.setPassword(newPassword);
        return userRepository.save(user);
    }
}
