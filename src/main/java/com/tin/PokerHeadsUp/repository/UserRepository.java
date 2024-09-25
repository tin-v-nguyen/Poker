package com.tin.PokerHeadsUp.repository;

import com.tin.PokerHeadsUp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// use this to manage data in the database
public interface UserRepository extends JpaRepository<User, Long> {

}
