package com.tin.PokerHeadsUp.controller;

import com.tin.PokerHeadsUp.config.JwtProvider;
import com.tin.PokerHeadsUp.model.User;
import com.tin.PokerHeadsUp.repository.UserRepository;
import com.tin.PokerHeadsUp.response.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
}
