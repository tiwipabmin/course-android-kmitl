package kmitl.lab07.tiwipabmin58070044.mylazyinstagram.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import kmitl.lab07.tiwipabmin58070044.mylazyinstagram.MainActivity;
import kmitl.lab07.tiwipabmin58070044.mylazyinstagram.R;

/**
 * Created by tiwip on 10/13/2017.
 */

public class SwitchUserDialogFragment extends DialogFragment
        implements View.OnClickListener{

        public interface DialogListener {
            public void onSignInClicked(String username);
        }

        private Button btnCancel, btnSignIn;
        private EditText etUsername;
        private DialogListener listener;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            listener  = (MainActivity) getActivity();
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder alertDialog =
                    new AlertDialog
                            .Builder(getActivity());

            LayoutInflater layoutInflater =
                    getActivity()
                            .getLayoutInflater();
            View view =
                    layoutInflater
                            .inflate(R.layout.dialog_signin, null);

            bindWidget(view);

            btnSignIn.setOnClickListener(this);
            btnCancel.setOnClickListener(this);
            alertDialog.setView(view);

            return alertDialog
                    .create();
        }

    private void bindWidget(View view){

        btnCancel = (Button) view.findViewById(R.id.btnCancel);
        btnSignIn = (Button) view.findViewById(R.id.btnSignIn);

        etUsername = (EditText) view.findViewById(R.id.etUsername);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnCancel){

            SwitchUserDialogFragment
                    .this
                    .getDialog()
                    .cancel();

        } else if(v.getId() == R.id.btnSignIn){

            listener.onSignInClicked(etUsername
                    .getText()
                    .toString()
                    .trim());

            SwitchUserDialogFragment
                    .this
                    .getDialog()
                    .cancel();
        }
    }
}
