package kmitl.lab07.tiwipabmin58070044.lazyinstagram.api;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tiwip on 10/8/2017.
 */

public class PostsModel implements Parcelable{

    private int comment;
    private int like;
    private String url;

    protected PostsModel(Parcel in) {
        comment = in.readInt();
        like = in.readInt();
        url = in.readString();
    }

    public static final Creator<PostsModel> CREATOR = new Creator<PostsModel>() {
        @Override
        public PostsModel createFromParcel(Parcel in) {
            return new PostsModel(in);
        }

        @Override
        public PostsModel[] newArray(int size) {
            return new PostsModel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(comment);
        dest.writeInt(like);
        dest.writeString(url);
    }
}
