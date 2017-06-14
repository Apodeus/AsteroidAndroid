package apoco.sandbox;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

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

        int width= context.getResources().getDisplayMetrics().widthPixels;
        int height= context.getResources().getDisplayMetrics().heightPixels;

        System.out.println("mw/mh : " + width + ";" + height);
        myShip = new Ship(width / 2, height / 2, 25, width, height);

    }

    @Override
    public void onDraw(Canvas canvas) {
        myShip.display(canvas);
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
