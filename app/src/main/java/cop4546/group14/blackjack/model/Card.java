package cop4546.group14.blackjack.model;

import androidx.annotation.NonNull;

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

    @NonNull
    public String toString() {
        String val = "";
        switch (value) {
            case 1:
                val = "ace";
                break;
            case 2:
                val = "two";
                break;
            case 3:
                val = "three";
                break;
            case 4:
                val = "four";
                break;
            case 5:
                val = "five";
                break;
            case 6:
                val = "six";
                break;
            case 7:
                val = "seven";
                break;
            case 8:
                val = "eight";
                break;
            case 9:
                val = "nine";
                break;
            case 10:
                val = "ten";
                break;
            case 11:
                val = "jack";
                break;
            case 12:
                val = "queen";
                break;
            case 13:
                val = "king";
                break;
            default:
                val = Integer.toString(value);
                break;
        }
        switch (suit) {
            case HEARTS:
                return val + "_h";
            case SPADES:
                return val + "_s";
            case DIAMONDS:
                return val + "_d";
            case CLUBS:
                return val + "_c";
            default:
                return "Unknown";
        }
    }
}
