package kmitl.lab04.tiwipab58070044.simplemydot.view;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import kmitl.lab04.tiwipab58070044.simplemydot.R;

/**
 * Created by tiwip on 9/13/2017.
 */

public class EditDotDialog {

    public interface OnShowedEditDotDialog {

        public void onEditPressed(int position);

        public void onDeletePressed(int position);
    }

    private OnShowedEditDotDialog listener;

    public void setListener(OnShowedEditDotDialog listener) {
        this.listener = listener;
    }

    private Context context;

    public EditDotDialog(Context context){
        this.context = context;
    }

    public void showDialog(final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.menu_title);
        builder.setItems(R.array.items_menu_edit_dot, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch(which){
                    case 0:
                        listener.onEditPressed(position);
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
