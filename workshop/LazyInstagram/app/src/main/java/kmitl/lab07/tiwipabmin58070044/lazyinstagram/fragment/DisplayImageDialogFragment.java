package kmitl.lab07.tiwipabmin58070044.lazyinstagram.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

/**
 * Created by tiwip on 10/9/2017.
 */

public class DisplayImageDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialog =
                new AlertDialog.Builder(getActivity());

        LayoutInflater inflater =
                getActivity()
                        .getLayoutInflater();

//        View view = inflater.inflate();
        return alertDialog.create();
    }
}
