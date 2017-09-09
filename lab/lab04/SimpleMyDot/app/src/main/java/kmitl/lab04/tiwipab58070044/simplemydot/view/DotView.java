package kmitl.lab04.tiwipab58070044.simplemydot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import kmitl.lab04.tiwipab58070044.simplemydot.model.Dot;
import kmitl.lab04.tiwipab58070044.simplemydot.model.Dots;

/**
 * Created by tiwip on 9/10/2017.
 */

public class DotView extends View{

    private Paint paint;
    private Dots allDot = null;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(this.allDot != null) {
            for(Dot dot : this.allDot.getAllDot()) {
                paint.setColor(dot.getColor());
                canvas.drawCircle(dot.getCenterX(), dot.getCenterY(), dot.getRadius(), paint);
            }
        }
    }

    public void setDots(Dots allDot) {
        this.allDot = allDot;
    }

    public interface OnDotViewPressListener{
        void onDotViewPressed(int x, int y);
    }

    private OnDotViewPressListener onDotViewPressListener;
    public void setOnDotViewPressListener(
            OnDotViewPressListener onDotViewPressListener) {
        this.onDotViewPressListener = onDotViewPressListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                this.onDotViewPressListener
                        .onDotViewPressed(
                                (int)event.getX(),
                                (int)event.getY());
                return true;
        }
        return false;
    }

    public DotView(Context context) {
        super(context);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
    }


}
