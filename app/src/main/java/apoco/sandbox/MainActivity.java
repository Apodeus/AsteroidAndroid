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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        //Put the orientation of the screen on landscape mode, and background color in Black
        getWindow().getDecorView().setBackgroundColor(Color.BLACK);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        civ = (CImageView) findViewById(R.id.cImageView);

        final Button button_right = (Button) findViewById(R.id.button_rota_right);
        button_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    civ.getGame().getShip().addAngle(0.1f);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    civ.getGame().getShip().addAngle(0);
                }
                return true;
            }
        });

        final Button button_left = (Button) findViewById(R.id.button_rota_left);
        button_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    civ.getGame().getShip().addAngle(-0.1f);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    civ.getGame().getShip().addAngle(0);
                }
                return true;
            }
        });

        final Button button_forward = (Button) findViewById(R.id.button_forward);
        button_forward.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    civ.getGame().getShip().setMoving(true);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    civ.getGame().getShip().setMoving(false);
                }
                return true;
            }
        });

        final Button button_fire = (Button) findViewById(R.id.button_fire);
        button_fire.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    civ.getGame().getShip().fire();
                }
                return true;
            }
        });



    }
}
