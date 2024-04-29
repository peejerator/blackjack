package cop4546.group14.blackjack;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;


public class SubmitDialogFragment extends DialogFragment {

    public interface onNameEnteredListener {
        void onNameEntered(String name);
    }

    private onNameEnteredListener mListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final EditText nameEditText = new EditText(requireActivity());
        nameEditText.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        nameEditText.setMaxLines(1);

        return new AlertDialog.Builder(requireActivity(), R.style.Theme_BlackJack_Dialog)
                .setTitle(R.string.enter_name)
                .setView(nameEditText)
                .setPositiveButton(R.string.ok, (dialog, whichButton) -> {
                    String name = nameEditText.getText().toString();
                    mListener.onNameEntered(name.trim());
                })
                .setNegativeButton(R.string.cancel, null)
                .create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (onNameEnteredListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}