package kmitl.lab05.tiwipab58070044.simplemydot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import kmitl.lab05.tiwipab58070044.simplemydot.model.Dot;
import kmitl.lab05.tiwipab58070044.simplemydot.model.Dots;

/**
 * Created by tiwip on 9/17/2017.
 */

public class DotView extends View {

    private Paint paint = null;
    private Dots dots = null;
    private GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            DotView.this.onDotViewPressListener.onDotViewPressed((int) e.getX(), (int) e.getY());
            return super.onSingleTapUp(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            DotView.this.onDotViewPressListener.onDotViewLongPressed((int) e.getX(), (int) e.getY());
        }

    });

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(this.dots != null) {
            for(Dot dot : this.dots.getAllDot()) {
                paint.setColor(dot.getColor());
                canvas.drawCircle(dot.getCenterX(), dot.getCenterY(), dot.getRadius(), paint);
            }
        }
    }

    public void setDots(Dots dots) {
        this.dots = dots;
    }

    public interface OnDotViewPressListener{
        void onDotViewPressed(int x, int y);

        void onDotViewLongPressed(int x, int y);
    }

    private OnDotViewPressListener onDotViewPressListener;
    public void setOnDotViewPressListener(
            OnDotViewPressListener onDotViewPressListener) {
        this.onDotViewPressListener = onDotViewPressListener;
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