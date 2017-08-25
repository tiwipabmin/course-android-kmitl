package kmitl.lab03.tiwipab58070044.simplemydot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import kmitl.lab03.tiwipab58070044.simplemydot.model.Dot;
import kmitl.lab03.tiwipab58070044.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity implements Dot.OnDotChangedListener{

    private Dot dot;
    private DotView dotView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dotView = (DotView) findViewById(R.id.dotView);

        dot = new Dot(MainActivity.this, 0, 0, 30);

    }

    public void onRandomDot(View view) {
        Random random = new Random();
        int centerX = random.nextInt(500);
        int centerY = random.nextInt(500);

        this.dot.setCenterX(centerX);
        this.dot.setCenterY(centerY);

    }

    @Override
    public void onDotChanged(Dot dot) {
        dotView.setDot(dot);
        dotView.invalidate();
    }
}
