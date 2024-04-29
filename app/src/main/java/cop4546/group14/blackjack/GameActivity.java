package cop4546.group14.blackjack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cop4546.group14.blackjack.repo.PlayerRepo;

import cop4546.group14.blackjack.model.Game;
import cop4546.group14.blackjack.model.Player;

public class GameActivity extends AppCompatActivity
        implements SubmitDialogFragment.onNameEnteredListener {
    public static final String GAME_STATUS = "cop4546.group14.blackjack.game_status";
    Game game;
    private PlayerRepo playerRepo;

    private LinearLayout mPlayerCards;
    private LinearLayout mDealerCards;

    private View mChips;
    private ImageView mFiftyChip;
    private ImageView mHundredChip;
    private ImageView mFiveHundredChip;

    private Button mPlayButton;

    private Button mResetButton;

    private LinearLayout mEndGameButtons;
//    private Button mPlayAgainButton;

    private LinearLayout mGameButtons;

    private TextView mBetAmount;

    private TextView mPlayerChips;

    private TextView mPlayerTotal;
    private TextView mDealerTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        playerRepo = PlayerRepo.getInstance(getApplicationContext());

        game = new Game();

        mBetAmount = findViewById(R.id.bet);
        mPlayerChips = findViewById(R.id.player_chips);

        mPlayerCards = findViewById(R.id.player_cards_layout);

        mDealerCards = findViewById(R.id.dealer_cards_layout);

        mChips = findViewById(R.id.chips);
        mFiftyChip = findViewById(R.id.fifty_chip);
        mHundredChip = findViewById(R.id.hundred_chip);
        mFiveHundredChip = findViewById(R.id.five_hundred_chip);

        mResetButton = findViewById(R.id.reset_button);

        mPlayButton = findViewById(R.id.play_button);

        mEndGameButtons = findViewById(R.id.end_game_buttons);

        mGameButtons = findViewById(R.id.game_buttons);

        mPlayerTotal = findViewById(R.id.player_total);
        mDealerTotal = findViewById(R.id.dealer_total);

        mResetButton.setOnClickListener(v -> {
            Thread thread = new Thread(() -> {
                game.resetBet();
                GameActivity.this.runOnUiThread(() -> {
                    mBetAmount.setText(getString(R.string.current_pot, 0));
                    mPlayerChips.setText(getString(R.string.player_chips, game.getChips()));
                });
            });
            thread.start();
        });

        mFiftyChip.setOnClickListener(v -> {
            Thread thread = new Thread(() -> {
                if (game.getHandSize() == 0)
                    if (game.setBet(game.getBet() + 50)) {
                        GameActivity.this.runOnUiThread(() -> {
                            mBetAmount.setText(getString(R.string.current_pot, game.getBet()));
                            mPlayerChips.setText(getString(R.string.player_chips, game.getChips() - game.getBet()));
                        });
                    }
            });

            thread.start();
        });

        mHundredChip.setOnClickListener(v -> {
            Thread thread = new Thread(() -> {
                if (game.getHandSize() == 0)
                    if (game.setBet(game.getBet() + 100)) {
                        GameActivity.this.runOnUiThread(() -> {
                            mBetAmount.setText(getString(R.string.current_pot, game.getBet()));
                            mPlayerChips.setText(getString(R.string.player_chips, game.getChips() - game.getBet()));
                        });
                    }
            });

            thread.start();
        });

        mFiveHundredChip.setOnClickListener(v -> {
            Thread thread = new Thread(() -> {
                if (game.getHandSize() == 0)
                    if (game.setBet(game.getBet() + 500)) {
                        GameActivity.this.runOnUiThread(() -> {
                            mBetAmount.setText(getString(R.string.current_pot, game.getBet()));
                            mPlayerChips.setText(getString(R.string.player_chips, game.getChips() - game.getBet()));
                        });
                    }
            });

            thread.start();
        });

//        mPlayAgainButton.setOnClickListener(v -> {
//            resetGame();
//        });

        // play again button
        mEndGameButtons.getChildAt(0).setOnClickListener(v -> {
            resetGame();
        });

        // submit score button
        mEndGameButtons.getChildAt(1).setOnClickListener(v -> {
//            Player player = playerRepo.getPlayer();
//            player.setChips(game.getChips());
//            playerRepo.updatePlayer(player);
//            finish();
            SubmitDialogFragment submitDialogFragment = new SubmitDialogFragment();
            submitDialogFragment.show(getSupportFragmentManager(), "submit_dialog");
        });

        // stand button
        mGameButtons.getChildAt(0).setOnClickListener(v -> {
            if (game.getState() != Game.State.PLAYER_TURN) return;

            if (game.getHandSize() > 0) {
                game.dealerPlay();
                showDealerCards();
                mDealerTotal.setVisibility(View.VISIBLE);
                mDealerTotal.setText(getString(R.string.dealer_total, game.getDealerTotal()));
                game.settle();
                endGame();
            }
        });

        // hit button
        mGameButtons.getChildAt(1).setOnClickListener(v -> {
            if (game.getState() != Game.State.PLAYER_TURN) return;

            if (game.getHandSize() > 0) {
                game.hit();
                showPlayerCards();
                mPlayerTotal.setText(getString(R.string.player_total, game.getPlayerTotal()));
                game.checkBust();
                if (game.getState() == Game.State.BUST) {
                    showDealerCards();
                    mDealerTotal.setVisibility(View.VISIBLE);
                    mDealerTotal.setText(getString(R.string.dealer_total, game.getDealerTotal()));
                    endGame();
                }
            }
        });

        // double button
        mGameButtons.getChildAt(2).setOnClickListener(v -> {
            if (game.getState() != Game.State.PLAYER_TURN) return;

            if (game.getHandSize() == 2 && game.setBet(game.getBet() * 2)) {
                mBetAmount.setText(getString(R.string.current_pot, game.getBet()));
                mPlayerChips.setText(getString(R.string.player_chips, game.getChips() - game.getBet()));
                game.hit();
                showPlayerCards();
                mPlayerTotal.setVisibility(View.VISIBLE);
                mPlayerTotal.setText(getString(R.string.player_total, game.getPlayerTotal()));
                game.dealerPlay();
                showDealerCards();
                mDealerTotal.setVisibility(View.VISIBLE);
                mDealerTotal.setText(getString(R.string.dealer_total, game.getDealerTotal()));
                game.settle();
                endGame();
            }
        });

        // play button
        mPlayButton.setOnClickListener(v -> {
            if (game.getHandSize() == 0 && game.getBet() > 0) {
                mPlayerCards.setVisibility(View.VISIBLE);
                mDealerCards.setVisibility(View.VISIBLE);
                game.deal();
                dealerStart();
                mPlayerTotal.setVisibility(View.VISIBLE);
                mPlayerTotal.setText(getString(R.string.player_total, game.getPlayerTotal()));
                game.checkBlackjack();
                if (game.getState() == Game.State.BLACKJACK) {
                    showDealerCards();
                    mDealerTotal.setVisibility(View.VISIBLE);
                    mDealerTotal.setText(getString(R.string.dealer_total, game.getDealerTotal()));
                    endGame();
                }
                mPlayButton.setVisibility(View.INVISIBLE);
                mGameButtons.setVisibility(View.VISIBLE);
                hideChipButtons();
                showPlayerCards();
            }
        });

        resetGame();
    }

    @Override
    public void onNameEntered(String name) {
        Log.d("SubmitDialogFragment", "onNameEntered: " + name);

        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(name);
        boolean hasSpecialChar = m.find();

        if (name.length() == 0 || hasSpecialChar) {
            Toast.makeText(this, "Invalid name", Toast.LENGTH_SHORT).show();
            return;
        }

        Player player = new Player(name, game.getChips());
        playerRepo.addPlayer(player);
        finish();
    }

    public void hideChipButtons() {
        mChips.setVisibility(View.INVISIBLE);
    }

    public void showChipButtons() {
        mChips.setVisibility(View.VISIBLE);
    }

    public void endGame() {
        mPlayerChips.setText(getString(R.string.player_chips, game.getChips() - game.getBet()));
        mGameButtons.setVisibility(View.GONE);
        Bundle bundle = new Bundle();
        bundle.putString(GAME_STATUS, stateToText(game.getState()));
        EndGameDialogFragment endGameDialogFragment = new EndGameDialogFragment();
        endGameDialogFragment.setArguments(bundle);
        endGameDialogFragment.show(getSupportFragmentManager(), "end_game_dialog");
        mEndGameButtons.setVisibility(View.VISIBLE);
    }

    public String stateToText(Game.State state) {
        switch (state) {
            case PLAYER_TURN:
                return "Player's turn";
            case DEALER_TURN:
                return "Dealer's turn";
            case BLACKJACK:
                return "Blackjack!";
            case BUST:
                return "Bust!";
            case PUSH:
                return "Push!";
            case WIN:
                return "You win!";
            case LOSS:
                return "You lose!";
            default:
                return "Unknown";
        }
    }

    public void showPlayerCards() {
        // show the player's hand
        String[] cards = game.getPlayerHand();
        Log.d("Player Cards", Arrays.toString(cards));

        for (int i = 0; i < cards.length; ++i) {
            int id = getResources().getIdentifier(cards[i].toLowerCase(), "drawable", getPackageName());
            ImageView card = (ImageView) mPlayerCards.getChildAt(i);
            card.setImageResource(id);
            card.setVisibility(View.VISIBLE);
        }
    }

    public void showDealerCards() {
        // show the dealer's hand
        String[] cards = game.getDealerHand();
        Log.d("Dealer Cards", Arrays.toString(cards));

        for (int i = 0; i < cards.length; ++i) {
            int id = getResources().getIdentifier(cards[i].toLowerCase(), "drawable", getPackageName());
            ImageView card = (ImageView) mDealerCards.getChildAt(i);
            card.setImageResource(id);
            card.setVisibility(View.VISIBLE);
        }
    }

    public void dealerStart() {
        // show the dealer's hand
        String[] cards = game.getDealerHand();
        Log.d("Dealer Cards", Arrays.toString(cards));

        ImageView firstCard = (ImageView) mDealerCards.getChildAt(0);
        int id = R.drawable.card_back;
        firstCard.setImageResource(id);
        firstCard.setVisibility(View.VISIBLE);

        id = getResources().getIdentifier(cards[1].toLowerCase(), "drawable", getPackageName());
        ImageView secondCard = (ImageView) mDealerCards.getChildAt(1);
        secondCard.setImageResource(id);
        secondCard.setVisibility(View.VISIBLE);

        mDealerTotal.setVisibility(View.VISIBLE);
        mDealerTotal.setText(getString(R.string.dealer_total, game.getDealerShowingValue()));
    }

    public void hidePlayerCards() {
        // hide the player's hand
        for (int i = 0; i < 2; ++i) {
            ImageView card = (ImageView) mPlayerCards.getChildAt(i);
            card.setVisibility(View.INVISIBLE);
        }

        for (int i = 2; i < 11; ++i) {
            ImageView card = (ImageView) mPlayerCards.getChildAt(i);
            card.setVisibility(View.GONE);
        }
    }

    public void hideDealerCards() {
        // hide the dealer's hand
        for (int i = 0; i < 2; ++i) {
            ImageView card = (ImageView) mDealerCards.getChildAt(i);
            card.setVisibility(View.INVISIBLE);
        }

        for (int i = 2; i < 11; ++i) {
            ImageView card = (ImageView) mDealerCards.getChildAt(i);
            card.setVisibility(View.GONE);
        }
    }

    public void resetGame() {
        Thread thread = new Thread(() -> {
            game.clearHands();
            game.shuffle();
            GameActivity.this.runOnUiThread(() -> {
                mPlayerChips.setText(getString(R.string.player_chips, game.getChips()));
                mBetAmount.setText(getString(R.string.current_pot, 0));
                mEndGameButtons.setVisibility(View.GONE);
                mGameButtons.setVisibility(View.GONE);
                mPlayButton.setVisibility(View.VISIBLE);
                hidePlayerCards();
                hideDealerCards();
                showChipButtons();
                mDealerTotal.setVisibility(View.INVISIBLE);
                mPlayerTotal.setVisibility(View.INVISIBLE);
            });
        });

        thread.start();
    }
}