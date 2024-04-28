package cop4546.group14.blackjack.repo;



import androidx.room.Database;
import androidx.room.RoomDatabase;

import cop4546.group14.blackjack.model.Player;

@Database(entities = {Player.class}, version = 2, exportSchema = false)
public abstract class PlayerDatabase extends RoomDatabase {
    public abstract PlayerDAO playerDAO();
}