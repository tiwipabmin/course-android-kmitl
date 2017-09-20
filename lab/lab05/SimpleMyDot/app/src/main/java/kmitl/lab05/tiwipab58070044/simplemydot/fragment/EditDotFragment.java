package kmitl.lab05.tiwipab58070044.simplemydot.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import info.hoang8f.widget.FButton;
import kmitl.lab05.tiwipab58070044.simplemydot.R;
import kmitl.lab05.tiwipab58070044.simplemydot.model.Dot;
import kmitl.lab05.tiwipab58070044.simplemydot.view.ColorPicker;
import kmitl.lab05.tiwipab58070044.simplemydot.view.DotView;
import kmitl.lab05.tiwipab58070044.simplemydot.view.PreviewDot;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditDotFragment extends Fragment implements ColorPicker.OnColorPickerShowed, DotView.OnDotViewPressListener {


    public EditDotFragment() {
        // Required empty public constructor
    }

    private FButton fbtn_ok = null, fbtn_cancel = null;
    private Dot dot = null;
    private PreviewDot previewDot = null;
    private ColorPicker colorPicker= null;
    private EditDotFragmentListener listener = null;
    private EditText et_pointX = null, et_pointY = null, et_radius = null;
    private int position = -1;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("dot", dot);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(savedInstanceState != null){
            dot = savedInstanceState.getParcelable("dot");
        }
    }

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
        int centerX = previewDot.getCurrentX();
        int centerY = previewDot.getCurrentY();
        double distance = Math.sqrt(Math.pow(centerX - x, 2)) +
                Math.sqrt(Math.pow(centerY - y, 2));
        if(distance <= previewDot.getRadius()){
            colorPicker.showPalette();
        }
    }

    @Override
    public void onDotViewLongPressed(int x, int y) {

    }

    private void bindWidget(View rootView){
        fbtn_ok = (FButton) rootView.findViewById(R.id.fbtn_ok);
        fbtn_cancel = (FButton) rootView.findViewById(R.id.fbtn_cancel);

        previewDot = (PreviewDot) rootView.findViewById(R.id.previewDot);
        previewDot.setOnDotViewPressListener(EditDotFragment.this);
        previewDot.setColor(dot.getColor());
        previewDot.setRadius(dot.getRadius());

        et_pointX = (EditText) rootView.findViewById(R.id.et_x);
        et_pointY = (EditText) rootView.findViewById(R.id.et_y);
        et_radius = (EditText) rootView.findViewById(R.id.et_r);

        et_pointX.setText(String.valueOf(dot.getCenterX()));
        et_pointY.setText(String.valueOf(dot.getCenterY()));
        et_radius.setText(String.valueOf(dot.getRadius()));
    }

    private void setWidgetEventListener(){

        fbtn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(previewDot.getRadius() > 0) {
                    dot.setColor(previewDot.getColor());
                    dot.setRadius(previewDot.getRadius());
                    listener.EditDotFinished(dot, position);
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .remove(EditDotFragment.this)
                            .commit();
                } else {
                    Toast.makeText(getActivity(), "Radius must be greater than 0", Toast.LENGTH_SHORT).show();
                }
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

        et_radius.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length() > 0) {
                    previewDot.setRadius(Integer.valueOf(editable.toString()));
                } else {
                    previewDot.setRadius(0);
                }
                previewDot.invalidate();
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
        previewDot.setColor(color);
        previewDot.invalidate();
    }

    @Override
    public void onCancelTouched() {

    }

    public interface EditDotFragmentListener {

        public void EditDotFinished(Dot dot, int position);
    }
}
