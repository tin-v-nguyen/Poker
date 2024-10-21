package com.tin.PokerHeadsUp.persistence.service.pokerServices;

import com.tin.PokerHeadsUp.persistence.model.pokerModel.CardEntity;
import com.tin.PokerHeadsUp.persistence.model.pokerModel.Deck;
import com.tin.PokerHeadsUp.persistence.service.pokerServices.interfaces.IDeckService;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckService implements IDeckService {

    @Override
    public Deck createShuffledDeck() {
        Deck deck = new Deck();
        List<CardEntity> cards = new ArrayList<>();
        // 0, 1, 2, 3 are suits
        for (int suit = 0; suit < 4; suit++) {
            // 1 is Ace, 13 is King
            for (int rank = 1; rank < 14; rank++) {
                cards.add(new CardEntity(suit, rank));
            }
        }
        Collections.shuffle(cards, new SecureRandom());
        deck.setCards(cards);
        return deck;
    }

    @Override
    public CardEntity dealCard(Deck deck) {
        return null;
    }


}
