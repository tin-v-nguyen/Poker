package com.tin.PokerHeadsUp.persistence.model.pokerModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Hand {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long handId;
    private List<PlayerEntity> playersInHand;
    private Deck deck;
    private List<CardEntity> runOut;
    private List<Action> preFlop;
    private List<Action> postFlop;
    private List<Action> postTurn;
    private List<Action> postRiver;
    private PlayerEntity winner;
    private int pot;
}
