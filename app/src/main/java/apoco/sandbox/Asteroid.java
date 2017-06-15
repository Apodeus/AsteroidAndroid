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
    private int r = 100;

    public Asteroid(){
        Random rand = new Random();
        int h = rand.nextInt()%2;
        if(h == 1)
            h = CImageView.HEIGHT_SCREEN;
        else
            h = 0;

        pos = new CVector2D(rand.nextInt()%CImageView.WIDTH_SCREEN, h);
        angle = rand.nextDouble()%(2*Math.PI); //Generate a random angle between [0; 2PI]
        level = Math.abs(rand.nextInt()%3);

        int Vx = (int)(Math.cos(angle) * 180 / Math.PI);
        int Vy = (int)(Math.sin(angle) * 180 / Math.PI);
        velocity = new CVector2D(Vx, Vy);
        velocity.mult(rand.nextFloat()%0.1f + 0.05f);
    }

    public Asteroid(int x, int y, int Vx, int Vy, double angle, int lvl){
        pos = new CVector2D(x, y);
        velocity = new CVector2D(Vx, Vy);
        velocity.mult(0.009f);

        level = lvl;
        this.angle = angle;
    }

    public void display(Canvas c){
        // =#=#=#=#= ADD ROTATION WHILE MOVING WHEN CHANGE THE CIRCLE DISPLAY... =#=#=#=#=

        Paint pWhite = new Paint();
        pWhite.setARGB(255, 255, 255, 255);
        pWhite.setStrokeWidth(4);

        Paint pBlack = new Paint();
        pBlack.setARGB(255, 0, 0, 0);

        float radius = r / (level + 1);

        c.drawCircle(pos.getX(), pos.getY(), radius, pWhite);
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
        if(pos.getY() >= CImageView.HEIGHT_SCREEN){
            pos.setY(0);
        }
        if(pos.getX() < 0){
            pos.setX(CImageView.WIDTH_SCREEN);
        }
        if(pos.getY() < 0){
            pos.setY(CImageView.HEIGHT_SCREEN);
        }
    }

    //get the radius of an asteroid based on its level and on the max radius (nammed 'r' here)
    public float getR(){
        return r / (level + 1);
    }

    public CVector2D getPos(){
        return pos;
    }

    public int getLevel(){return level;}

    public double getAngle(){return angle;}

    public CVector2D getVelocity(){return velocity;}

}
