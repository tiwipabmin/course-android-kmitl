package kmitl.lab04.tiwipab58070044.simplemydot;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

import kmitl.lab04.tiwipab58070044.simplemydot.model.Colors;
import kmitl.lab04.tiwipab58070044.simplemydot.model.Dot;
import kmitl.lab04.tiwipab58070044.simplemydot.model.Dots;
import kmitl.lab04.tiwipab58070044.simplemydot.view.Dialog;
import kmitl.lab04.tiwipab58070044.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity implements Dots.OnDotsChangeListener, DotView.OnDotViewPressListener, Dialog.OnShowedDialog {

    final int EDIT_REQUEST = 54920;

    private Dots dots = null;
    private DotView dotView = null;
    private Dialog dialog = null;
    private Intent indexInBound = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        indexInBound = getIntent();

        dotView = (DotView) findViewById(R.id.dotView);
        dotView.setOnDotViewPressListener(this);

        dots = new Dots();
        dots.setListener(this);

        dialog = new Dialog(this);
        dialog.setListener(this);
    }

    public void onRandomDot(View view){
        Random random = new Random();
        int radius = random.nextInt(100) + 31;
        int centerX = random.nextInt((dotView.getWidth() - radius) - radius + 1) + radius;
        int centerY = random.nextInt((dotView.getHeight() - radius) - radius + 1) + radius;
        Dot dot = new Dot(centerX, centerY, radius, new Colors().getColor());
        dots.addDot(dot);
    }

    @Override
    public void onDotsChanged(Dots dots) {
        dotView.setDots(dots);
        dotView.invalidate();
    }

    public void onClearDot(View view){
        dots.clearAll();
    }

    @Override
    public void onDotViewPressed(int x, int y) {
        int position = dots.findDot(x, y);
        if(position == -1){
            Random random = new Random();
            int radius = random.nextInt(100) + 31;
            Dot dot = new Dot(x, y, radius, new Colors().getColor());
            dots.addDot(dot);
        } else {
            dialog.showDialog(position);
        }
    }

    @Override
    public void onEditPressed(int position) {
        Intent intent = new Intent(MainActivity.this, EditActivity.class);
        intent.putExtra("dot", dots.getAllDot().get(position));
        intent.putExtra("position", position);
        startActivityForResult(intent, EDIT_REQUEST);
    }

    @Override
    public void onDeletePressed(int position) {
        dots.removeBy(position);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == EDIT_REQUEST){
            if(resultCode == RESULT_OK){
                Dot dot = data.getParcelableExtra("dot");
                int position = data.getIntExtra("position", -1);

                if(position != -1){
                    dots.setDot(position, dot);
                    Toast.makeText(MainActivity.this, position + "", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
