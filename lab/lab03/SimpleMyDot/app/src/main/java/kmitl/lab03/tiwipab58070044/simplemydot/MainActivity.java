package kmitl.lab03.tiwipab58070044.simplemydot;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import kmitl.lab03.tiwipab58070044.simplemydot.model.Dot;
import kmitl.lab03.tiwipab58070044.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity implements Dot.OnDotChangedListener {

    private final long DOUBLE_CLICK_TIME_DELTA = 500;//milliseconds
    private int click = 0, currentIndex = 0;
    private long lastClickTime = 0;

    private Button btnRandomDot = null;
    private DotView dotView = null;
    private Handler handler = null;
    private List<Dot> listDot = null;
    private Runnable runnable = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createObject();
        bindWidget();
        setWidgetEventListener();
        update();

    }

    private void createObject(){

        handler = new Handler();

        listDot = new LinkedList<>();

        runnable = new Runnable() {
            @Override
            public void run() {
                lastClickTime = 0;
                click = 0;
            }
        };
    }

    private void bindWidget() {

        MainActivity.this.btnRandomDot = (Button) findViewById(R.id.btn_randomDot);

        MainActivity.this.dotView = (DotView) findViewById(R.id.dotView);

    }

    private void setWidgetEventListener() {

        MainActivity.this.btnRandomDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paint paint = new Paint();
                Random random = new Random();

                int centerX = random.nextInt((dotView.getWidth() - 30 * 2) + 1) + 30;
                int centerY = random.nextInt((dotView.getHeight() - 30 * 2) + 1) + 30;

                paint.setColor(Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256)));

                new Dot(MainActivity.this, centerX, centerY, 30, paint);

                click = 0;
            }
        });

        MainActivity.this.dotView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                long currentTime = System.currentTimeMillis();

                boolean touchDot = false;

                for(int index = listDot.size() - 1; index >= 0 ; index-- ){
                    double pointX = Math.pow((listDot.get(index).getCenterX() - (int) event.getX()), 2);
                    double pointY = Math.pow((listDot.get(index).getCenterY() - (int) event.getY()), 2);
                    double result = Math.sqrt(pointX + pointY);
                    if (result <= listDot.get(index).getRadius()) {
                        currentIndex = index;
                        touchDot = true;
                        break;
                    }
                }

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        click++;

                        if(!touchDot){

                            Paint paint = new Paint();
                            Random random = new Random();

                            int centerX = (int) event.getX();
                            int centerY = (int) event.getY();

                            paint.setColor(Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256)));

                            new Dot(MainActivity.this, centerX, centerY, 30, paint);

                            click = 0;

                        } else if (click == 2 && currentTime - lastClickTime <= DOUBLE_CLICK_TIME_DELTA) {
                            handler.removeCallbacks(runnable);
                            MainActivity.this.listDot.remove(currentIndex);
                            MainActivity.this.dotView.invalidate();
                            click = 0;
                            lastClickTime = 0;
                        } else {

                            lastClickTime = System.currentTimeMillis();

                            handler.postDelayed(runnable, 501);
                        }


                        break;
                    default:
                        break;
                }
                return true;
            }
        });

    }

    private void update() {

        MainActivity.this.dotView.setListDot(listDot);
    }

    public void onClearDotButtonClicked(View view) {
        MainActivity.this.listDot.clear();
        MainActivity.this.dotView.invalidate();

    }

    @Override
    public void onDotChange(Dot dot) {
        MainActivity.this.listDot.add(dot);
        MainActivity.this.dotView.invalidate();
    }

}
