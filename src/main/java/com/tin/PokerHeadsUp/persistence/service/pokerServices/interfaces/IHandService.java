package com.tin.PokerHeadsUp.persistence.service.pokerServices.interfaces;

import com.tin.PokerHeadsUp.persistence.model.pokerModel.CardEntity;
import com.tin.PokerHeadsUp.persistence.model.pokerModel.Hand;
import com.tin.PokerHeadsUp.persistence.model.pokerModel.PlayerEntity;

import java.util.List;

public interface IHandService {
    Hand initializeHand(List<PlayerEntity> players);

    List<CardEntity> createShuffledDeck(Hand hand);
    CardEntity dealCard(List<CardEntity> deck);
}
