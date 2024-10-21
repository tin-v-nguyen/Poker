package com.tin.PokerHeadsUp.persistence.service.pokerServices.interfaces;

import com.tin.PokerHeadsUp.persistence.model.pokerModel.CardEntity;
import com.tin.PokerHeadsUp.persistence.model.pokerModel.Deck;

public interface IDeckService {
    Deck createShuffledDeck();
    CardEntity dealCard(Deck deck);
}
