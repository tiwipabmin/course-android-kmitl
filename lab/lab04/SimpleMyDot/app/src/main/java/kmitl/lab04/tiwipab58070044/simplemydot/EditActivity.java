package kmitl.lab04.tiwipab58070044.simplemydot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import kmitl.lab04.tiwipab58070044.simplemydot.model.Dot;
import kmitl.lab04.tiwipab58070044.simplemydot.view.ColorPicker;
import kmitl.lab04.tiwipab58070044.simplemydot.view.DotView;
import kmitl.lab04.tiwipab58070044.simplemydot.view.EditDotView;

public class EditActivity extends AppCompatActivity implements DotView.OnDotViewPressListener, ColorPicker.OnColorPickerShowed{

    private ColorPicker colorPicker = null;
    private Dot dot = null;
    private EditDotView editDotView = null;
    private EditText et_x = null, et_y = null, et_r = null;
    private Intent indexInBound = null;
    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        bindWidget();

        indexInBound = getIntent();
        dot = (Dot) indexInBound.getParcelableExtra("dot");
        position = indexInBound.getIntExtra("position", -1);

        editDotView.setCurrentRadius(dot.getRadius());
        editDotView.setCurrentColor(dot.getColor());
        editDotView.setOnDotViewPressListener(EditActivity.this);

        et_x.setText(String.valueOf(dot.getCenterX()));
        et_y.setText(String.valueOf(dot.getCenterY()));
        et_r.setText(String.valueOf(dot.getRadius()));

        colorPicker = new ColorPicker(EditActivity.this);
        colorPicker.setListener(EditActivity.this);
    }

    public void bindWidget(){
        editDotView = (EditDotView) findViewById(R.id.editDotView);

        et_x = (EditText) findViewById(R.id.et_x);
        et_y = (EditText) findViewById(R.id.et_y);
        et_r = (EditText) findViewById(R.id.et_r);

    }

    public void onOkTouched(View view){
        dot.setColor(editDotView.getCurrentColor());
        Intent returnIntent = new Intent();
        returnIntent.putExtra("dot", dot);
        returnIntent.putExtra("position", position);
        setResult(RESULT_OK, returnIntent);
        finish();
    }

    public void onCancelTouched(View view){
        finish();
    }

    @Override
    
    public void onDotViewPressed(int x, int y) {
        int centerX = editDotView.getCurrentX();
        int centerY = editDotView.getCurrentY();
        double distance = Math.sqrt(Math.pow(centerX - x, 2)) +
                Math.sqrt(Math.pow(centerY - y, 2));
        if(distance <= 100){
            colorPicker.showPalette();
        }
    }

    @Override
    public void onOkTouched(int color) {
        editDotView.setCurrentColor(color);
        editDotView.invalidate();
    }

    @Override
    public void onCancelTouched() {

    }
}
