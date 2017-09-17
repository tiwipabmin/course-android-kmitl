package kmitl.lab05.tiwipab58070044.simplemydot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TimeUtils;
import android.view.MotionEvent;
import android.view.View;

import kmitl.lab05.tiwipab58070044.simplemydot.model.Dot;
import kmitl.lab05.tiwipab58070044.simplemydot.model.Dots;

/**
 * Created by tiwip on 9/15/2017.
 */

public class DotView extends View {

    private Paint paint;
    private Dots dots = null;
    private int position = -1;
    private long startTime = 0;
    private Handler handler = null;
    private Runnable runnable = null;

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

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void setRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    public interface OnDotViewPressListener{
        void onDotViewPressed(int x, int y);
    }

    private OnDotViewPressListener onDotViewPressListener;
    public void setOnDotViewPressListener(
            OnDotViewPressListener onDotViewPressListener) {
        this.onDotViewPressListener = onDotViewPressListener;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startTime = System.currentTimeMillis();
                this.onDotViewPressListener
                        .onDotViewPressed(
                                (int)event.getX(),
                                (int)event.getY());
                return true;
            case MotionEvent.ACTION_UP:
                long endTime = System.currentTimeMillis() - startTime;
                if(endTime < 1000 && position != -1){
                    dots.removeBy(position);
                    handler.removeCallbacks(runnable);
                }
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
