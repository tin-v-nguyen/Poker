package com.tin.PokerHeadsUp.persistence.model.pokerModel;

import jakarta.persistence.Entity;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Deck {
    private List<CardEntity> cards;
}
