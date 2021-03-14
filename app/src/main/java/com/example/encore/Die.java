package com.example.encore;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

public class Die {
    private int value;
    private ImageView dieFaceView;

    public Die(ImageView newDieFaceView){
        dieFaceView = newDieFaceView;
        dieFaceView.setVisibility(View.INVISIBLE);
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
}
