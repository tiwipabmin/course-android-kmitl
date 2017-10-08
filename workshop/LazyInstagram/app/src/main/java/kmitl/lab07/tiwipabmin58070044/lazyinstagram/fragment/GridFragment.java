package kmitl.lab07.tiwipabmin58070044.lazyinstagram.fragment;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kmitl.lab07.tiwipabmin58070044.lazyinstagram.R;
import kmitl.lab07.tiwipabmin58070044.lazyinstagram.adapter.PostAdapter;
import kmitl.lab07.tiwipabmin58070044.lazyinstagram.api.PostsModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class GridFragment extends Fragment {

    private List<PostsModel> posts;

    public GridFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        posts = getArguments().getParcelableArrayList("posts");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_grid, container, false);

        PostAdapter postAdapter =
                new PostAdapter(getActivity(), posts);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.gridImage);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        postAdapter.setItemLayout(PostAdapter.GRID);
        recyclerView.setAdapter(postAdapter);

        return rootView;
    }

    public static GridFragment newInstance(List<PostsModel> posts) {
        Bundle args = new Bundle();
        GridFragment fragment = new GridFragment();
        args.putParcelableArrayList("posts", (ArrayList<PostsModel>) posts);
        fragment.setArguments(args);
        return fragment;
    }

}
