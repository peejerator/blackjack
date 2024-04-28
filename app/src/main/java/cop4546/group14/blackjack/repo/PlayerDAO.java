package cop4546.group14.blackjack.repo;
import cop4546.group14.blackjack.model.Player;

import java.util.List;
import androidx.room.*;

@Dao
public interface PlayerDAO {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    long addPlayer(Player player);



}
