package kmitl.it.tiwipabmin58070044.moneyflow.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import kmitl.it.tiwipabmin58070044.moneyflow.R;

public class TransactionInfoHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_transaction_symbol)
    public ImageView iv_transaction_symbol;

    @BindView(R.id.tv_transaction_detail)
    public TextView tv_transaction_detail;

    @BindView(R.id.tv_amountOfMoney)
    public TextView tv_amountOfMoney;

    public TransactionInfoHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }
}
