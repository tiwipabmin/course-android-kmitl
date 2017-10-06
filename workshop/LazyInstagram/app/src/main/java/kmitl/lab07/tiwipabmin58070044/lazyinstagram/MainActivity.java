package kmitl.lab07.tiwipabmin58070044.lazyinstagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import kmitl.lab07.tiwipabmin58070044.lazyinstagram.adapter.PostAdapter;
import kmitl.lab07.tiwipabmin58070044.lazyinstagram.api.LazyInstagramApi;
import kmitl.lab07.tiwipabmin58070044.lazyinstagram.api.UserProfile;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getUserProfile("cartoon");

        PostAdapter postAdapter =
                new PostAdapter(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(postAdapter);
    }

    private void getUserProfile(String usrName){
        OkHttpClient client = new OkHttpClient
                .Builder()
                .build();

        Retrofit retrofit = new Retrofit
                .Builder()
                .client(client)
                .baseUrl(LazyInstagramApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LazyInstagramApi lazyInstagramApi =
                retrofit.create(LazyInstagramApi.class);

        Call<UserProfile> call = lazyInstagramApi.getProfile(usrName);
        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if(response.isSuccessful()){
                    UserProfile userProfile = response.body();

                    TextView tvUser = (TextView) findViewById(R.id.tvUser);
                    TextView tvPost = (TextView) findViewById(R.id.tvPost);
                    TextView tvFollowing = (TextView) findViewById(R.id.tvFollowing);
                    TextView tvFollower = (TextView) findViewById(R.id.tvFollower);
                    ImageView ivUser = (ImageView) findViewById(R.id.ivUser);
                    TextView tvBio = (TextView) findViewById(R.id.tvBio);

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
