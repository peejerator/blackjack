package cop4546.group14.blackjack.model;

public class Player {
    private int chips;
    private int bet;
    private Hand hand;

    private String name;

    private Player() {}

    public Player(int chips) {
        this.chips = chips;
        hand = new Hand();
    }

    public int getChips() { return chips; }

    public int getBet() { return bet; }

    public void setBet(int bet) { this.bet = bet; }

    public String getName()

    public void win() {
        chips += bet;
        bet = 0;
    }

    public void blackjack() {
        bet *= 1.5;
        win();
    }

    public void loss() {
        chips -= bet;
        bet = 0;
    }

    public void bust() {
        chips -= bet;
        bet = 0;
    }

    public void push() {
        bet = 0;
    }

    public void removeFromGame() { chips = -1; } // TODO: see if this is needed

    public void resetChips() { chips = 0; }

    public int getTotal() { return hand.getTotal(); }

    public void addCard(Card card) { hand.addCard(card); }

    public void clearHand() { hand.clear(); }

}
