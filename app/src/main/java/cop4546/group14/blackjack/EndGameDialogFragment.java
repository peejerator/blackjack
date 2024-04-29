package cop4546.group14.blackjack;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;


public class EndGameDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final TextView statusTextView = new TextView(requireActivity());

        if (getArguments() != null) {
            String status = getArguments().getString(GameActivity.GAME_STATUS, "unknown");
            statusTextView.setText(status);
            statusTextView.setTextSize(40);
            statusTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }
        else {
            statusTextView.setText("unknown");
        }

        return new AlertDialog.Builder(requireActivity(), R.style.Theme_BlackJack_Dialog)
                .setTitle(R.string.hand_over)
                .setCancelable(false)
                .setView(statusTextView)
                .setPositiveButton(R.string.ok, null)
                .create();

    }
}