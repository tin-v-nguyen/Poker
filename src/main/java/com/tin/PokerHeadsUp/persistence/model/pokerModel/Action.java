package com.tin.PokerHeadsUp.persistence.model.pokerModel;

import jakarta.persistence.Embeddable;

@Embeddable
public class Action {
    private ActionType action;
    private int betAmount;
}
