package cop4546.group14.blackjack.model;

import static androidx.core.math.MathUtils.clamp;

import java.util.ArrayList;

import cop4546.group14.blackjack.model.Card;

public class Hand {
    private final ArrayList<Card> hand = new ArrayList<>(11); // player can have at most 11 cards

    public void addCard(Card card) {
        hand.add(card);
    }

    public void clear() {
        hand.clear();
    }

    public boolean peek() {
        int val = hand.get(1).getValue();
        return val == 1 || val >= 10;
    }

    public int getTotal() {
        int total = 0;
        boolean hasAce = false;
        for (int i = 0; i < hand.size(); ++i) {
            int val = clamp(hand.get(i).getValue(), 1, 10);

            if (val == 1) hasAce = true;

            total += val;
        }

        // if ace can be used as 10, then do it
        if (hasAce && total + 10 <= 21) {
            total += 10;
        }

        return total;
    }

    public int size() {
        return hand.size();
    }

    public String[] toStringArray() {
        String[] str = new String[hand.size()];
        for (int i = 0; i < hand.size(); ++i) {
            str[i] = hand.get(i).toString();
        }

        return str;
    }

    public int getShowingValue() {
        return hand.get(1).getValue();
    }
}
