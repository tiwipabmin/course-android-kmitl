package kmitl.lab07.tiwipabmin58070044.mylazyinstagram;

import kmitl.lab07.tiwipabmin58070044.mylazyinstagram.api.LazyInstagramApi;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tiwip on 10/13/2017.
 */

public class ConnectionServer {

    private static ConnectionServer connectionServer;
    private Retrofit retrofit;
    private LazyInstagramApi lazyInstagramApi;

    private ConnectionServer(){
        OkHttpClient client = new OkHttpClient
                .Builder()
                .build();

        retrofit = new Retrofit
                .Builder()
                .client(client)
                .baseUrl(LazyInstagramApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                //        ScalarsConverterFactory.create() มองค่าเดียว นั่นคือ String Integer Float
                .build();

        lazyInstagramApi = retrofit.create(LazyInstagramApi.class);
    }

    public static ConnectionServer getConnectionServer(){
        if (connectionServer == null){
            connectionServer = new ConnectionServer();
        }
        return connectionServer;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public LazyInstagramApi getLazyInstagramApi() {
        return lazyInstagramApi;
    }

}
