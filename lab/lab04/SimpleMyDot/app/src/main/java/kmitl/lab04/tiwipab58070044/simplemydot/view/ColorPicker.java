package kmitl.lab04.tiwipab58070044.simplemydot.view;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.widget.Toast;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import kmitl.lab04.tiwipab58070044.simplemydot.EditActivity;
import kmitl.lab04.tiwipab58070044.simplemydot.model.Dot;

/**
 * Created by tiwip on 9/10/2017.
 */

public class ColorPicker {

    public interface OnColorPickerShowed {

        public void onOkTouched(int color);

        public void onCancelTouched();
    }

    private Context context;
    private OnColorPickerShowed listener;

    public void setListener(OnColorPickerShowed listener) {
        this.listener = listener;
    }

    public ColorPicker(Context context) {
        this.context = context;
    }

    public void showPalette(){
        ColorPickerDialogBuilder
                .with(context)
                .setTitle("Choose color")
                .initialColor(Color.WHITE)
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int selectedColor) {
                        Toast.makeText(context, "onColorSelected: 0x" + Integer.toHexString(selectedColor), Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("ok", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                        listener.onOkTouched(selectedColor);
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onCancelTouched();
                    }
                })
                .build()
                .show();
    }
}
