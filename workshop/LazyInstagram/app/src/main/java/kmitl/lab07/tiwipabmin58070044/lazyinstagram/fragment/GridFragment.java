package kmitl.lab07.tiwipabmin58070044.lazyinstagram.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kmitl.lab07.tiwipabmin58070044.lazyinstagram.R;
import kmitl.lab07.tiwipabmin58070044.lazyinstagram.adapter.PostAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class GridFragment extends Fragment {

    private String[] data;

    public GridFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        data = getArguments().getStringArray("data");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_grid, container, false);

        PostAdapter postAdapter =
                new PostAdapter(getActivity(), data);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.gridImage);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(postAdapter);

        return rootView;
    }

    public static GridFragment newInstance(String[] data) {
        Bundle args = new Bundle();
        GridFragment fragment = new GridFragment();
        args.putStringArray("data", data);
        fragment.setArguments(args);
        return fragment;
    }

}
