package com.example.encore;

import android.widget.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Dice {
    private HashMap<String, Die> DiceList;
    private ArrayList<String> DiceKeys;
    //private ArrayList<ImageView> ImageViews;

    public Dice(ArrayList<ImageView> images, ArrayList<String> keys) {
        DiceList = new HashMap<String, Die>();
        DiceKeys = keys;
        for (int i = 0; i < keys.size(); i++) {
            DiceList.put(DiceKeys.get(i), new Die(images.get(i)));
        }
    }

    public HashMap<String, Die> getDiceList(){
        return DiceList;
    }

    public void RollDice(){
        for (Map.Entry<String, Die> die :
                DiceList.entrySet()) {
            die.getValue().Roll();
        }
    }

    public void MakeHidden(){
        for (Map.Entry<String, Die> die :
                DiceList.entrySet()) {
            die.getValue().makeInvisible();
        }
    }

    public void MakeVisible(){
        for (Map.Entry<String, Die> die :
                DiceList.entrySet()) {
            die.getValue().makeVisible();
        }
    }

    public void MakeOnes(){
        for (int i = 0; i < DiceKeys.size(); i++) {
            DiceList.get(DiceKeys.get(i)).getDieFaceView().setImageResource(R.drawable.die1);
        }
    }

    public int ScoreDice(){
        int totalScore = 0;
        for (String key :
                DiceKeys) {
            switch (DiceList.get(key).getValue()){
                case 1:
                    totalScore += 1;
                    break;
                case 2:
                    totalScore += 2;
                    break;
                case 3:
                    totalScore += 3;
                    break;
                case 4:
                    totalScore += 4;
                    break;
                case 5:
                    totalScore += 5;
                    break;
                case 6:
                    totalScore -= 6;
                    break;
            }
        }
        return totalScore;
    }

}
