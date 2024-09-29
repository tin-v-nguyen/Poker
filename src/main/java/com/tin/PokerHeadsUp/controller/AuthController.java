package com.tin.PokerHeadsUp.controller;

import com.tin.PokerHeadsUp.config.JwtProvider;
import com.tin.PokerHeadsUp.model.User;
import com.tin.PokerHeadsUp.repository.UserRepository;
import com.tin.PokerHeadsUp.response.AuthenticationResponse;
import com.tin.PokerHeadsUp.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    // UserRepository is how the application interacts with the DB with functions inherited from the JpaRepository
    // Autowired injects the singleton instance of UserRepository for us to use to interact with the db
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody User user) throws Exception {

        User userExists = userRepository.findByEmail(user.getEmail());
        if(userExists != null) {
            throw new Exception("Email is already associated with an account, please log in");
        }

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setFullName(user.getFullName());

        User savedUser = userRepository.save(newUser);

        Authentication auth = new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                user.getPassword()
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
