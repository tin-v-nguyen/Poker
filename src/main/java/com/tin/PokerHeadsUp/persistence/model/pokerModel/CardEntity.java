package com.tin.PokerHeadsUp.persistence.model.pokerModel;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Embeddable
@AllArgsConstructor
public class CardEntity {
    private int suit;
    private int rank;
}
