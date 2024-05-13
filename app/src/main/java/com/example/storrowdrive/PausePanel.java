package com.example.storrowdrive;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Intent;
import java.util.Random;
import androidx.annotation.NonNull;

public class PausePanel extends SurfaceView implements SurfaceHolder.Callback
{
    Bitmap paused, playbutton;
    int multiX, multiY;

    private PauseThread pauseThread;
    private int playbuttonX = (ConstantVar.WIDTH/3), playbuttonY = (2*ConstantVar.HEIGHT/5);
    public PausePanel(Context context)
    {
        super(context);
        getHolder().addCallback(this); //Register the Panel class as a SurfaceHolder callback listener
        setFocusable(true); //Make the Panel the focus of the screen since it handles user input

        //Set the background
        Bitmap PauseScreen = BitmapFactory.decodeResource(getResources(), R.drawable.gamepaused);
        paused = Bitmap.createScaledBitmap(PauseScreen, ConstantVar.WIDTH, ConstantVar.HEIGHT, false);
        Bitmap PlayButton = BitmapFactory.decodeResource(getResources(), R.drawable.playbutton);
        playbutton = Bitmap.createScaledBitmap(PlayButton, (ConstantVar.WIDTH/3), (ConstantVar.HEIGHT/5), false);
        Random rand = new Random();
        multiX = rand.nextInt(2);
        multiY = rand.nextInt(2);
        if(multiX == 0){
            multiX = -1;
        }
        if(multiY == 0){
            multiY = -1;
        }
    }
    @Override //from SurfaceHolder
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height){}

    @Override //from SurfaceHolder
    public void surfaceCreated(@NonNull SurfaceHolder holder){
        pauseThread = new PauseThread(getHolder(),this);
        pauseThread.setRunning(true);
        pauseThread.start();
    }

    @Override //from SurfaceHolder
    public boolean onTouchEvent(MotionEvent event){
        pauseThread.setRunning(false); //Stop the thread
        pauseThread.interrupt();
        Intent intent = new Intent(ConstantVar.CONTEXT, GameActivity.class); //Create an intent to start the GameActivity
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        ConstantVar.CONTEXT.startActivity(intent);

        return true;
    }

    @Override //from SurfaceHolder
    public void surfaceDestroyed(@NonNull SurfaceHolder holder){}

    public void update(){
        if(playbuttonX >= (2*ConstantVar.WIDTH/3) || playbuttonX <= 0) {
            multiX = -1*multiX;
        }

        if(playbuttonY >= (4*ConstantVar.HEIGHT/5)||playbuttonY <= 0){
            multiY = -1*multiY;
        }

        playbuttonX += multiX*ConstantVar.WIDTH/100;
        playbuttonY += multiY*ConstantVar.HEIGHT/100;

        //implement an if perfect fit in the corner, you get an easter egg.....
    }

    @Override //from SurfaceView
    public void draw(Canvas canvas){
        super.draw(canvas);
        canvas.drawBitmap(paused, 0, 0, null);
        canvas.drawBitmap(playbutton, playbuttonX, playbuttonY, null);
    }
}