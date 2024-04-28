package cop4546.group14.blackjack;
import cop4546.group14.blackjack.model.Player;

import java.util.List;
import androidx.room.*;

@Dao
public interface PlayerDAO {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    long addPlayer(Player player);

    @Query("SELECT * FROM players ORDER BY player_winnings DESC LIMIT 10")
    List<Player> getTop10Bets();

}
