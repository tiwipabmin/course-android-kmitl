package kmitl.it.tiwipabmin58070044.moneyflow;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kmitl.it.tiwipabmin58070044.moneyflow.database.TransactionInfoDB;
import kmitl.it.tiwipabmin58070044.moneyflow.model.TransactionInfo;

public class TransactionActivity extends AppCompatActivity {

    @BindView(R.id.tv_income)
    TextView tv_income;

    @BindView(R.id.tv_expense)
    TextView tv_expense;

    @BindView(R.id.et_list)
    EditText et_list;

    @BindView(R.id.et_money)
    EditText et_money;

    @BindView(R.id.btn_delete)
    Button btn_delete;

    private TransactionInfoDB transactionInfoDB;
    private TransactionInfo transactionInfo;
    private String type = "income";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if(intent.getParcelableExtra("transactionInfo") != null) {
            transactionInfo = intent.getParcelableExtra("transactionInfo");
            setUp();
        }

        initialInstance();
    }

    private void initialInstance(){

        if(transactionInfo == null) {
            transactionInfo = new TransactionInfo();
        } else {
            btn_delete.setVisibility(View.VISIBLE);
        }

        transactionInfoDB = Room.databaseBuilder(this, TransactionInfoDB.class, "TRANSACTION_INFO_DB")
                .fallbackToDestructiveMigration()
                .build();
    }

    private void setUp(){

        et_money.setText(String.valueOf(transactionInfo.getAmount()));

        et_list.setText(transactionInfo.getDescribe());

        if(transactionInfo.getType().equals("income")){
            tv_income.setBackgroundColor(Color.rgb(0,0,255));
            tv_expense.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.border_shadow_style));
        } else {
            tv_income.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.border_shadow_style));
            tv_expense.setBackgroundColor(Color.rgb(0,0,255));
        }
    }

    private void insertTransaction() {
        new AsyncTask<Void, Void, TransactionInfo>() {
            @Override
            protected TransactionInfo doInBackground(Void... voids) {
                transactionInfoDB.transactionInfoDAO().insert(transactionInfo);

                return null;
            }
        }.execute();
    }

    private void updateTransaction() {
        new AsyncTask<Void, Void, TransactionInfo>() {
            @Override
            protected TransactionInfo doInBackground(Void... voids) {
                transactionInfoDB.transactionInfoDAO().update(transactionInfo);

                return null;
            }
        }.execute();
    }

    private void deleteTransaction() {
        new AsyncTask<Void, Void, TransactionInfo>() {
            @Override
            protected TransactionInfo doInBackground(Void... voids) {
                transactionInfoDB.transactionInfoDAO().delete(transactionInfo);

                return null;
            }
        }.execute();
    }

    @OnClick(R.id.tv_income)
    public void onTvIncomeTouched(){

        type = "income";
        tv_income.setBackgroundColor(Color.rgb(0,0,255));
        tv_expense.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.border_shadow_style));
    }

    @OnClick(R.id.tv_expense)
    public void onTvExpenseTouched(){

        type = "expense";
        tv_income.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.border_shadow_style));
        tv_expense.setBackgroundColor(Color.rgb(0,0,255));
    }

    @OnClick(R.id.btn_save)
    public void onBtnSaveTouched(){
        String describe = et_list.getText().toString();
        String amount = et_money.getText().toString();

        transactionInfo.setType(type);

        if (!describe.isEmpty()) {
            transactionInfo.setDescribe(describe);
        } else {
            Toast.makeText(this, "Please enter description.", Toast.LENGTH_LONG).show();
            return;
        }

        try {
            transactionInfo.setAmount(Integer.parseInt(amount));
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter amount.", Toast.LENGTH_LONG).show();
            return;
        }

        if (transactionInfo.getId() == 0) {
            insertTransaction();
        } else {
            updateTransaction();
        }

        finish();
    }

    @OnClick(R.id.btn_delete)
    public void onBtnDeleteTouched(){

        deleteTransaction();
        finish();
    }
}
