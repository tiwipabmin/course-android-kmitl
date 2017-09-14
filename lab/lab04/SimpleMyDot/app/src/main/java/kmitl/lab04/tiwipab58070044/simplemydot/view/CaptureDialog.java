package kmitl.lab04.tiwipab58070044.simplemydot.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import kmitl.lab04.tiwipab58070044.simplemydot.R;

/**
 * Created by tiwip on 9/13/2017.
 */

public class CaptureDialog {

    public interface OnShowedCaptureDialog {

        public void onCaptureScreenPhonePressed(Bitmap screenPhone);

        public void onCaptureScreenDotsPressed(Bitmap screenDots);
    }

    private OnShowedCaptureDialog listener;

    public void setListener(OnShowedCaptureDialog listener) {
        this.listener = listener;
    }

    private Context context = null;
    private Bitmap screenPhone = null, screenDots = null;
    private ImageView imageView;

    public void setScreenPhone(Bitmap screenPhone) {
        this.screenPhone = screenPhone;
    }

    public void setScreenDots(Bitmap screenDots) {
        this.screenDots = screenDots;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public CaptureDialog(Context context){
        this.context = context;
    }

    public void showCaptureDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.menu_title);
        builder.setItems(R.array.items_menu_capture, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch(which){
                    case 0:
                        listener.onCaptureScreenPhonePressed(screenPhone);
                        break;
                    case 1:
                        listener.onCaptureScreenDotsPressed(screenDots);
                        break;
                    default:
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public String addImageToGallery(Bitmap bitmap){

        File imageFile = createImageFile("SimpleMyDots");

        try
        {
//            String imagePath = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, imageName, "Capture");

            FileOutputStream fileOutputStream = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();

            galleryAddImage(Uri.fromFile(imageFile));

//            Toast.makeText(context, "Successed : " + imageFile, Toast.LENGTH_SHORT).show();

            return imageFile.getAbsolutePath();
        }
        catch (Exception e)
        {
            Log.i("error", String.valueOf(e));
//            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }

        return "error";
    }

    private File createImageFile(String albumName) {

        // make folder
        File mFolder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath(), albumName);
        if(!mFolder.exists()){
            if(!mFolder.mkdirs()){
                Log.i("error", "Directory not created : " + !mFolder.mkdirs());
            }
        }

        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
        String imageName = "SimpleMyDot_" + timeStamp;

        File imageFile = new File(mFolder, imageName + ".jpg");

        if(!imageFile.exists()){
            try {
                if(!imageFile.createNewFile()){
                    Log.i("error", "imageFile not create");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return imageFile;
    }

    private void galleryAddImage(Uri imageUri) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mediaScanIntent.setData(imageUri);
        context.sendBroadcast(mediaScanIntent);
    }


}
