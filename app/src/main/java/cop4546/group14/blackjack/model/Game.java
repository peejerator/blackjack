package cop4546.group14.blackjack.model;


public class Game {
    private Player player = new Player("blabla",100);// default 100 chips
    private Deck deck = new Deck();
    private Dealer dealer = new Dealer();

    public void shuffle() { deck.shuffle(); }

    public boolean setBet(int bet) {
        if (bet <= 0 || bet > player.getChips()) return false;

        player.setBet(bet);
        return true;
    }

    public void deal() {
        for (int i = 0; i < 2; ++i) {
            player.addCard(deck.getNextCard());
            dealer.addCard(deck.getNextCard());
        }
    }

    public void checkBlackjack() {

    }

    public void hit() {
        if (player.getTotal() > 21) return;

        player.addCard(deck.getNextCard());
    }

    public void dealerPlay() {
        dealer.play(deck);
    }

    public void settle() {
        if (player.getBet() > 0) {
            if (player.getTotal() > 21)
                player.bust();
            else if (player.getTotal() == dealer.getTotal())
                player.push();
            else if (player.getTotal() < dealer.getTotal() && dealer.getTotal() <= 21)
                player.loss();
            else if (player.getTotal() == 21)
                player.blackjack();
            else
                player.win();
        }
    }

    public void clearHands() {
        player.clearHand();
        dealer.clearHand();
    }
}
