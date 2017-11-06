package kmitl.it.tiwipabmin58070044.moneyflow;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kmitl.it.tiwipabmin58070044.moneyflow.database.TransactionInfoDB;
import kmitl.it.tiwipabmin58070044.moneyflow.model.TransactionInfo;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_balance)
    TextView tv_balance;

    @BindView(R.id.list)
    RecyclerView recyclerView;

    @BindView(R.id.btn_transaction)
    Button btn_transaction;

    private TransactionInfoDB transactionInfoDB;
    private List<TransactionInfo> transactionInfoList;
    private TransactionInfo transactionInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initialInstance();
    }

    private void initialInstance(){
        transactionInfoDB = Room.databaseBuilder(this,
                TransactionInfoDB.class,
                "TRANSACTION").build();
    }




}
