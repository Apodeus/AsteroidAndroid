package apoco.sandbox;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public CImageView civ;
    private static boolean fingerDown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        //Put the orientation of the screen on landscape mode, and background color in Black
        getWindow().getDecorView().setBackgroundColor(Color.BLACK);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // *-*-*-* Init Custom ImageView *-*-*-*-*
        civ = (CImageView) findViewById(R.id.cImageView);

        // -*-*-*-*-*-* Init Buttons *-*-*-*-*-*
        final Button button_right = (Button) findViewById(R.id.button_rota_right);
        final Button button_left = (Button) findViewById(R.id.button_rota_left);
        final Button button_forward = (Button) findViewById(R.id.button_forward);
        final Button button_fire = (Button) findViewById(R.id.button_fire);

        // *-*-*-*-* Init behaviour of buttons *-*-*-*-*-

        button_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    civ.getGame().getShip().addAngle(0.1f);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    civ.getGame().getShip().addAngle(0);
                }else if((event.getAction() == MotionEvent.ACTION_MOVE)){
                    if ((event.getRawX() >= button_left.getX()) && (event.getRawX() < button_left.getX() + button_left.getWidth())
                            && (event.getRawY() >= button_left.getY()) && (event.getRawY() < button_left.getY() + button_left.getHeight())){
                        civ.getGame().getShip().addAngle(-0.1f);
                    } else {
                        civ.getGame().getShip().addAngle(0.1f);
                    }
                }
                return true;
            }
        });

        button_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    civ.getGame().getShip().addAngle(-0.1f);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    civ.getGame().getShip().addAngle(0);
                } else if((event.getAction() == MotionEvent.ACTION_MOVE)){
                    if ((event.getRawX() >= button_right.getX()) && (event.getRawX() < button_right.getX() + button_right.getWidth())
                            && (event.getRawY() >= button_right.getY()) && (event.getRawY() < button_right.getY() + button_right.getHeight())) {
                        civ.getGame().getShip().addAngle(0.1f);
                    } else {
                        civ.getGame().getShip().addAngle(-0.1f);
                    }
                }

                return true;
            }
        });

        button_forward.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    //System.out.println("forward");
                    civ.getGame().getShip().setMoving(true);
                    fingerDown = true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    civ.getGame().getShip().setMoving(false);
                    civ.getGame().getShip().addAngle(0);
                    fingerDown = false;
                } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    if ((event.getRawX() >= button_right.getX()) && (event.getRawX() < button_right.getX() + button_right.getWidth())
                            && (event.getRawY() >= button_right.getY()) && (event.getRawY() < button_right.getY() + button_right.getHeight())) {
                        //System.out.println("RIGHT AND FORWARD ");
                        civ.getGame().getShip().addAngle(0.1f);
                    } else if ((event.getRawX() >= button_left.getX()) && (event.getRawX() < button_left.getX() + button_left.getWidth())
                            && (event.getRawY() >= button_left.getY()) && (event.getRawY() < button_left.getY() + button_left.getHeight())){
                        civ.getGame().getShip().addAngle(-0.1f);
                    } else {
                        civ.getGame().getShip().addAngle(0);
                    }
                }
                return false;
            }
        });

        button_fire.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    //civ.getGame().getShip().fire();
                    civ.getGame().getShip().setFiring(true);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    civ.getGame().getShip().setFiring(false);
                }
                return false;
            }
        });



    }
}
