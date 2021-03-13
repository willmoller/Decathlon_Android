package com.example.encore;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class Die {
    private int value;
    private ImageView dieFaceView;

    public Die(ImageView newDieFaceView){
        dieFaceView = newDieFaceView;
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
}
