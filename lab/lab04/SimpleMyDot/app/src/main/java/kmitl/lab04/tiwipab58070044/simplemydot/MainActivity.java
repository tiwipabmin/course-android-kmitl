package kmitl.lab04.tiwipab58070044.simplemydot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Random;

import kmitl.lab04.tiwipab58070044.simplemydot.model.Colors;
import kmitl.lab04.tiwipab58070044.simplemydot.model.Dot;
import kmitl.lab04.tiwipab58070044.simplemydot.model.Dots;
import kmitl.lab04.tiwipab58070044.simplemydot.view.Dialog;
import kmitl.lab04.tiwipab58070044.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity implements Dots.OnDotsChangeListener, DotView.OnDotViewPressListener, Dialog.OnShowedDialog{

    private Dots dots = null;
    private DotView dotView = null;
    private Dialog dialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dotView = (DotView) findViewById(R.id.dotView);
        dotView.setOnDotViewPressListener(this);

        dots = new Dots();
        dots.setListener(this);

        dialog = new Dialog(this);
        dialog.setListener(this);
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
        int position = dots.findDot(x, y);
        if(position == -1){
            Random random = new Random();
            int radius = random.nextInt(100) + 31;
            Dot dot = new Dot(x, y, radius, new Colors().getColor());
            dots.addDot(dot);
        } else {
            dialog.showDialog(position);
        }
    }

    @Override
    public void onEditPressed() {

    }

    @Override
    public void onDeletePressed(int position) {
        dots.removeBy(position);
    }
}
