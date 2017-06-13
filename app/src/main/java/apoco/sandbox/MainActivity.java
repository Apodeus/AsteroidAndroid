package apoco.sandbox;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public CImageView civ;
    public static int WIDTH_SCREEN;
    public static int HEIGHT_SCREEN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setBackgroundColor(Color.BLACK);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        WIDTH_SCREEN = metrics.widthPixels;
        HEIGHT_SCREEN = metrics.heightPixels;

        civ = (CImageView) findViewById(R.id.cImageView);

        final Button button_right = (Button) findViewById(R.id.button_rota_right);
        button_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    civ.getShip().addAngle(0.1f);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    civ.getShip().addAngle(0);
                }
                return true;
            }
        });

        final Button button_left = (Button) findViewById(R.id.button_rota_left);
        button_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    civ.getShip().addAngle(-0.1f);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    civ.getShip().addAngle(0);
                }
                return true;
            }
        });

        final Button button_forward = (Button) findViewById(R.id.button_forward);
        button_forward.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    civ.getShip().setMoving(true);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    civ.getShip().setMoving(false);
                }
                return true;
            }
        });

        final Button button_fire = (Button) findViewById(R.id.button_fire);
        button_fire.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    civ.getShip().fire();
                }
                return true;
            }
        });



    }
}