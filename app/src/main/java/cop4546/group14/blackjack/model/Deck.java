package cop4546.group14.blackjack.model;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private final ArrayList<Card> deck = new ArrayList<>(52);

    private int current = 0;

    public Deck() {
        for (int i = 1; i <= 13; i++) {
            deck.add(new Card(Card.Suit.CLUBS, i));
        }
        for (int i = 1; i <= 13; i++) {
            deck.add(new Card(Card.Suit.SPADES, i));
        }
        for (int i = 1; i <= 13; i++) {
            deck.add(new Card(Card.Suit.DIAMONDS, i));
        }
        for (int i = 1; i <= 13; i++) {
            deck.add(new Card(Card.Suit.HEARTS, i));
        }
    }

    public void shuffle() {
        SecureRandom rand = new SecureRandom();
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < deck.size(); ++j) {
                Collections.swap(deck, i, rand.nextInt(52));
            }
        }
    }

    public Card getNextCard() {
        if (current >= 51) {
            shuffle();
            current = 0;
        }

        return deck.get(current++);
    }
}
