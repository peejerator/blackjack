package cop4546.group14.blackjack.repo;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cop4546.group14.blackjack.model.Player;

public class PlayerRepo {
    private static PlayerRepo mPlayerRepo;
    private final PlayerDAO mPlayerDAO;

    private static final ExecutorService mExecutor = Executors.newFixedThreadPool(4);

    // Private constructor to initialize the repository
    private PlayerRepo(Context context) {
        // Build the database instance
        PlayerDatabase database = Room.databaseBuilder(context,
                        PlayerDatabase.class, "players.db")
//                .allowMainThreadQueries() // Don't allow main thread queries anymore
                .build();

        // Obtain the PlayerDAO instance
        mPlayerDAO = database.playerDAO();
    }

    // Singleton pattern to ensure only one instance of the repository is created
    public static PlayerRepo getInstance(Context context) {
        if (mPlayerRepo == null) {
            mPlayerRepo = new PlayerRepo(context);
        }
        return mPlayerRepo;
    }

    // AddPlayer method to insert a new player into the database
    public void addPlayer(Player player) {
        mExecutor.execute(() -> mPlayerDAO.addPlayer(player));
    }


    public LiveData<List<Player>> getTop10Bets() {
        return mPlayerDAO.getTop10Bets();
    }
}
