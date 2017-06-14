package apoco.sandbox;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Romain on 11/06/2017.
 */

public class CImageView extends View {

    private Ship myShip;
    private ArrayList<Asteroid> asteroids;
    private final int maxAsteroids = 10;
    public static int WIDTH_SCREEN;
    public static int HEIGH_SCREEN;

    public CImageView(Context context) {
        super(context);
    }

    public CImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        int width= context.getResources().getDisplayMetrics().widthPixels;
        int height= context.getResources().getDisplayMetrics().heightPixels;
        WIDTH_SCREEN = width;
        HEIGH_SCREEN = height;

        //System.out.println("mw/mh : " + width + ";" + height);
        myShip = new Ship(width / 2, height / 2, 25);
        asteroids = new ArrayList<>();
        for(int i = 0; i < maxAsteroids; i++){
            asteroids.add(new Asteroid());
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        myShip.display(canvas);
        for(Asteroid a : asteroids){
            a.display(canvas);
            a.update();
        }
        myShip.update();

        invalidate();
    }
    /*
    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        width=this.getWidth();
        height=this.getHeight();
    }*/

    public Ship getShip(){
        return myShip;
    }

}
