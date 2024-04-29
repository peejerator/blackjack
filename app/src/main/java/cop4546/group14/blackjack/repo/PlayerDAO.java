package cop4546.group14.blackjack.repo;
import cop4546.group14.blackjack.model.Player;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.*;

@Dao
public interface PlayerDAO {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void addPlayer(Player player);

    @Query("SELECT * FROM Player ORDER BY player_chips DESC LIMIT 10")
    LiveData<List<Player>> getTop10Bets();
}
