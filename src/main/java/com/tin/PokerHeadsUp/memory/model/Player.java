package com.tin.PokerHeadsUp.memory.model;

import lombok.Data;

import java.util.List;

@Data
public class Player {
    private Long playerId;
    private Long userId;
    private List<Card> playersHand;
    private int seatNumber;
    private int chipStack;
}