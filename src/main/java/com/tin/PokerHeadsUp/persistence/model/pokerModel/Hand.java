package com.tin.PokerHeadsUp.persistence.model.pokerModel;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Hand {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long handId;
    @OneToMany
    private List<PlayerEntity> playersInHand;
    @ElementCollection
    @CollectionTable(name = "hand_cards", joinColumns = @JoinColumn(name = "hand_id"))
    private List<CardEntity> deck;
    @ElementCollection
    private List<CardEntity> runOut;
    @ElementCollection
    private List<Action> preFlop;
    @ElementCollection
    private List<Action> postFlop;
    @ElementCollection
    private List<Action> postTurn;
    @ElementCollection
    private List<Action> postRiver;
    @OneToOne
    private PlayerEntity winner;
    private int pot;
}
