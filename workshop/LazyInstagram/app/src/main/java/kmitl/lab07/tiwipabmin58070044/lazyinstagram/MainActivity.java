package kmitl.lab07.tiwipabmin58070044.lazyinstagram;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import kmitl.lab07.tiwipabmin58070044.lazyinstagram.adapter.PostAdapter;
import kmitl.lab07.tiwipabmin58070044.lazyinstagram.api.PostsModel;
import kmitl.lab07.tiwipabmin58070044.lazyinstagram.api.UserProfile;
import kmitl.lab07.tiwipabmin58070044.lazyinstagram.fragment.SwitchUserDialogFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, SwitchUserDialogFragment.DialogListener, PostAdapter.OnViewPressedListener{

    private TextView tvUser, tvPost, tvFollowing,
            tvFollower, tvBio;
    private ImageView ivUser;
    private Button btnSwitch, btnSwitchUser;
    private UserProfile userProfile;
    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private List<PostsModel> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindWidget();
        btnSwitch.setOnClickListener(this);
        btnSwitchUser.setOnClickListener(this);

        getUserProfile("cartoon");
    }

    private void bindWidget(){

        tvUser = (TextView) findViewById(R.id.tvUser);
        tvPost = (TextView) findViewById(R.id.tvPost);
        tvFollowing = (TextView) findViewById(R.id.tvFollowing);
        tvFollower = (TextView) findViewById(R.id.tvFollower);
        ivUser = (ImageView) findViewById(R.id.ivUser);
        tvBio = (TextView) findViewById(R.id.tvBio);
        btnSwitch = (Button) findViewById(R.id.btnSwitch);
        btnSwitchUser = (Button) findViewById(R.id.btnSwitchUser);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }

    private void initialInstance(){}

    private void showProfile(UserProfile userProfile){

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
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(postAdapter);
    }

    private void showSwitchUserDialog(){
        DialogFragment switchUser = new SwitchUserDialogFragment();
        switchUser.show(getSupportFragmentManager(), "switchUser");
    }

    private void getUserProfile(String usrName){

        Call<UserProfile> call = ConnectionServer.getConnectionServer().getLazyInstagramApi().getProfile(usrName);
        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if(response.isSuccessful()){
                    userProfile = response.body();
                    showProfile(userProfile);
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(R.id.btnSwitch == v.getId()){
            switch (btnSwitch.getText().toString()) {
                case "List":
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    postAdapter.setItemLayout(PostAdapter.LIST);
                    btnSwitch.setText(String.format("%s", "Grid"));
                    break;
                case "Grid":
                    recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
                    postAdapter.setItemLayout(PostAdapter.GRID);
                    btnSwitch.setText(String.format("%s", "List"));
                    break;
            }
        } else if(R.id.btnSwitchUser == v.getId()) {
            showSwitchUserDialog();
        }
    }

    @Override
    public void onSignInClicked(String username) {
        getUserProfile(username);
    }

    @Override
    public void OnLongPressedListener(int position) {
        Toast.makeText(MainActivity.this, String.valueOf(posts.get(position).getLike()), Toast.LENGTH_SHORT).show();
    }
}
