package com.tin.PokerHeadsUp.persistence.repository.pokerRepository;

import com.tin.PokerHeadsUp.persistence.model.pokerModel.Hand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HandRepository extends JpaRepository<Hand, Long> {

}
