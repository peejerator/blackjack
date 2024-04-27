package cop4546.group14.blackjack.model;

public class Card {

    public static enum Suit { HEARTS, SPADES, DIAMONDS, CLUBS };

    private final Suit suit;
    private final int value;

    public Card(Suit suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    public Suit getSuit() { return this.suit; }
    public int getValue() { return this.value; }
}
