package com.example.encore;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class Die {
    private int value;
    private ImageView dieFaceView;
    private Random r;

    public Die(ImageView newDieFaceView){
        dieFaceView = newDieFaceView;
        dieFaceView.setVisibility(View.INVISIBLE);
        r = new Random();
        dieFaceView.setBackgroundColor(Color.TRANSPARENT);
        dieFaceView.setClickable(false);
    }

    public int getValue(){
        return value;
    }

    public void setValue(int newValue){
        value = newValue;
    }

    public ImageView getDieFaceView(){
        return dieFaceView;
    }

    public void setDieFaceView(ImageView newDieFaceView){
        dieFaceView = newDieFaceView;
    }

    public void makeVisible(){
        dieFaceView.setVisibility(View.VISIBLE);
    }

    public void makeInvisible(){
        dieFaceView.setVisibility(View.INVISIBLE);
    }

    public void Roll(){
        value = r.nextInt(6) + 1;
    }

    public void MakeClickable(){
        dieFaceView.setClickable(true);
    }

    public void MakeUnclickable(){
        dieFaceView.setClickable(false);
    }
}
