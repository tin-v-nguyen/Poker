package com.tin.PokerHeadsUp.persistence.repository;

import com.tin.PokerHeadsUp.persistence.model.authModel.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// use this to manage data in the database
public interface UserRepository extends JpaRepository<User, Long> {

    // method name matches a field in User
    Optional<User> findByEmail(String email);

}
