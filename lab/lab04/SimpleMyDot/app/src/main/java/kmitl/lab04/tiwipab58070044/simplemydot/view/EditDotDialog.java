package kmitl.lab04.tiwipab58070044.simplemydot.view;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import java.util.List;

import kmitl.lab04.tiwipab58070044.simplemydot.R;
import kmitl.lab04.tiwipab58070044.simplemydot.model.Dot;
import kmitl.lab04.tiwipab58070044.simplemydot.model.Dots;

/**
 * Created by tiwip on 9/13/2017.
 */

public class EditDotDialog {

    public interface OnShowedEditDotDialog {

        public void onEditPressed(Dot dot, int position);

        public void onDeletePressed(int position);
    }

    private OnShowedEditDotDialog listener;

    public void setListener(OnShowedEditDotDialog listener) {
        this.listener = listener;
    }

    private Context context = null;
    private List<Dot> allDot = null;
    private int position = -1;

    public void setAllDot(List<Dot> allDot) {
        this.allDot = allDot;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public EditDotDialog(Context context){
        this.context = context;
    }

    public void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.menu_title);
        builder.setItems(R.array.items_menu_edit_dot, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch(which){
                    case 0:
                        listener.onEditPressed(allDot.get(position), position);
                        break;
                    case 1:
                        listener.onDeletePressed(position);
                        break;
                    default:
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
