package kmitl.lab04.tiwipab58070044.simplemydot;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import kmitl.lab04.tiwipab58070044.simplemydot.model.Colors;
import kmitl.lab04.tiwipab58070044.simplemydot.model.Dot;
import kmitl.lab04.tiwipab58070044.simplemydot.model.Dots;
import kmitl.lab04.tiwipab58070044.simplemydot.view.Dialog;
import kmitl.lab04.tiwipab58070044.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity implements Dots.OnDotsChangeListener, DotView.OnDotViewPressListener, Dialog.OnShowedDialog {

    final int EDIT_REQUEST = 54920;
    final int REQUEST_WRITE_EXTERNAL_PERMISSIONS = 1234;
    final int REQUEST_READ_EXTERNAL_PERMISSIONS = 1235;

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

    public void onCapture(View view) throws IOException {
        dotView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(dotView.getDrawingCache());
        dotView.setDrawingCacheEnabled(false);

        String imagePath = addImageToGallery(bitmap);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
        intent.putExtra("capture", byteArray);
        intent.putExtra("imagePath", imagePath);
        startActivity(intent);
    }

    public String addImageToGallery(Bitmap bitmap) throws IOException {

        File imageFile = createImageFile("SimpleMyDots");
        String imagePath = imageFile.getAbsolutePath();

        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();

            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            File f = new File(imagePath);
            Uri contentUri = Uri.fromFile(f);
            mediaScanIntent.setData(contentUri);
            this.sendBroadcast(mediaScanIntent);

//            Toast.makeText(this, "Successed : " + imagePath, Toast.LENGTH_SHORT).show();

            return imagePath;
        }
        catch (Exception e)
        {
            e.printStackTrace();
//            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        }

        return "error";
    }

    private File createImageFile(String albumName) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileNameDir = "SimpleMyDot_" + timeStamp;
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), albumName);
        if(!storageDir.exists()){
            storageDir.mkdirs();
            Log.i("error", String.valueOf(storageDir.mkdirs()));
        }
        File image = new File(storageDir,
                imageFileNameDir +  /* prefix */
                        ".jpg"         /* suffix */
        );
        return image;
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
                }
            }
        }
    }
}
