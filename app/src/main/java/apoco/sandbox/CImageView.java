package apoco.sandbox;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Romain on 11/06/2017.
 */

public class CImageView extends View {

    public static int WIDTH_SCREEN;
    public static int HEIGHT_SCREEN;

    private Game actualGame;

    public CImageView(Context context) {
        super(context);
    }

    public CImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        int width= context.getResources().getDisplayMetrics().widthPixels;
        int height= context.getResources().getDisplayMetrics().heightPixels;
        WIDTH_SCREEN = width;
        HEIGHT_SCREEN = height;

        actualGame = new Game();

        //System.out.println("mw/mh : " + width + ";" + height);

    }

    @Override
    public void onDraw(Canvas canvas) {
        actualGame.update(canvas);

        invalidate();
    }

    public Game getGame(){return actualGame;}
}
