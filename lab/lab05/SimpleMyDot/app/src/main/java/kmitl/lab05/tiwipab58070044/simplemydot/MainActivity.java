package kmitl.lab05.tiwipab58070044.simplemydot;

import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Random;

import kmitl.lab05.tiwipab58070044.simplemydot.fragment.EditDotFragment;
import kmitl.lab05.tiwipab58070044.simplemydot.model.Colors;
import kmitl.lab05.tiwipab58070044.simplemydot.model.Dot;
import kmitl.lab05.tiwipab58070044.simplemydot.model.Dots;
import kmitl.lab05.tiwipab58070044.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity implements Dots.OnDotsChangeListener, DotView.OnDotViewPressListener {

    private Dots dots = null;
    private DotView dotView = null;
    private FragmentManager fragmentManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dotView = (DotView) findViewById(R.id.dotView);
        dotView.setOnDotViewPressListener(this);

        dots = new Dots();
        dots.setListener(this);

        fragmentManager = getSupportFragmentManager();
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
    public void onDotsChanged(Dots dots) {
        dotView.setDots(dots);
        dotView.invalidate();
    }

    public void onClearDot(View view){
        dots.clearAll();
    }

    @Override
    public void onDotViewPressed(int x, int y) {
        final int position = dots.findDot(x, y);
        dotView.setPosition(position);
        if(position == -1){
            Random random = new Random();
            int radius = random.nextInt(100) + 31;
            Dot dot = new Dot(x, y, radius, new Colors().getColor());
            dots.addDot(dot);
        } else {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    fragmentManager.beginTransaction()
                            .replace(R.id.frameLayout, new EditDotFragment().newInstance(dots.getAllDot().get(position)))
                            .commit();
                }
            };
            Handler handler = new Handler();
            handler.postDelayed(runnable, 1000);

            dotView.setRunnable(runnable);
            dotView.setHandler(handler);
        }
    }
}

