package cop4546.group14.blackjack.model;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Player {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private long mPlayerId;

    @NonNull
    @ColumnInfo(name = "player_name")
    private String mPlayerName;
    @Ignore
    private int mBets;

    @ColumnInfo(name = "player_chips")
    private int mChips;

    @Ignore
    private Hand hand;

    public long getPlayerId() {
        return mPlayerId;
    }

    public void setPlayerId(long playerId) {
        mPlayerId = playerId;
    }

    public String getPlayerName(){
        return mPlayerName;
    }

    public void setPlayerName(@NonNull String playerName){
        mPlayerName = playerName;
    }

    public Player() {
        // empty default constructor
    }

    public Player(@NonNull String playerName, int chips) {
        mPlayerName = playerName;

        mBets = 0;

        mChips = chips;
        hand = new Hand();
    }

    public int getChips() { return mChips; }

    public void setChips(int chips) {
        mChips = chips;
    }

    public int getBet() { return mBets; }

    public void setBet(int bet) {
        mBets = bet;
    }

    public void win() {
        mChips += mBets;
        mBets = 0;
    }

    public void blackjack() {
        mChips += mBets * 1.5;
        mBets = 0;
    }

    public void loss() {
        mChips -= mBets;
        mBets = 0;
    }

    public void bust() {
        mChips -= mBets;
        mBets = 0;
    }

    public void push() {
        mBets = 0;
    }

    public int getTotal() { return hand.getTotal(); }

    public int getHandSize() { return hand.size(); }

    public void addCard(Card card) { hand.addCard(card); }

    public void clearHand() { hand.clear(); }

    public void resetBet() {
        mBets = 0;
    }

    public String[] getHand() {
        return hand.toStringArray();
    }
}
