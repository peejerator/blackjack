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
    @Ignore
    private int mBets;
    @ColumnInfo(name = "player_winnings")
    private int mWinnings;
    @Ignore
    private int mChips;
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

    public int getWinnings() { return mWinnings;}

    public void setPlayerName(@NonNull String playerName) {
        mPlayerName = playerName;
    }

    public Player(@NonNull String playerName, int winnings) {
        mPlayerName = playerName;
        mWinnings = winnings;
    }

    public int getChips() { return mChips; }

    public int getBet() { return mBets; }

    public void setBet(int bet) { mBets= bet; }

    public void win() {
        mChips += mBets;
        mWinnings = mBets;
        mBets = 0;
    }

    public void blackjack() {
        mBets *= 1.5;
        win();
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

    public void removeFromGame() { mChips = -1; } // TODO: see if this is needed

    public void resetChips() { mChips = 0; }

    public int getTotal() { return hand.getTotal(); }

    public void addCard(Card card) { hand.addCard(card); }

    public void clearHand() { hand.clear(); }

}
