package kmitl.lab07.tiwipabmin58070044.lazyinstagram.api;

import android.os.Parcel;

/**
 * Created by tiwip on 10/8/2017.
 */

public class PostsModel {

    private int comment;
    private int like;
    private String url;

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
