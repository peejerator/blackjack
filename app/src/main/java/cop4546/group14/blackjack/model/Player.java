package cop4546.group14.blackjack.model;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity (tableName = "players")
public class Player {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private long mPlayerId;

    @ColumnInfo(name = "player_name")
    @NonNull
    private String mPlayerName;
    @ColumnInfo(name = "player_chips")
    private int mChips;
    @Ignore
    private int bet;
    @Ignore
    private Hand hand;

    public void setPlayerId(long playerId) {
        mPlayerId = playerId;
    }

    public long getPlayerId() {
        return mPlayerId;
    }

    public String getPlayerName(){
        return mPlayerName;
    }

    public void setPlayerName(@NonNull String playerName) {
        mPlayerName = playerName;
    }

    public Player(@NonNull String playerName, int chips) {
        mPlayerName = playerName;
        mChips = chips;
    }

    public int getChips() { return mChips; }

    public int getBet() { return bet; }

    public void setBet(int bet) { this.bet = bet; }

    public void win() {
        mChips += bet;
        bet = 0;
    }

    public void blackjack() {
        bet *= 1.5;
        win();
    }

    public void loss() {
        mChips -= bet;
        bet = 0;
    }

    public void bust() {
        mChips -= bet;
        bet = 0;
    }

    public void push() {
        bet = 0;
    }

    public void removeFromGame() { mChips = -1; } // TODO: see if this is needed

    public void resetChips() { mChips = 0; }

    public int getTotal() { return hand.getTotal(); }

    public void addCard(Card card) { hand.addCard(card); }

    public void clearHand() { hand.clear(); }

}
