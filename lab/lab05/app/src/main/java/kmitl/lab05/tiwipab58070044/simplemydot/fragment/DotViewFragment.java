package kmitl.lab05.tiwipab58070044.simplemydot.fragment;


import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Random;

import kmitl.lab05.tiwipab58070044.simplemydot.R;
import kmitl.lab05.tiwipab58070044.simplemydot.model.Colors;
import kmitl.lab05.tiwipab58070044.simplemydot.model.Dot;
import kmitl.lab05.tiwipab58070044.simplemydot.model.Dots;
import kmitl.lab05.tiwipab58070044.simplemydot.view.DotView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DotViewFragment extends Fragment implements DotView.OnDotViewPressListener, Dots.OnDotsChangeListener{


    public DotViewFragment() {
        // Required empty public constructor
    }

    private Dots dots = null;
    private DotView dotView = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dotView.setOnDotViewPressListener(this);

        dots = new Dots();
        dots.setListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dot_view, container, false);

        dotView = (DotView) rootView.findViewById(R.id.dotView);

        return rootView;
    }

    public void onRandomDot(View view){
        Random random = new Random();
        int radius = random.nextInt(100) + 31;
        int centerX = random.nextInt((dotView.getWidth() - radius) - radius + 1) + radius;
        int centerY = random.nextInt((dotView.getHeight() - radius) - radius + 1) + radius;
        Dot dot = new Dot(centerX, centerY, radius, new Colors().getColor());
        dots.addDot(dot);
    }

    @Override
    public void onDotViewPressed(int x, int y) {

    }

    @Override
    public void onDotsChanged(Dots dots) {
        dotView.setDots(dots);
        dotView.invalidate();
    }
}
