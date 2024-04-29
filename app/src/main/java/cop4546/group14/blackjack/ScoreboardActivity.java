package cop4546.group14.blackjack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import cop4546.group14.blackjack.model.Player;
import cop4546.group14.blackjack.repo.PlayerRepo;

public class ScoreboardActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private PlayerRepo mPlayerRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        mRecyclerView = findViewById(R.id.scoreboard_recycler_view);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getApplicationContext());

        mRecyclerView.setLayoutManager(layoutManager);

        mPlayerRepo = PlayerRepo.getInstance(getApplicationContext());

        // Get the top 10 players from the database
        LiveData<List<Player>> players = mPlayerRepo.getTop10Bets();

        players.observe(this, playersList -> {
            mRecyclerView.setAdapter(new ScoreboardAdapter(playersList));
        });
    }

    private class ScoreboardHolder extends RecyclerView.ViewHolder {

        private Player mPlayer;
        private final TextView mPlayerTextView;

        public ScoreboardHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.recycler_view_items, parent, false));
            mPlayerTextView = itemView.findViewById(R.id.player_text_view);
        }

        public void bind(Player player, int position) {
            mPlayer = player;
            mPlayerTextView.setText(getString(R.string.scoreboard_entry, position + 1, mPlayer.getPlayerName(), mPlayer.getChips()));
            //mPlayerTextView.setText(mPlayer.getPlayerName() + " - " + mPlayer.getChips());
        }
    }

    private class ScoreboardAdapter extends RecyclerView.Adapter<ScoreboardHolder> {

        private final List<Player> mPlayers;

        public ScoreboardAdapter(List<Player> players) {
            mPlayers = players;
        }

        @NonNull
        @Override
        public ScoreboardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
            return new ScoreboardHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(ScoreboardHolder holder, int position){
            holder.bind(mPlayers.get(position), position);
        }

        @Override
        public int getItemCount() {
            return mPlayers.size();
        }
    }
}