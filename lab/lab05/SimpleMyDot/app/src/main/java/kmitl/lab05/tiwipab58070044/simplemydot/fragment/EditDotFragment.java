package kmitl.lab05.tiwipab58070044.simplemydot.fragment;


import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import info.hoang8f.widget.FButton;
import kmitl.lab05.tiwipab58070044.simplemydot.R;
import kmitl.lab05.tiwipab58070044.simplemydot.model.Dot;
import kmitl.lab05.tiwipab58070044.simplemydot.view.ColorPicker;
import kmitl.lab05.tiwipab58070044.simplemydot.view.DotView;
import kmitl.lab05.tiwipab58070044.simplemydot.view.EditDotView;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditDotFragment extends Fragment implements ColorPicker.OnColorPickerShowed, DotView.OnDotViewPressListener {


    public EditDotFragment() {
        // Required empty public constructor
    }

    private FButton fbtn_ok = null, fbtn_cancel = null;
    private Dot dot = null;
    private EditDotView editDotView = null;
    private ColorPicker colorPicker= null;
    private EditDotFragmentListener listener = null;
    private int position = -1;

    public void setListener(EditDotFragmentListener listener) {
        this.listener = listener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dot = getArguments().getParcelable("dot");
        position = getArguments().getInt("position", -1);

        colorPicker = new ColorPicker(getActivity());
        colorPicker.setListener(EditDotFragment.this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_edit_dot, container, false);

        bindWidget(rootView);
        setWidgetEventListener();

        return rootView;
    }

    @Override
    public void onDotViewPressed(int x, int y) {
        int centerX = editDotView.getCurrentX();
        int centerY = editDotView.getCurrentY();
        double distance = Math.sqrt(Math.pow(centerX - x, 2)) +
                Math.sqrt(Math.pow(centerY - y, 2));
        if(distance <= editDotView.getRadius()){
            colorPicker.showPalette();
        }
    }

    @Override
    public void onDotViewLongPressed(int x, int y) {

    }

    private void bindWidget(View rootView){
        fbtn_ok = (FButton) rootView.findViewById(R.id.fbtn_ok);
        fbtn_cancel = (FButton) rootView.findViewById(R.id.fbtn_cancel);

        editDotView = (EditDotView) rootView.findViewById(R.id.editDotView);
        editDotView.setOnDotViewPressListener(EditDotFragment.this);
        editDotView.setColor(dot.getColor());
        editDotView.setRadius(dot.getRadius());
    }

    private void setWidgetEventListener(){

        fbtn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dot.setColor(editDotView.getColor());
                listener.EditDotFinished(dot, position);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .remove(EditDotFragment.this)
                        .commit();
            }
        });

        fbtn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .remove(EditDotFragment.this)
                        .commit();
            }
        });
    }

    public static EditDotFragment newInstance(Dot dot, int position, MainFragment mainFragment) {
        Bundle args = new Bundle();
        EditDotFragment fragment = new EditDotFragment();
        fragment.setListener(mainFragment);
        args.putParcelable("dot", dot);
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onOkTouched(int color) {
        editDotView.setColor(color);
        editDotView.invalidate();
    }

    @Override
    public void onCancelTouched() {

    }

    public interface EditDotFragmentListener {

        public void EditDotFinished(Dot dot, int position);
    }
}
