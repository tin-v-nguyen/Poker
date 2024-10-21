package com.tin.PokerHeadsUp.memory.model;

import com.tin.PokerHeadsUp.persistence.model.pokerModel.Hand;
import lombok.Data;

import java.util.List;

@Data
public class Table {
    private Long tableId;
    private Hand currentHand;
    private List<Player> playerList;
    private int buttonPosition;

    public Table(List<Player> playerList) {
        this.playerList = playerList;
        buttonPosition = 0;
    }
}
