package kmitl.it.tiwipabmin58070044.moneyflow;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kmitl.it.tiwipabmin58070044.moneyflow.adapter.TransactionInfoAdapter;
import kmitl.it.tiwipabmin58070044.moneyflow.database.TransactionInfoDB;
import kmitl.it.tiwipabmin58070044.moneyflow.model.Balance;
import kmitl.it.tiwipabmin58070044.moneyflow.model.TransactionInfo;

public class MainActivity extends AppCompatActivity implements TransactionInfoAdapter.TransactionInfoAdapterListener{

    @BindView(R.id.tv_balance)
    TextView tv_balance;

    @BindView(R.id.list)
    RecyclerView recyclerView;

    @BindView(R.id.btn_transaction)
    Button btn_transaction;

    private TransactionInfoDB transactionInfoDB;
    private List<TransactionInfo> transactionInfoList;
    private TransactionInfo transactionInfo;
    private TransactionInfoAdapter transactionInfoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        initialInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void initialInstance(){
        transactionInfoDB = Room.databaseBuilder(this,
                TransactionInfoDB.class,
                "TRANSACTION_INFO_DB")
                .fallbackToDestructiveMigration()
                .build();

        transactionInfoAdapter = new TransactionInfoAdapter(this, this);
        recyclerView.setAdapter(transactionInfoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadData() {
        new AsyncTask<Void, Void, Balance>() {
            @Override
            protected Balance doInBackground(Void... voids) {
                return transactionInfoDB.transactionInfoDAO().getBalance();
            }

            @Override
            protected void onPostExecute(Balance balance) {
                int total = balance.getBalance();
                double balanceRatio = (double) total/balance.getSum_income();

                if (balanceRatio < 0.25) {
                    tv_balance.setTextColor(Color.parseColor("#cc0000"));
                } else if (balanceRatio <= 0.5) {
                    tv_balance.setTextColor(Color.parseColor("#f1c232"));
                } else {
                    tv_balance.setTextColor(Color.parseColor("#6aa84f"));
                }

                tv_balance.setText(NumberFormat.getNumberInstance().format(total));
            }
        }.execute();

        new AsyncTask<Void, Void, List<TransactionInfo>>() {
            @Override
            protected List<TransactionInfo> doInBackground(Void... voids) {
                return transactionInfoDB.transactionInfoDAO().getAll();
            }

            @Override
            protected void onPostExecute(List<TransactionInfo> transactionInfos) {
                transactionInfoAdapter.setTransactionInfoList(transactionInfos);
                transactionInfoAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

    @OnClick(R.id.btn_transaction)
    public void onBtnTransactionTouched(){

       Intent intent = new Intent(this, TransactionActivity.class);
       startActivity(intent);
    }

    @Override
    public void onItemTouched(TransactionInfo transactionInfo) {

        Intent intent = new Intent(this, TransactionActivity.class);
        intent.putExtra("transactionInfo", transactionInfo);
        startActivity(intent);
    }
}
