package apoco.sandbox;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Romain on 12/06/2017.
 */

public class Shot {
    private CVector2D pos;
    private double angle;
    private CVector2D velocity;

    public Shot(int x, int y, double a){
        pos = new CVector2D(x, y);
        angle = a;
        velocity = new CVector2D((int)(Math.cos(a) * 180 / Math.PI), (int)(Math.sin(a) * 180 / Math.PI));
        velocity.mult(0.5);
    }

    public void display(Canvas c){
        Paint p = new Paint();
        p.setARGB(255, 255, 255, 255);
        p.setStrokeWidth(5);

        c.drawPoint(pos.getX(), pos.getY(), p);
    }

    public void update(){
        pos.add(velocity);
    }

    //return true if the point is out of the screen
    public boolean isOutEdges(int bw, int bh){
        int x = pos.getX();
        int y = pos.getY();
        return x >= bw || x < 0 || y >= bh || y < 0;
    }

    public CVector2D getPos(){
        return pos;
    }
}
