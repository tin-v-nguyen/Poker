package com.tin.PokerHeadsUp.memory.model;

import com.tin.PokerHeadsUp.persistence.model.pokerModel.Hand;

import java.util.List;

public class Table {
    private Long tableId;
    private Hand currentHand;

    // PlayerEntity object contains userId and stack size
    private List<Player> playerList;
    private int buttonPosition;
}
