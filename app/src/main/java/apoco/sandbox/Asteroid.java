package apoco.sandbox;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Random;

/**
 * Created by Romain on 12/06/2017.
 */

public class Asteroid {
    private CVector2D pos;
    private CVector2D velocity;
    private double angle;
    private int level;
    private int r;

    public Asteroid(){
        Random rand = new Random();
        int h = rand.nextInt()%2;
        if(h == 1)
            h = CImageView.HEIGH_SCREEN;
        else
            h = 0;

        pos = new CVector2D(rand.nextInt()%CImageView.WIDTH_SCREEN, h);// faire le x random, et le y soit 0 soit max screen
        angle = rand.nextDouble()%(2*Math.PI);
        velocity = new CVector2D((int)(Math.cos(angle) * 180 / Math.PI), (int)(Math.sin(angle) * 180 / Math.PI));
        level = rand.nextInt()%3;
        r = 80;

        velocity.mult(rand.nextFloat()%0.2f + 0.1f);
    }

    public void display(Canvas c){
        // =#=#=#=#= ADD ROTATION WHILE MOVING WHEN CHANGE THE CIRCLE DISPLAY... =#=#=#=#=

        Paint p = new Paint();
        p.setARGB(255, 255, 255, 255);
        p.setStrokeWidth(4);

        Paint pBlack = new Paint();
        pBlack.setARGB(255, 0, 0, 0);

        //Tmp ... Need a rework direclty based on the actual level of the asteroid ( level is between [0;2] )
        int radius = r / (level + 1);

        c.drawCircle(pos.getX(), pos.getY(), radius, p);
        c.drawCircle(pos.getX(), pos.getY(), radius - 2, pBlack);
    }

    public void update(){
        pos.add(velocity);
        this.edges();
    }

    private void edges(){
        if(pos.getX() >= CImageView.WIDTH_SCREEN){
            pos.setX(0);
        }
        if(pos.getY() >= CImageView.HEIGH_SCREEN){
            pos.setY(0);
        }
        if(pos.getX() < 0){
            pos.setX(CImageView.WIDTH_SCREEN);
        }
        if(pos.getY() < 0){
            pos.setY(CImageView.HEIGH_SCREEN);
        }
    }

}
