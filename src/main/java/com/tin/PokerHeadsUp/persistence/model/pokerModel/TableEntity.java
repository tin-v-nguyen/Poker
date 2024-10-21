package com.tin.PokerHeadsUp.persistence.model.pokerModel;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

public class TableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tableId;
    private Hand currentHand;

    // PlayerEntity object contains userId and stack size
    private List<PlayerEntity> playerList;
    private int buttonPosition;

}
