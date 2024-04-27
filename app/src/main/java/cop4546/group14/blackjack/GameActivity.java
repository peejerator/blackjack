package cop4546.group14.blackjack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import cop4546.group14.blackjack.model.Game;

public class GameActivity extends AppCompatActivity {
    Game game = new Game();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        game.shuffle();
    }
}