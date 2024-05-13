package com.example.storrowdrive;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import java.util.Random;

public class Decor {

    Bitmap decor;
    int Xcoordinate;

    public Decor(){
        Random rand = new Random();
        int randNum = rand.nextInt(4);
        int randNumcoord = rand.nextInt(4); //must be different than randNum so that different color chairs can be on different columns
        if(randNumcoord == 2){
            randNumcoord = 4;
        }
        Xcoordinate = randNumcoord * (ConstantVar.WIDTH/5); //So that a tree doesn't spawn where user starts
        switch(randNum){
            case 0:
                decor = ConstantVar.TREE;
                if(rand.nextInt(100) == 0){
                    decor = ConstantVar.RAINBOWCHAIR;
                }
                break;
            case 1:
                decor = ConstantVar.REDCHAIR;
                break;
            case 2:
                decor = ConstantVar.BLUECHAIR;
                break;
            case 3:
                decor = ConstantVar.BROWNCHAIR;
                break;
        }
    }

    public void draw(Canvas canvas, int Ycoordinate){ //draw the decor
        canvas.drawBitmap(decor, Xcoordinate, Ycoordinate, null);
    }

    public boolean collisionCheck(Rhett rhett){ //check if rhett is in the same X coordinate as the decor
        if(rhett.X_positionLeft >= Xcoordinate && rhett.X_positionLeft <= Xcoordinate + 4*(ConstantVar.WIDTH/25)){
            rhett.center();
            return true;
        }
        return false;
    }
}
