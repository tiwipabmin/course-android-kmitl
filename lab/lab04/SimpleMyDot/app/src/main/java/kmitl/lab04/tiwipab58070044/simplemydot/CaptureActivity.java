package kmitl.lab04.tiwipab58070044.simplemydot;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

public class CaptureActivity extends AppCompatActivity {

    private Intent indexInBound = null;
    private ImageView iv_ScreenDot = null;
    private String imagePath = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);

        indexInBound = getIntent();
        byte[] byteArray = indexInBound.getByteArrayExtra("capture");
        imagePath = indexInBound.getStringExtra("imagePath");
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        iv_ScreenDot = (ImageView) findViewById(R.id.iv_ScreenDots);
        iv_ScreenDot.setImageBitmap(bitmap);
    }

    public void onShareTouched(View view){
        File imageFile = new File(imagePath);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/png");
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(imageFile));
        startActivity(Intent.createChooser(intent, "Share to..."));
    }

    public void onCancel(View view){
        finish();
    }
}
