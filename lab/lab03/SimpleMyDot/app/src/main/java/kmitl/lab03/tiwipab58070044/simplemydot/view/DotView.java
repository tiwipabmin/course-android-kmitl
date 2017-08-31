package kmitl.lab03.tiwipab58070044.simplemydot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

import kmitl.lab03.tiwipab58070044.simplemydot.model.Dot;

public class DotView extends View {

    private List<Dot> listDot = null;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(this.listDot.size() > 0) {
            for(Dot dot : listDot) {
                canvas.drawCircle(dot.getCenterX(), dot.getCenterY(), dot.getRadius(), dot.getPaint());
            }
        }
    }

    public DotView(Context context) {
        super(context);
    }

    public DotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setListDot(List<Dot> listDot) {
        this.listDot = listDot;
    }
}
