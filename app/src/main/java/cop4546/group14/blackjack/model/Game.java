package cop4546.group14.blackjack.model;


public class Game {
    private Player player = new Player("blabla",1000);// default 1000 chips
    private Deck deck = new Deck();
    private Dealer dealer = new Dealer();

//    public boolean isPlayerTurn = true;

    public enum State {
        PLAYER_TURN,
        DEALER_TURN,
        WIN,
        LOSS,
        PUSH,
        BLACKJACK,
        BUST
    }

    private State state = State.PLAYER_TURN;

    public void shuffle() { deck.shuffle(); }

    public boolean setBet(int bet) {
        if (bet <= 0 || bet > player.getChips()) return false;

        player.setBet(bet);
        return true;
    }

    public String[] getPlayerHand() {
        return player.getHand();
    }

    public int getPlayerTotal() {
        return player.getTotal();
    }

    public int getDealerTotal() {
        return dealer.getTotal();
    }

    public int getDealerShowingValue() {
        return dealer.getShowingValue();
    }

    public int getBet() {
        return player.getBet();
    }

    public int getChips() {
        return player.getChips();
    }

    public void deal() {
        for (int i = 0; i < 2; ++i) {
            player.addCard(deck.getNextCard());
            dealer.addCard(deck.getNextCard());
        }
    }

    public void checkBlackjack() {
        if (dealer.isBlackjack()) {
            if (player.getTotal() == 21) {
                player.push();
                state = State.PUSH;
            }
            else {
                player.bust();
                state = State.BUST;
            }
        } else if (player.getTotal() == 21) {
            player.blackjack();

            state = State.BLACKJACK;
        }
    }

    public int getHandSize() {
        return player.getHandSize();
    }

    public void hit() {
        if (player.getTotal() > 21) return;

        player.addCard(deck.getNextCard());
    }

    public void checkBust() {
        if (player.getTotal() > 21) {
            player.bust();
            state = State.BUST;
        }
    }

    public void dealerPlay() {
        state = State.DEALER_TURN;
        dealer.play(deck);
    }

    public void settle() {
        if (player.getBet() > 0) {
            if (player.getTotal() > 21) {
                player.bust();
                state = State.BUST;
            }
            else if (player.getTotal() == dealer.getTotal()) {
                player.push();
                state = State.PUSH;
            }
            else if (player.getTotal() < dealer.getTotal() && dealer.getTotal() <= 21) {
                player.loss();
                state = State.LOSS;
            }
            else if (player.getTotal() == 21) {
                player.blackjack();
                state = State.BLACKJACK;
            }
            else {
                player.win();
                state = State.WIN;
            }
        }
    }

    public void clearHands() {
        player.clearHand();
        dealer.clearHand();
        state = State.PLAYER_TURN;
    }

    public State getState() {
        return state;
    }

    public void resetBet() {
        player.resetBet();
    }

    public String[] getDealerHand() {
        return dealer.getHand();
    }
}
