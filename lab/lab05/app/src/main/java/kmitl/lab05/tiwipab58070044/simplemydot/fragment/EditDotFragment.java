package kmitl.lab05.tiwipab58070044.simplemydot.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kmitl.lab05.tiwipab58070044.simplemydot.R;
import kmitl.lab05.tiwipab58070044.simplemydot.model.Dot;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditDotFragment extends Fragment {


    public EditDotFragment() {
        // Required empty public constructor
    }

    private Dot dot;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dot = getArguments().getParcelable("dot");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_dot, container, false);
    }

    public static EditDotFragment newInstance(Dot dot) {
        Bundle args = new Bundle();
        EditDotFragment fragment = new EditDotFragment();
        args.putParcelable("dot", dot);
        fragment.setArguments(args);
        return fragment;
    }

}
