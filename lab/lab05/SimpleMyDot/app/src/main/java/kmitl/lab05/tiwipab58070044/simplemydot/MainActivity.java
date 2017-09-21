package kmitl.lab05.tiwipab58070044.simplemydot;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import kmitl.lab05.tiwipab58070044.simplemydot.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        if(savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .add(R.id.frameLayout, new MainFragment())
                    .addToBackStack("simpleMyDotFragment")
                    .commit();
        }
    }

}

