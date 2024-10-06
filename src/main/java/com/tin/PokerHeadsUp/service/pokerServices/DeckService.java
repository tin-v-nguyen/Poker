package com.tin.PokerHeadsUp.service.pokerServices;

import com.tin.PokerHeadsUp.model.pokerObjects.Card;
import com.tin.PokerHeadsUp.service.pokerServices.interfaces.IDeckService;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckService implements IDeckService {

    @Override
    public List<Card> createShuffledDeck() {
        List<Card> deck = new ArrayList<>();
        // 0, 1, 2, 3 are suits
        for (int suit = 0; suit < 4; suit++) {
            // 1 is Ace, 13 is King
            for (int rank = 1; rank < 14; rank++) {
                deck.add(new Card(suit, rank));
            }
        }
        Collections.shuffle(deck, new SecureRandom());
        return deck;
    }
}
