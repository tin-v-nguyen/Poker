package com.tin.PokerHeadsUp.model.pokerObjects;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Card {
    private int suit;
    private int rank;
}
