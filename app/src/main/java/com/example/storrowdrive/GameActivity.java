package com.example.storrowdrive;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;

public class GameActivity extends Activity{
    private GamePanel gamepanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set the window to fullscreen mode
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Get the screen size
        DisplayMetrics screenConstants = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(screenConstants);

        gamepanel = new GamePanel(ConstantVar.CONTEXT); //Create the GamePanel
        setContentView(gamepanel);

    }
}
