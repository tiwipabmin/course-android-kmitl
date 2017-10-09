package kmitl.lab07.tiwipabmin58070044.lazyinstagram.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import java.util.List;

import kmitl.lab07.tiwipabmin58070044.lazyinstagram.R;
import kmitl.lab07.tiwipabmin58070044.lazyinstagram.api.PostsModel;

/**
 * Created by student on 10/6/2017 AD.
 */

class Holder extends RecyclerView.ViewHolder{

    public ImageView image;
    public TextView tvComment, tvLike;
    public int itemLayout;

    public Holder(View itemView) {
        super(itemView);
        if(itemView.getId() == R.id.grid) {
            image =
                    (ImageView) itemView.findViewById(R.id.ivImage);

            itemLayout = PostAdapter.GRID;

        } else if (itemView.getId() == R.id.list) {
            image =
                    (ImageView) itemView.findViewById(R.id.ivImage);
            tvComment =
                    (TextView) itemView.findViewById(R.id.tvComment);
            tvLike =
                    (TextView) itemView.findViewById(R.id.tvLike);

            itemLayout = PostAdapter.LIST;
        }

    }
}

public class PostAdapter extends
        RecyclerView.Adapter<Holder> {

    public static final int GRID = 0;
    public static final int LIST = 1;

    private List<PostsModel> posts;
    private Context context;
    private int itemLayout = GRID;

    public PostAdapter(Context context, List<PostsModel> posts) {
        this.context = context;
        this.posts = posts;
    }

    public PostAdapter(Context context) {
        this.context = context;
    }

    public void PostAdapter(){}

    public void setPosts(List<PostsModel> posts) {
        this.posts = posts;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater =
                LayoutInflater.from(parent.getContext());

        View itemView = null;

        if(viewType == 0) {
            itemView =
                    inflater.inflate(R.layout.post_item, null, false);
            itemView.setId(R.id.grid);
        } else if (viewType == 1){
            itemView =
                    inflater.inflate(R.layout.list_item, null, false);
            itemView.setId(R.id.list);
        }

        Holder holder = new Holder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        if(holder.itemLayout == LIST){
            holder.tvComment.setText(holder
                    .tvComment
                    .getText()
                    .toString()
                    .concat(String.valueOf(posts.get(position).getComment())));

            holder.tvLike.setText(holder
                    .tvLike
                    .getText()
                    .toString()
                    .concat(String.valueOf(posts.get(position).getLike())));
        }
        Glide.with(context).load(posts
                .get(position)
                .getUrl())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    @Override
    public int getItemViewType(int position) {
        return itemLayout;
    }

    public void setItemLayout(int itemLayout) {
        this.itemLayout = itemLayout;
    }
}
