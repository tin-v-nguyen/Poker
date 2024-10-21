package com.tin.PokerHeadsUp.persistence.model.pokerModel;

import com.tin.PokerHeadsUp.memory.model.Player;
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
    @ManyToOne
    private Long userId;

    @ElementCollection
    private List<CardEntity> playersHand;
    private int seatNumber;
    private int chipStack;


}
