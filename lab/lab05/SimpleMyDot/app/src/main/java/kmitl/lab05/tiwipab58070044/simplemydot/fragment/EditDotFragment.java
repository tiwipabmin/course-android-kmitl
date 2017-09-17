package kmitl.lab05.tiwipab58070044.simplemydot.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import info.hoang8f.widget.FButton;
import kmitl.lab05.tiwipab58070044.simplemydot.R;
import kmitl.lab05.tiwipab58070044.simplemydot.model.Dot;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditDotFragment extends Fragment {


    public EditDotFragment() {
        // Required empty public constructor
    }

    public FButton fbtn_ok = null, fbtn_cancel = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_edit_dot, container, false);

        fbtn_ok = (FButton) rootView.findViewById(R.id.fbtn_ok);
        fbtn_cancel = (FButton) rootView.findViewById(R.id.fbtn_cancel);

        fbtn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .remove(EditDotFragment.this)
                        .commit();
            }
        });

        return rootView;
    }

    public static EditDotFragment newInstance(Dot dot) {

        Bundle args = new Bundle();
        EditDotFragment fragment = new EditDotFragment();
        args.putParcelable("dot", dot);
        fragment.setArguments(args);
        return fragment;
    }

}
