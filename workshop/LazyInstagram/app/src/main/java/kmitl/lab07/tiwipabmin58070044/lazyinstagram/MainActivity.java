package kmitl.lab07.tiwipabmin58070044.lazyinstagram;

import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import kmitl.lab07.tiwipabmin58070044.lazyinstagram.adapter.PostAdapter;
import kmitl.lab07.tiwipabmin58070044.lazyinstagram.api.LazyInstagramApi;
import kmitl.lab07.tiwipabmin58070044.lazyinstagram.api.UserProfile;
import kmitl.lab07.tiwipabmin58070044.lazyinstagram.fragment.GridFragment;
import kmitl.lab07.tiwipabmin58070044.lazyinstagram.fragment.ListFragment;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView tvUser, tvPost, tvFollowing,
            tvFollower, tvBio;
    private ImageView ivUser;
    private String[] data = {
        "http://api.learn2crack.com/android/images/donut.png",
                "http://api.learn2crack.com/android/images/eclair.png",
                "http://api.learn2crack.com/android/images/froyo.png",
                "http://api.learn2crack.com/android/images/ginger.png"
    };
    private FragmentManager imageFragment;
    private Button btnSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindWidget();
        setWidgetEventListener();

        ConnectionServer connectionSever = ConnectionServer.getConnectionServer();

        getUserProfile(connectionSever.getRetrofit(), "cartoon");

        imageFragment = getSupportFragmentManager();
        imageFragment.beginTransaction()
                .replace(R.id.imageFragment, new GridFragment().newInstance(data))
                .commit();
    }

    private void bindWidget(){

        tvUser = (TextView) findViewById(R.id.tvUser);
        tvPost = (TextView) findViewById(R.id.tvPost);
        tvFollowing = (TextView) findViewById(R.id.tvFollowing);
        tvFollower = (TextView) findViewById(R.id.tvFollower);
        ivUser = (ImageView) findViewById(R.id.ivUser);
        tvBio = (TextView) findViewById(R.id.tvBio);
        btnSwitch = (Button) findViewById(R.id.btnSwitch);
    }

    private void setWidgetEventListener(){

        btnSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (btnSwitch.getText().toString()) {
                    case "List": imageFragment.beginTransaction()
                            .replace(R.id.imageFragment, new ListFragment().newInstance(data))
                            .commit();
                        btnSwitch.setText(String.format("%s", "Grid"));
                        break;
                    case "Grid": imageFragment.beginTransaction()
                            .replace(R.id.imageFragment, new GridFragment().newInstance(data))
                            .commit();
                        btnSwitch.setText(String.format("%s", "List"));
                        break;
                }
            }
        });
    }

    private void getUserProfile(Retrofit retrofit, String usrName){

        LazyInstagramApi lazyInstagramApi =
                retrofit.create(LazyInstagramApi.class);

        Call<UserProfile> call = lazyInstagramApi.getProfile(usrName);
        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if(response.isSuccessful()){
                    UserProfile userProfile = response.body();

                    tvUser.setText("@" + userProfile.getUser());
                    tvPost.setText(String.valueOf(userProfile.getPost()));
                    tvFollowing.setText(String.valueOf(userProfile.getFollowing()));
                    tvFollower.setText(String.valueOf(userProfile.getFollower()));
                    tvBio.setText(userProfile.getBio());
                    Glide.with(MainActivity.this)
                            .load(userProfile.getUrlProfile())
                            .into(ivUser);
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }
        });
    }
}
