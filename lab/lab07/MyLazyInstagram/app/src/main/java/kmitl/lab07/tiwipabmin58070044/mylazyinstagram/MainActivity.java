package kmitl.lab07.tiwipabmin58070044.mylazyinstagram;

import android.app.Dialog;
import android.support.annotation.IdRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.List;

import kmitl.lab07.tiwipabmin58070044.mylazyinstagram.adapter.PostAdapter;
import kmitl.lab07.tiwipabmin58070044.mylazyinstagram.api.PostsModel;
import kmitl.lab07.tiwipabmin58070044.mylazyinstagram.api.UserProfile;
import kmitl.lab07.tiwipabmin58070044.mylazyinstagram.fragment.AlertDialogFragment;
import kmitl.lab07.tiwipabmin58070044.mylazyinstagram.fragment.DisplayImageDialogFragment;
import kmitl.lab07.tiwipabmin58070044.mylazyinstagram.fragment.ProgressFragment;
import kmitl.lab07.tiwipabmin58070044.mylazyinstagram.fragment.SwitchUserDialogFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements OnTabSelectListener,SwitchUserDialogFragment.DialogListener, PostAdapter.OnViewPressedListener, View.OnClickListener {

    private TextView tvUser, tvPost, tvFollowing,
            tvFollower, tvBio;
    private ImageView ivUser;
    private BottomBar bottomBar;
    private Button btnSwitchUser;
    private UserProfile userProfile;
    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private List<PostsModel> posts;
    private String username = "cartoon";
    private ProgressFragment progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progress = new ProgressFragment();

        bindWidget();
        btnSwitchUser.setOnClickListener(this);

        getUserProfile(username);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("username", username);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            username = savedInstanceState.getString("username");
        }
    }

    private void bindWidget() {

        tvUser = (TextView) findViewById(R.id.tvUser);
        tvPost = (TextView) findViewById(R.id.tvPost);
        tvFollowing = (TextView) findViewById(R.id.tvFollowing);
        tvFollower = (TextView) findViewById(R.id.tvFollower);
        ivUser = (ImageView) findViewById(R.id.ivUser);
        tvBio = (TextView) findViewById(R.id.tvBio);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        bottomBar = (BottomBar) findViewById(R.id.tab_bar);

        btnSwitchUser = (Button) findViewById(R.id.btnSwitchUser);
    }

    private void showProfile(UserProfile userProfile) {

        tvUser.setText("@".concat(userProfile.getUser()));
        tvPost.setText(String.valueOf(userProfile.getPost()));
        tvFollowing.setText(String.valueOf(userProfile.getFollowing()));
        tvFollower.setText(String.valueOf(userProfile.getFollower()));
        tvBio.setText(userProfile.getBio());
        Glide.with(MainActivity.this)
                .load(userProfile.getUrlProfile())
                .into(ivUser);

        posts = userProfile.getPosts();

        postAdapter = new PostAdapter(this, posts);
        postAdapter.setListener(MainActivity.this);
        recyclerView.setAdapter(postAdapter);

        bottomBar.setOnTabSelectListener(this);
    }

    private void getUserProfile(String usrName) {

        Call<UserProfile> call = ConnectionServer.getConnectionServer().getLazyInstagramApi().getProfile(usrName);
        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if (response.isSuccessful()) {
                    userProfile = response.body();
                    showProfile(userProfile);
                    if(progress.isVisible()) {
                        progress.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(MainActivity.this, String.valueOf(t.getMessage()), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onSignInClicked(String username) {
        if (username.equals("android") || username.equals("nature") || username.equals("cartoon")) {
            this.username = username;
            getUserProfile(this.username);
            progress.show(getSupportFragmentManager(), null);
        } else {
            DialogFragment alertDialog = new AlertDialogFragment();
            alertDialog.show(getSupportFragmentManager(), "alertDialog");
        }
    }

    @Override
    public void OnLongPressedListener(int position) {
        DialogFragment displayImage =
                new DisplayImageDialogFragment()
                        .newInstance(posts.get(position), userProfile.getUrlProfile(), userProfile.getUser());
        displayImage.show(getSupportFragmentManager(), "displayImage");
        postAdapter.setDisplayImage(displayImage);
    }

    @Override
    public void OnSingleTapUp(DialogFragment displayImage) {
        if (displayImage != null) {
            displayImage.dismiss();
            postAdapter.setDisplayImage(null);
        }
    }

    @Override
    public void onTabSelected(@IdRes int tabId) {
        if (tabId == R.id.tab_grid) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
            postAdapter.setItemLayout(PostAdapter.GRID);
        } else if (tabId == R.id.tab_list) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            postAdapter.setItemLayout(PostAdapter.LIST);
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnSwitchUser){
            DialogFragment switchUser = new SwitchUserDialogFragment();
            switchUser.show(getSupportFragmentManager(), "switchUser");
        }
    }
}
