package cop4546.group14.blackjack;

import android.content.Context;

import androidx.room.Room;

import cop4546.group14.blackjack.model.Player;

public class PlayerRepo {
    private static PlayerRepo mPlayerRepo;
    private final PlayerDAO mPlayerDAO;

    // Private constructor to initialize the repository
    private PlayerRepo(Context context) {
        // Build the database instance
        PlayerDatabase database = Room.databaseBuilder(context.getApplicationContext(),
                        PlayerDatabase.class, "players.db")
                .allowMainThreadQueries() // Allow queries on the main thread (for simplicity)
                .fallbackToDestructiveMigration()
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
        long playerID = mPlayerDAO.addPlayer(player);
        player.setPlayerId(playerID);
    }


    /* Method to get all players from the database
    public List<Player> getTop10Bets() {
        return mPlayerDAO.getTop10Bets();
    } */

    // Add other methods for interacting with the database as needed
}
