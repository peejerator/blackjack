package cop4546.group14.blackjack.model;

public class Dealer {
    private Hand hand = new Hand();

    public boolean isBlackjack() { return hand.getTotal() == 21; }

    public void play(Deck deck) {
        while (hand.getTotal() <= 16) {
            hand.addCard(deck.getNextCard());
        }
        if (hand.getTotal() > 21) {

        } else {

        }
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public int getTotal() {
        return hand.getTotal();
    }

    public void clearHand() {
        hand.clear();
    }

    public boolean peek() {
        return hand.peek();
    }
}
