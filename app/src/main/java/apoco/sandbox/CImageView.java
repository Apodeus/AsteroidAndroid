package apoco.sandbox;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

/**
 * Created by Romain on 11/06/2017.
 */

public class CImageView extends View {

    private Ship myShip;

    public CImageView(Context context) {
        super(context);
    }

    public CImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        System.out.println("mw/mh : " + MainActivity.WIDTH_SCREEN + ";" + MainActivity.HEIGHT_SCREEN);
        myShip = new Ship(MainActivity.WIDTH_SCREEN / 2, MainActivity.HEIGHT_SCREEN / 2, 25);

    }

    @Override
    public void onDraw(Canvas canvas) {
        myShip.display(canvas);
        myShip.update();

        invalidate();
    }

    public Ship getShip(){
        return myShip;
    }

}
