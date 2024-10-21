package com.tin.PokerHeadsUp.persistence.model.pokerModel;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class CardEntity {
    private int suit;
    private int ranking;
}
