package kmitl.lab03.tiwipab58070044.simplemydot.model;

import android.graphics.Paint;

public class Dot {

    public interface OnDotChangedListener {
        void onDotChange(Dot dot);
    }

    private OnDotChangedListener listener;

    public OnDotChangedListener getListener() {
        return listener;
    }

    public void setListener(OnDotChangedListener listener) {
        this.listener = listener;
    }

    private int centerX;
    private int centerY;
    private int radius;
    private Paint paint;

    public Dot(OnDotChangedListener listener, int centerX, int centerY, int radius, Paint paint) {
        this.listener = listener;
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.paint = paint;
        this.listener.onDotChange(Dot.this);

    }

    public Dot(int centerX, int centerY, int radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;

    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
        this.listener.onDotChange(Dot.this);
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
        this.listener.onDotChange(Dot.this);
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

}
