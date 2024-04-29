package cop4546.group14.blackjack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button helpButton = findViewById(R.id.help_button);
        Button playButton = findViewById(R.id.play_button);
        Button scoreboardButton = findViewById(R.id.scoreboard_button);
        helpButton.setOnClickListener(v -> {
            // Start the HelpActivity
            Intent intent = new Intent(MenuActivity.this, HelpActivity.class);
            startActivity(intent);
        });

        playButton.setOnClickListener(v -> {
            // Start the GameActivity
            Intent intent = new Intent(MenuActivity.this, GameActivity.class);
            startActivity(intent);
        });

        scoreboardButton.setOnClickListener(v -> {
            // Start the ScoreboardActivity
            Intent intent = new Intent(MenuActivity.this, ScoreboardActivity.class);
            startActivity(intent);
        });
    }
}