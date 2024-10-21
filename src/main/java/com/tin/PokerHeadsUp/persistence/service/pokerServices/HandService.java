package com.tin.PokerHeadsUp.persistence.service.pokerServices;

import com.tin.PokerHeadsUp.persistence.model.pokerModel.CardEntity;
import com.tin.PokerHeadsUp.persistence.model.pokerModel.Hand;
import com.tin.PokerHeadsUp.persistence.model.pokerModel.PlayerEntity;
import com.tin.PokerHeadsUp.persistence.repository.pokerRepository.HandRepository;
import com.tin.PokerHeadsUp.persistence.service.pokerServices.interfaces.IHandService;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HandService implements IHandService {

    @Autowired
    private HandRepository handRepository;

    @Override
    public Hand initializeHand(List<PlayerEntity> players) {
        Hand newHand = new Hand();
        newHand.setPlayersInHand(players);
        newHand.setDeck(createShuffledDeck(newHand));
        return newHand;
    }

    @Override
    public List<CardEntity> createShuffledDeck(Hand hand) {
        List<CardEntity> deck = new ArrayList<>();
        // 0, 1, 2, 3 are suits
        for (int suit = 0; suit < 4; suit++) {
            // 1 is Ace, 13 is King
            for (int rank = 1; rank < 14; rank++) {
                deck.add(new CardEntity(suit, rank));
            }
        }
        Collections.shuffle(deck, new SecureRandom());
        hand.setDeck(deck);
        return deck;
    }

    @Override
    public CardEntity dealCard(List<CardEntity> deck) {
        return null;
    }

}
