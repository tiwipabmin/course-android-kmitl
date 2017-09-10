package kmitl.lab04.tiwipab58070044.simplemydot.view;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import kmitl.lab04.tiwipab58070044.simplemydot.R;

/**
 * Created by tiwip on 9/10/2017.
 */

public class Dialog {

    public interface OnShowedDialog {

        public void onEditPressed(int position);

        public void onDeletePressed(int position);
    }

    private OnShowedDialog listener;

    public void setListener(OnShowedDialog listener) {
        this.listener = listener;
    }

    private Context context;

    public Dialog(Context context){
        this.context = context;
    }

    public void showDialog(final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.menu_title);
        builder.setItems(R.array.items_menu, new DialogInterface.OnClickListener() {
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
