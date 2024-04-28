package cop4546.group14.blackjack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import cop4546.group14.blackjack.repo.PlayerRepo;

import cop4546.group14.blackjack.model.Game;
import cop4546.group14.blackjack.model.Player;

public class GameActivity extends AppCompatActivity {
    Game game = new Game();
    private PlayerRepo playerRepo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        playerRepo = PlayerRepo.getInstance(getApplicationContext());
        setContentView(R.layout.activity_game);
        Player player = new Player("John", 100);
        playerRepo.addPlayer(player);
        game.shuffle();
    }
}