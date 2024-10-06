package com.tin.PokerHeadsUp.service.pokerServices.interfaces;

import com.tin.PokerHeadsUp.model.pokerObjects.Card;

import java.util.List;

public interface IDeckService {
    List<Card> createShuffledDeck();
}
