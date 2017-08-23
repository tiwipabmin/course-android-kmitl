package mobile.first.homework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class NewFlipboard extends AppCompatActivity {

    private Button btn_seeNewTopics;
    private TextView tv_termsOfUse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_flipboard);

        bindWidget();
        update();
    }

    private void bindWidget(){

        btn_seeNewTopics = (Button) findViewById(R.id.btn_seeNewTopics);

        tv_termsOfUse = (TextView) findViewById(R.id.tv_termsOfUse);
    }

    private void update(){

    }
}
