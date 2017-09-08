package kmitl.lab03.tiwipab58070044.simplemydot.model;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by tiwip on 9/8/2017.
 */

public class Colors {

    private List<Integer> colorList = new ArrayList<>();

    public Colors() {
        colorList.add(Color.RED);
        colorList.add(Color.GREEN);
        colorList.add(Color.BLUE);
        colorList.add(Color.CYAN);
        colorList.add(Color.MAGENTA);
        colorList.add(Color.YELLOW);
        colorList.add(Color.GRAY);
    }

    public int getColor() {
        return colorList.get(
                new Random().nextInt(colorList.size()));
    }

}
