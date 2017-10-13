package kmitl.lab07.tiwipabmin58070044.lazyinstagram.adapter;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import kmitl.lab07.tiwipabmin58070044.lazyinstagram.MainActivity;
import kmitl.lab07.tiwipabmin58070044.lazyinstagram.R;
import kmitl.lab07.tiwipabmin58070044.lazyinstagram.api.PostsModel;

/**
 * Created by student on 10/6/2017 AD.
 */

class Holder extends RecyclerView.ViewHolder {

    public ImageView image;
    public TextView tvComment, tvLike;
    public int itemLayout;

    public Holder(View itemView) {
        super(itemView);
        if (itemView.getId() == R.id.grid) {
            image =
                    (ImageView) itemView.findViewById(R.id.ivImage);

            itemLayout = PostAdapter.GRID;

        } else if (itemView.getId() == R.id.list) {
            image =
                    (ImageView) itemView.findViewById(R.id.ivImage);
            tvComment =
                    (TextView) itemView.findViewById(R.id.tvComments);
            tvLike =
                    (TextView) itemView.findViewById(R.id.tvLikes);

            itemLayout = PostAdapter.LIST;
        }

    }
}

public class PostAdapter extends
        RecyclerView.Adapter<Holder> {

    public interface OnViewPressedListener {

        public void OnLongPressedListener(int position);

        public void OnSingleTapUp(DialogFragment displayImage);
    }

    public static final int GRID = 0;
    public static final int LIST = 1;

    private List<PostsModel> posts;
    private Context context;
    private int itemLayout = GRID;
    private OnViewPressedListener listener;
    private DialogFragment displayImage;

    public PostAdapter(Context context, List<PostsModel> posts) {
        this.context = context;
        this.posts = posts;
    }

    public void setDisplayImage(DialogFragment displayImage) {
        this.displayImage = displayImage;
    }

    public void setListener(OnViewPressedListener listener) {
        this.listener = listener;
    }

    public PostAdapter(Context context) {
        this.context = context;
    }

    public void setPosts(List<PostsModel> posts) {
        this.posts = posts;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater =
                LayoutInflater.from(parent.getContext());

        View itemView;

        if (viewType == 1) {
            itemView =
                    inflater.inflate(R.layout.list_item, null, false);
            itemView.setId(R.id.list);
        } else {
            itemView =
                    inflater.inflate(R.layout.post_item, null, false);
            itemView.setId(R.id.grid);
        }

        Holder holder = new Holder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        Glide.with(context).load(posts
                .get(position)
                .getUrl())
                .into(holder.image);

        if (holder.itemLayout == LIST) {
            holder.tvComment.setText(String.valueOf(posts.get(position).getComment()));

            holder.tvLike.setText(String.valueOf(posts.get(position).getLike()));
        } else {
            setWidgetEventListener(holder, position);
        }
    }

    private void setWidgetEventListener(Holder holder, final int position){

        holder.image.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.OnLongPressedListener(position);
                return true;
            }
        });

        holder.image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_UP:
                        listener.OnSingleTapUp(displayImage);
                }
                return false;
            }
        });

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
