package com.tin.PokerHeadsUp.user.controller;

import com.tin.PokerHeadsUp.user.config.JwtProvider;
import com.tin.PokerHeadsUp.user.model.TwoFactorOTP;
import com.tin.PokerHeadsUp.user.model.User;
import com.tin.PokerHeadsUp.user.repository.UserRepository;
import com.tin.PokerHeadsUp.user.response.AuthenticationResponse;
import com.tin.PokerHeadsUp.user.service.CustomUserDetailsService;
import com.tin.PokerHeadsUp.user.service.EmailService;
import com.tin.PokerHeadsUp.user.service.TwoFactorOTPService;
import com.tin.PokerHeadsUp.user.service.UserService;
import com.tin.PokerHeadsUp.user.utils.OtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    // UserRepository is how the application interacts with the DB with functions inherited from the JpaRepository
    // Autowired injects the singleton instance of UserRepository for us to use to interact with the db
    @Autowired
    private UserService userService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private TwoFactorOTPService twoFactorOTPService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody User user) throws Exception {
        User savedUser = userService.register(user);

        Authentication auth = new UsernamePasswordAuthenticationToken(
                savedUser.getEmail(),
                savedUser.getPassword()
        );

        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt = JwtProvider.generateToken(auth);

        AuthenticationResponse res = new AuthenticationResponse();
        res.setJwt(jwt);
        res.setStatus(true);
        res.setMessage("Successfully registered user");

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody User user) throws Exception {

        String email = user.getEmail();
        String password = user.getPassword();

        Authentication auth = authenticate(email, password);
        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt = JwtProvider.generateToken(auth);

        User foundUser = userService.findUserByEmail(email);

        if (foundUser.getTwoFactorAuth().isEnabled()) {
            AuthenticationResponse res = new AuthenticationResponse();
            res.setMessage("Two factor auth is enabled");
            res.setTwoFactorAuthEnabled((true));
            String otp = OtpUtils.generateOTP();

            TwoFactorOTP oldTwoFactorOTP = twoFactorOTPService.findByUser(foundUser.getId());

            if (oldTwoFactorOTP != null) {
                twoFactorOTPService.deleteTwoFactorOTP(oldTwoFactorOTP);
            }

            TwoFactorOTP newTwoFactorOTP = twoFactorOTPService.createTwoFactorOTP(foundUser, otp, jwt);
            emailService.sendOtpEmail(email, otp);

            res.setSession(newTwoFactorOTP.getId());
            return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
        }

        AuthenticationResponse res = new AuthenticationResponse();
        res.setJwt(jwt);
        res.setStatus(true);
        res.setMessage("Successfully logged in");

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        if (userDetails == null || !password.equals(userDetails.getPassword())) {
            throw new BadCredentialsException("Username and/or password is invalid");
        }

        return new UsernamePasswordAuthenticationToken(
                userDetails,
                email,
                userDetails.getAuthorities()
        );
    }
}
