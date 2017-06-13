package apoco.sandbox;

/**
 * Created by Romain on 12/06/2017.
 */

public class CVector2D {
    private int x, y;

    public CVector2D(){
        this.x = 0;
        this.y = 0;
    }

    public CVector2D(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){return x;}
    public int getY(){return y;}
    public void setX(int x){this.x = x;}
    public void setY(int y){this.y = y;}

    public void add(CVector2D v){
        this.x += v.getX();
        this.y += v.getY();
    }

    public void mult(double scal){
        this.x *= scal;
        this.y *= scal;
    }
}
