package kmitl.lab05.tiwipab58070044.simplemydot;

import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Random;

import kmitl.lab05.tiwipab58070044.simplemydot.fragment.EditDotFragment;
import kmitl.lab05.tiwipab58070044.simplemydot.fragment.MainFragment;
import kmitl.lab05.tiwipab58070044.simplemydot.model.Colors;
import kmitl.lab05.tiwipab58070044.simplemydot.model.Dot;
import kmitl.lab05.tiwipab58070044.simplemydot.model.Dots;
import kmitl.lab05.tiwipab58070044.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.frameLayout, new MainFragment())
                .addToBackStack("simpleMyDotFragment")
                .commit();
    }

}

