package kmitl.lab04.tiwipab58070044.simplemydot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by tiwip on 9/10/2017.
 */

public class EditDotView extends View {

    private DotView.OnDotViewPressListener onDotViewPressListener;

    public void setOnDotViewPressListener(
            DotView.OnDotViewPressListener onDotViewPressListener) {
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

    private int currentColor;
    private int currentX = 0;
    private int currentY = 0;
    private int currentRadius = 0;
    private Paint paint;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        currentX = canvas.getWidth()/2;
        currentY = canvas.getHeight()/2;

        paint.setColor(currentColor);
        canvas.drawCircle(currentX, currentY, currentRadius, paint);
    }

    public void setCurrentRadius(int currentRadius) {
        this.currentRadius = currentRadius;
    }

    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public int getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(int currentColor) {
        this.currentColor = currentColor;
    }

    public EditDotView(Context context) {
        super(context);
        paint = new Paint();
    }

    public EditDotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    public EditDotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
    }
}
