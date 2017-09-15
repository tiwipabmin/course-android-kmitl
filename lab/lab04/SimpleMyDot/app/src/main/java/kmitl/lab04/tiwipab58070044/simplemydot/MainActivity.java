package kmitl.lab04.tiwipab58070044.simplemydot;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

import kmitl.lab04.tiwipab58070044.simplemydot.model.Colors;
import kmitl.lab04.tiwipab58070044.simplemydot.model.Dot;
import kmitl.lab04.tiwipab58070044.simplemydot.model.Dots;
import kmitl.lab04.tiwipab58070044.simplemydot.view.CaptureDialog;
import kmitl.lab04.tiwipab58070044.simplemydot.view.DotView;
import kmitl.lab04.tiwipab58070044.simplemydot.view.EditDotDialog;

public class MainActivity extends AppCompatActivity implements Dots.OnDotsChangeListener,
        DotView.OnDotViewPressListener, EditDotDialog.OnShowedEditDotDialog, CaptureDialog.OnShowedCaptureDialog {

    final int EDIT_REQUEST = 54920;
    final int REQUEST_WRITE_EXTERNAL_PERMISSIONS = 1234;
    final int REQUEST_READ_EXTERNAL_PERMISSIONS = 1235;

    private Dots dots = null;
    private DotView dotView = null;
    private EditDotDialog editDotDialog = null;
    private CaptureDialog captureDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dotView = (DotView) findViewById(R.id.dotView);
        dotView.setOnDotViewPressListener(this);

        dots = new Dots();
        dots.setListener(this);

        editDotDialog = new EditDotDialog(this);
        editDotDialog.setListener(this);

        captureDialog = new CaptureDialog(this);
        captureDialog.setListener(this);

        checkPermission();
    }

    public void checkPermission(){
        if(ContextCompat.checkSelfPermission(MainActivity.this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_EXTERNAL_PERMISSIONS);
            Toast.makeText(this, "Permission grated", Toast.LENGTH_SHORT).show();
        } else if(ContextCompat.checkSelfPermission(MainActivity.this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {android.Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_READ_EXTERNAL_PERMISSIONS);
            Toast.makeText(this, "Permission grated", Toast.LENGTH_SHORT).show();
        }
    }

    public void onRandomDot(View view){
        Random random = new Random();
        int radius = random.nextInt(100) + 31;
        int centerX = random.nextInt((dotView.getWidth() - radius) - radius + 1) + radius;
        int centerY = random.nextInt((dotView.getHeight() - radius) - radius + 1) + radius;
        Dot dot = new Dot(centerX, centerY, radius, new Colors().getColor());
        dots.addDot(dot);
    }

    public void onCapture(View view) {

        dotView.setDrawingCacheEnabled(true);
        Bitmap bScreenDots = Bitmap.createBitmap(dotView.getDrawingCache());
        dotView.setDrawingCacheEnabled(false);

        View screenPhone = getWindow().getDecorView().getRootView();
        screenPhone.setDrawingCacheEnabled(true);
        Bitmap bScreenPhone = Bitmap.createBitmap(screenPhone.getDrawingCache());
        screenPhone.setDrawingCacheEnabled(false);

        captureDialog.setScreenPhone(bScreenPhone);
        captureDialog.setScreenDots(bScreenDots);
        captureDialog.showCaptureDialog();
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
            editDotDialog.setAllDot(dots.getAllDot());
            editDotDialog.setPosition(position);
            editDotDialog.showDialog();
        }
    }

    @Override
    public void onEditPressed(Dot dot, int position) {
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
                }
            }
        }
    }

    @Override
    public void onCaptureScreenPhonePressed(String imagePath) {

        if(!imagePath.equals("error")){

            Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
            intent.putExtra("imagePath", imagePath);
            startActivity(intent);
        } else {

            Toast.makeText(this, "Capture failed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCaptureScreenDotsPressed(String imagePath) {

        if(!imagePath.equals("error")){

            Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
            intent.putExtra("imagePath", imagePath);
            startActivity(intent);
        } else {

            Toast.makeText(this, "Capture failed", Toast.LENGTH_SHORT).show();
        }
    }
}
