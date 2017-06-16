package apoco.sandbox;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by Romain on 15/06/2017.
 */

public class Game {

    private Ship myShip;
    private ArrayList<Asteroid> asteroids;
    private final int maxAsteroids = 6;
    private boolean gameOver = false;
    private int score = 0;
    private int width;
    private int height;

    public Game(){
        width = CImageView.WIDTH_SCREEN;
        height = CImageView.HEIGHT_SCREEN;

        myShip = new Ship(width / 2, height / 2, 25);
        asteroids = new ArrayList<>();
        for(int i = 0; i < maxAsteroids; i++){
            asteroids.add(new Asteroid());
        }
    }

    public void update(Canvas canvas){

        // -*-*-*-*- White paint to write actual score -*-*-*-*-*-
        Paint p = new Paint();
        p.setARGB(255, 255, 255, 255);
        p.setTextAlign(Paint.Align.CENTER);
        p.setTextSize(width/40);
        canvas.drawText(score + " points", width / 2, p.getTextSize(), p);

        // *-*-*-*-*-* Let's Check if the game is over *-*-*-*-*-*-*
        /*
        if(gameOver){

            canvas.drawText("GAME OVER!", CImageView.WIDTH_SCREEN / 2, CImageView.HEIGH_SCREEN / 2, p);
            return;
        }
        */

        // *-*-*-*-*-* Generate a new wave of asteroids if there is no more asteroid *-*-*-*-*-*
        if(asteroids.size() == 0){
            for(int i = 0; i < maxAsteroids; i++){
                asteroids.add(new Asteroid());
            }
        }

        // *-*-*-*-*-* Display and update the state of each asteroid and the ship *-*-*-*-*-*
        myShip.display(canvas);
        for(Asteroid a : asteroids){
            a.display(canvas);
            a.update();
        }
        myShip.update();

        // *-*-*-*-*-* Let's check if a shot has destroyed an asteroid, if yes,
        // *-*-*-*-*-*       the shot is added to an array to be deleted, and same for an asteroid *-*-*-*-*-*
        ArrayList<Asteroid> destructedAst = new ArrayList<>();
        ArrayList<Shot> destructedShots = new ArrayList<>();

        for(Asteroid a : asteroids){
            // Getting some datas about the asteroid and the ship, to clarify the code.
            int xa = a.getPos().getX();
            int ya = a.getPos().getY();
            float ra = a.getR();

            int xs = myShip.getPos().getX();
            int ys = myShip.getPos().getY();
            int ls = myShip.getLen();

            double d = dist(xs, ys, xa, ya);

            if(d <= (ra + ls)){
                gameOver = true;
                //System.out.println("Colision !");
                break;
            }

            // *-*-*-*-* Let's check if a shot from the ship touch an asteroid *-*-*-*-*
            for(Shot s : myShip.getShots()){
                int x = s.getPos().getX();
                int y = s.getPos().getY();

                double dis = dist(x, y, xa, ya);
                if(dis < ra){
                    //DESTROYED ASTEROID !!!
                    destructedAst.add(a);
                    destructedShots.add(s);
                }
            }
        }

        // -*-*-*-*-*-*- Now, we can work on the lists of destructed asteroids, and shots. Update the score and create new asteroid based on
        // -*-*-*-*-*-*-             the size and velocity of the destructed asteroid, and then remove the main asteroid. *-*-*-*-*-*-*-*
        for(Asteroid a : destructedAst){
            score += 10 * (a.getLevel() + 1);
            //split the asteroid
            //remove the main asteroid from the arraylist
            if(a.getLevel() == 2){ //if it's the tiny asteroid, just remove it
                asteroids.remove(a);
                continue;
            }

            int xa = a.getPos().getX();
            int ya = a.getPos().getY();
            int lvl = a.getLevel() + 1;
            double angle = a.getAngle();

            CVector2D newDir = a.getPos().rotate(a.getVelocity(), Math.PI / 2);
            Asteroid new1 = new Asteroid(xa, ya, newDir.getX(), newDir.getY(), angle + Math.PI / 2, lvl);

            newDir = a.getPos().rotate(a.getVelocity(), -(Math.PI / 2));
            Asteroid new2 = new Asteroid(xa, ya, newDir.getX(), newDir.getY(), angle - Math.PI / 2, lvl);

            asteroids.add(new1);
            asteroids.add(new2);

            asteroids.remove(a);
        }

        // *-*-*-*-*-* Remove shots *-*-*-*-*-*-*
        for(Shot s : destructedShots){
            myShip.getShots().remove(s);
        }
    }

    private double dist(int x, int y, int i, int j){
        return Math.sqrt(Math.pow(x - i, 2) + Math.pow(y - j, 2));
    }

    public Ship getShip(){
        return myShip;
    }
}
