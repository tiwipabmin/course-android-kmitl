package kmitl.lab05.tiwipab58070044.simplemydot.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Random;

import kmitl.lab05.tiwipab58070044.simplemydot.R;
import kmitl.lab05.tiwipab58070044.simplemydot.model.Colors;
import kmitl.lab05.tiwipab58070044.simplemydot.model.Dot;
import kmitl.lab05.tiwipab58070044.simplemydot.model.Dots;
import kmitl.lab05.tiwipab58070044.simplemydot.view.DotView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements DotView.OnDotViewPressListener, Dots.OnDotsChangeListener, EditDotFragment.EditDotFragmentListener {


    public MainFragment() {
        // Required empty public constructor
    }

    private Dots dots = null;
    private DotView dotView = null;
    private FragmentManager fragmentManager = null;
    private Button btn_randomDot = null, btn_clearDot = null;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("dots", dots);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            dots = savedInstanceState.getParcelable("dots");
            dotView.setDots(dots);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        dotView.invalidate();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dots = new Dots();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        bindWidget(rootView);
        setWidgetEventListener();

        return rootView;
    }

    private void bindWidget(View rootView) {

        dotView = (DotView) rootView.findViewById(R.id.dotView);
        dotView.setOnDotViewPressListener(this);

        dots.setListener(this);

        btn_randomDot = (Button) rootView.findViewById(R.id.btn_randomDot);
        btn_clearDot = (Button) rootView.findViewById(R.id.btn_clearDot);

        fragmentManager = getActivity().getSupportFragmentManager();
    }

    private void setWidgetEventListener() {
        btn_randomDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRandomDot();
            }
        });

        btn_clearDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClearDot();
            }
        });
    }

    public static MainFragment newInstance() {

        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void onRandomDot() {
        Random random = new Random();
        int radius = random.nextInt(100) + 31;
        int centerX = random.nextInt((dotView.getWidth() - radius) - radius + 1) + radius;
        int centerY = random.nextInt((dotView.getHeight() - radius) - radius + 1) + radius;
        Dot dot = new Dot(centerX, centerY, radius, new Colors().getColor());
        dots.addDot(dot);
    }

    @Override
    public void onDotsChanged(Dots dots) {
        dotView.setDots(dots);
        dotView.invalidate();
    }

    private void onClearDot() {
        dots.clearAll();
    }

    @Override
    public void onDotViewPressed(int x, int y) {
        int position = dots.findDot(x, y);
        if (position == -1) {
            Random random = new Random();
            int radius = random.nextInt(100) + 31;
            Dot dot = new Dot(x, y, radius, new Colors().getColor());
            dots.addDot(dot);
        } else {
            dots.removeBy(position);
        }
    }

    @Override
    public void onDotViewLongPressed(int x, int y) {
        int position = dots.findDot(x, y);
        if (position == -1) {
            Random random = new Random();
            int radius = random.nextInt(100) + 31;
            Dot dot = new Dot(x, y, radius, new Colors().getColor());
            dots.addDot(dot);
        } else {
            fragmentManager.beginTransaction()
                    .add(R.id.frameLayout, new EditDotFragment().newInstance(dots.getAllDot().get(position), position, MainFragment.this))
                    .addToBackStack("simpleMyDotFragment")
                    .commit();
        }
    }

    @Override
    public void EditDotFinished(Dot dot, int position) {
        if (position != -1) {
            dots.getAllDot().set(position, dot);
            dotView.invalidate();
        }

    }
}
