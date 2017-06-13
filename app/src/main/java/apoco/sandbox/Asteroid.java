package apoco.sandbox;

/**
 * Created by Romain on 12/06/2017.
 */

public class Asteroid {
    private CVector2D pos;
    private CVector2D velocity;
    private int level;
    private int r;

    public Asteroid(){
        pos = new CVector2D(0, 0);// faire le x random, et le y soit 0 soit max screen

    }
}
