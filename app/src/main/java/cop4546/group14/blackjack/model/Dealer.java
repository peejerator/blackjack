package cop4546.group14.blackjack.model;

public class Dealer {
    private final Hand hand = new Hand();

    public boolean isBlackjack() { return hand.getTotal() == 21; }

    public void play(Deck deck) {
        while (hand.getTotal() <= 16) {
            hand.addCard(deck.getNextCard());
        }
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public int getTotal() {
        return hand.getTotal();
    }

    public int getShowingValue() {
        return hand.getShowingValue();
    }

    public void clearHand() {
        hand.clear();
    }

    public String[] getHand() {
        return hand.toStringArray();
    }
}
