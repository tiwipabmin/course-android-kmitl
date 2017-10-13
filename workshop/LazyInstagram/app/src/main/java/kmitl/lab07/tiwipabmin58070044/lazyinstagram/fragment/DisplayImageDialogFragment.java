package kmitl.lab07.tiwipabmin58070044.lazyinstagram.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import kmitl.lab07.tiwipabmin58070044.lazyinstagram.R;
import kmitl.lab07.tiwipabmin58070044.lazyinstagram.api.PostsModel;

/**
 * Created by tiwip on 10/9/2017.
 */

public class DisplayImageDialogFragment extends DialogFragment {

    private ImageView ivUserProfile, ivImage;
    private TextView tvUsername, tvComments, tvLikes;
    private String urlUserProfile, urlImage, username;
    private int comments, likes;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        username = args.getString("username");
        urlUserProfile = args.getString("urlUserProfile");
        urlImage = args.getString("urlImage");
        comments = args.getInt("comments");
        likes = args.getInt("likes");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialog =
                new AlertDialog.Builder(getActivity());

        LayoutInflater inflater =
                getActivity()
                        .getLayoutInflater();

        View view =
                inflater
                        .inflate(R.layout.dialog_displayimage, null);

        bindWidget(view);
        update();
        alertDialog.setView(view);
        return alertDialog.create();
    }

    private void bindWidget(View view){

        ivUserProfile = (ImageView) view.findViewById(R.id.ivUserProfile);
        ivImage = (ImageView) view.findViewById(R.id.ivImage);

        tvUsername = (TextView) view.findViewById(R.id.tvUsername);
        tvComments = (TextView) view.findViewById(R.id.tvComments);
        tvLikes = (TextView) view.findViewById(R.id.tvLikes);
    }

    private void update(){

        Glide.with(getActivity())
                .load(urlUserProfile)
                .into(ivUserProfile);
        Glide.with(getActivity())
                .load(urlImage)
                .into(ivImage);

        tvUsername.setText("@".concat(username));
        tvComments.setText(String.valueOf(comments));
        tvLikes.setText(String.valueOf(likes));
    }

    public static DisplayImageDialogFragment newInstance(PostsModel postsModel, String urlUserProfile,
                                                         String username) {
        Bundle args = new Bundle();
        DisplayImageDialogFragment fragment = new DisplayImageDialogFragment();
        args.putString("username", username);
        args.putString("urlUserProfile", urlUserProfile);
        args.putString("urlImage", postsModel.getUrl());
        args.putInt("comments", postsModel.getComment());
        args.putInt("likes", postsModel.getLike());
        fragment.setArguments(args);
        return fragment;
    }

}
