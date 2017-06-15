package apoco.sandbox;

import android.graphics.Canvas;
import android.graphics.Paint;
import java.util.ArrayList;

/**
 * Created by Romain on 12/06/2017.
 */

public class Ship {

    private CVector2D pos;      //position of the ship
    private CVector2D velocity; // Velocity of the ship
    private float[] shape;
    private double angle;
    private double rotation;
    private int len;            //Length of the ship
    private boolean isMoving;
    private ArrayList<Shot> shots;

    public Ship(int x, int y, int len){
        pos = new CVector2D(x, y);
        velocity = new CVector2D();
        shape = new float[]{
            x - len, y + len,
            x + len, y + len,
            x      , y - len
        };
        this.len = len;
        angle = 0;
        rotation = 0;
        shots = new ArrayList<>();
    }

    public void display(Canvas c){
        Paint p = new Paint();
        p.setARGB(255, 255, 255, 255);
        p.setTextSize(50);
        p.setStrokeWidth(4);

        for(Shot s: shots){
            s.display(c);
        }

        c.drawLine(shape[0], shape[1], shape[2], shape[3], p);
        c.drawLine(shape[2], shape[3], shape[4], shape[5], p);
        c.drawLine(shape[4], shape[5], shape[0], shape[1], p);
     }

    public void update(){
        if(isMoving) { //rework this part

            CVector2D force = new CVector2D();
            force.setX((int)(Math.cos(this.angle) * (180 / Math.PI)));
            force.setY((int)(Math.sin(this.angle) * (180 / Math.PI)));

            velocity.add(force);
            velocity.mult(0.2);
        }

        pos.add(velocity);
        velocity.mult(0.95);

        shape = new float[]{
            pos.getX() - len, pos.getY() + len,
            pos.getX() + len, pos.getY() + len,
            pos.getX()      , pos.getY() - len
        };

        this.angle += rotation; // A mettre en bouton le set sur rotation (+0.1 ou -0.1)
        this.turn(angle + (Math.PI / 2));
        this.edges();

        ArrayList<Shot> tmp = new ArrayList<>();
        for(Shot s : shots){
            s.update();
            if(s.isOutEdges(CImageView.WIDTH_SCREEN, CImageView.HEIGHT_SCREEN)){
                tmp.add(s);
            }
        }
        shots.removeAll(tmp);
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

    private void turn(double a){
        for(int i = 0; i < shape.length; i+=2) {
            rotatePoint(i, a);
        }
    }

    private void rotatePoint(int ind, double a){
        double x1 = shape[ind] - pos.getX();
        double y1 = shape[ind+1] - pos.getY();

        double x2 = x1 * Math.cos(a) - y1 * Math.sin(a);
        double y2 = x1 * Math.sin(a) + y1 * Math.cos(a);

        shape[ind] = (float)(x2 + pos.getX());
        shape[ind+1] = (float)(y2 + pos.getY());
    }

    public void addAngle(double a){
        this.rotation = a;
    }

    public void setMoving(boolean b){
        this.isMoving = b;
    }

    public void fire(){
        shots.add(new Shot(pos.getX(), pos.getY(), this.angle));
    }

    public CVector2D getPos(){
        return pos;
    }

    public int getLen(){
        return len;
    }

    public ArrayList<Shot> getShots() { return shots;}
}

