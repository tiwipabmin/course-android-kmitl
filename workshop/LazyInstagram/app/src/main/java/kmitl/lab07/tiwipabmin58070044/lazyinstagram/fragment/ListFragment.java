package kmitl.lab07.tiwipabmin58070044.lazyinstagram.fragment;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
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
public class ListFragment extends Fragment {

    private List<PostsModel> posts;

    public ListFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        PostAdapter postAdapter =
                new PostAdapter(getActivity(), posts);
        RecyclerView recycleView = (RecyclerView) rootView.findViewById(R.id.listImage);
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleView.setAdapter(postAdapter);
        postAdapter.setItemLayout(PostAdapter.LIST);
        return rootView;
    }

    public static ListFragment newInstance(List<PostsModel> posts) {

        Bundle args = new Bundle();
        ListFragment fragment = new ListFragment();
        args.putParcelableArrayList("posts", (ArrayList<PostsModel>) posts);
        fragment.setArguments(args);
        return fragment;
    }

}
