package com.tin.PokerHeadsUp.persistence.service.pokerServices;

import com.tin.PokerHeadsUp.persistence.model.pokerModel.Hand;
import com.tin.PokerHeadsUp.persistence.model.pokerModel.PlayerEntity;
import com.tin.PokerHeadsUp.persistence.repository.pokerRepository.HandRepository;
import com.tin.PokerHeadsUp.persistence.service.pokerServices.interfaces.IHandService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HandService implements IHandService {

    @Autowired
    private HandRepository handRepository;

    @Autowired
    private DeckService deckService;

    @Override
    public Hand initializeHand(List<PlayerEntity> players) {
        Hand newHand = new Hand();
        newHand.setPlayersInHand(players);
        newHand.setDeck(deckService.createShuffledDeck());
        return newHand;
    }
}
