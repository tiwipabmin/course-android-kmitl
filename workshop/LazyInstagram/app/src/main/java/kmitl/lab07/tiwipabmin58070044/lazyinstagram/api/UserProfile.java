package kmitl.lab07.tiwipabmin58070044.lazyinstagram.api;

/**
 * Created by student on 10/6/2017 AD.
 */

public class UserProfile {

    private String user;
    private int following;
    private int follower;
    private String bio;
    private int post;
    private boolean isFollow;
    private String urlProfile;

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public String getUrlProfile() {
        return urlProfile;
    }

    public void setUrlProfile(String urlProfile) {
        this.urlProfile = urlProfile;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public int getFollower() {
        return follower;
    }

    public void setFollower(int follower) {
        this.follower = follower;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public boolean isFollow() {
        return isFollow;
    }

    public void setFollow(boolean follow) {
        isFollow = follow;
    }
}
