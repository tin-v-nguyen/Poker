package com.tin.PokerHeadsUp.persistence.model.pokerModel;

import com.tin.PokerHeadsUp.memory.model.Player;
import com.tin.PokerHeadsUp.persistence.model.authModel.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long playerId;

    // many players to one user, so one user can multitable
    // Use @ManyToOne or @OneToOne to define the relationship
    @ManyToOne
    @JoinColumn(name = "user_id") // Join column that points to UserEntity's primary key
    private User user;

    @ElementCollection
    @CollectionTable(name = "player_hand", joinColumns = @JoinColumn(name = "player_id"))
    private List<CardEntity> playersHand;
    private int seatNumber;
    private int chipStack;


}
