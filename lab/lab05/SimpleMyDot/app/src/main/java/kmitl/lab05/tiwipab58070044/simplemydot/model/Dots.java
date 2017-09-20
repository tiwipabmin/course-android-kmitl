package kmitl.lab05.tiwipab58070044.simplemydot.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiwip on 9/17/2017.
 */

public class Dots implements Parcelable{

    public Dots() {}

    protected Dots(Parcel in) {
        allDot = in.createTypedArrayList(Dot.CREATOR);
    }

    public static final Creator<Dots> CREATOR = new Creator<Dots>() {
        @Override
        public Dots createFromParcel(Parcel in) {
            return new Dots(in);
        }

        @Override
        public Dots[] newArray(int size) {
            return new Dots[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(allDot);
    }

    public interface OnDotsChangeListener {
        public void onDotsChanged(Dots dots);
    }

    private List<Dot> allDot = new ArrayList<Dot>();
    private OnDotsChangeListener listener;

    public void addDot(Dot dot){
        allDot.add(dot);
        this.listener.onDotsChanged(this);
    }

    public void setDot(int position, Dot dot){
        allDot.set(position, dot);
        this.listener.onDotsChanged(this);
    }

    public void removeBy(int position) {
        allDot.remove(position);
        this.listener.onDotsChanged(this);
    }

    public List<Dot> getAllDot() {
        return allDot;
    }

    public void setAllDot(List<Dot> allDot) {
        this.allDot = allDot;
    }

    public void setListener(OnDotsChangeListener listener) {
        this.listener = listener;
    }

    public void clearAll(){
        allDot.clear();
        this.listener.onDotsChanged(this);
    }

    public int findDot(int x, int y){
        for(int i = allDot.size() - 1; i >= 0; i--) {
            int centerX = allDot.get(i).getCenterX();
            int centerY = allDot.get(i).getCenterY();
            double distance = Math.sqrt(Math.pow(centerX - x, 2)) +
                    Math.sqrt(Math.pow(centerY - y, 2));
            if(distance <= allDot.get(i).getRadius()){
                return i;
            }
        }
        return -1;
    }

}
