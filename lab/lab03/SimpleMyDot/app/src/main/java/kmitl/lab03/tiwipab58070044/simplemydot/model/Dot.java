package kmitl.lab03.tiwipab58070044.simplemydot.model;

import android.graphics.Paint;

public class Dot {

    private int centerX;
    private int centerY;
    private int radius;
    private int color;

    public Dot(int centerX, int centerY, int radius, int color) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.color = color;

    }

    public Dot(int centerX, int centerY, int radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;

    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

}
