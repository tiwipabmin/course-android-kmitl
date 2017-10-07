package kmitl.lab07.tiwipabmin58070044.lazyinstagram.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import kmitl.lab07.tiwipabmin58070044.lazyinstagram.R;

/**
 * Created by student on 10/6/2017 AD.
 */

class Holder extends RecyclerView.ViewHolder{

    public ImageView image;

    public Holder(View itemView) {
        super(itemView);
        image = (ImageView) itemView.findViewById(R.id.ivImage);
    }
}

public class PostAdapter extends
        RecyclerView.Adapter<Holder> {

    private String[] data;

    private Context context;
    public PostAdapter(Context context, String[] data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater =
                LayoutInflater.from(parent.getContext());
        View itemView =
                inflater.inflate(R.layout.post_item, null, false);

        Holder holder = new Holder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ImageView image = holder.image;
        Glide.with(context).load(data[position]).into(image);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

}
