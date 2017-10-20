package kmitl.lab07.tiwipabmin58070044.mylazyinstagram.fragment;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import kmitl.lab07.tiwipabmin58070044.mylazyinstagram.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProgressFragment extends DialogFragment {

    private ImageView download;
    private Animation animLoading;

    public ProgressFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_progress, container, false);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        getDialog().setCancelable(false);
        getDialog().setCanceledOnTouchOutside(false);

        animLoading = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);

        download = (ImageView) rootView.findViewById(R.id.loading);
        download.setAnimation(animLoading);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        download.clearAnimation();
    }
}
