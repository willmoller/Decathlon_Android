package com.example.encore;

import android.graphics.Color;
import android.view.View;
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

    public ArrayList<String> getDiceKeys(){
        return DiceKeys;
    }

    public void RollDice(){
        for (Map.Entry<String, Die> die :
                DiceList.entrySet()) {
            die.getValue().Roll();
        }
    }

    public void RemoveDie(String key){
        DiceList.remove(key);
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
        for (String key :
                DiceKeys) {
            DiceList.get(key).getDieFaceView().setImageResource(R.drawable.die1);
        }
//        for (int i = 0; i < DiceKeys.size(); i++) {
//            DiceList.get(DiceKeys.get(i)).getDieFaceView().setImageResource(R.drawable.die1);
//            DiceList.get(DiceKeys.get(i)).setValue(0);
//        }
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

    public int ScoreDiceNormal(){
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
                    totalScore += 6;
                    break;
            }
        }
        return totalScore;
    }

    public boolean CheckIfOneRolled(int numDice){
        boolean rolledOne = false;
        for (int i = 0; i < numDice; i++){
            switch (DiceList.get(DiceKeys.get(i)).getValue()){
                case 1:
                    rolledOne = true;
                    break;
            }
        }
        return rolledOne;
    }

    public int ScoreDiceNormalSubset(int numDice){
        int totalScore = 0;
        for (int i = 0; i < numDice; i++){
            switch (DiceList.get(DiceKeys.get(i)).getValue()){
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
                    totalScore += 6;
                    break;
            }
        }
        return totalScore;
    }
    
    public void animateDice(){

    }

    public void MakeClickable(){
        for (Map.Entry<String, Die> die :
                DiceList.entrySet()) {
            die.getValue().MakeClickable();
        }
    }

    public void MakeClickable(ArrayList<String> keys){
        for (String key :
                keys) {
            DiceList.get(key).MakeClickable();
        }
    }

    public void MakeUnclickable(){
        for (Map.Entry<String, Die> die :
                DiceList.entrySet()) {
            die.getValue().MakeUnclickable();
        }
    }

    public void MakeUnclickable(ArrayList<String> keys){
        for (String key :
                keys) {
            DiceList.get(key).MakeUnclickable();
        }
    }

    public void HideUnusedDice(int unusedDice){
        switch (unusedDice){
            case 1:
                for (int i = 1; i < 5; i++){
                    DiceList.get(DiceKeys.get(i)).makeInvisible();
                }
                break;
            case 2:
                for (int i = 2; i < 5; i++){
                    DiceList.get(DiceKeys.get(i)).makeInvisible();
                }
                break;
            case 3:
                for (int i = 3; i < 5; i++){
                    DiceList.get(DiceKeys.get(i)).makeInvisible();
                }
                break;
            case 4:
                for (int i = 4; i < 5; i++){
                    DiceList.get(DiceKeys.get(i)).makeInvisible();
                }
                break;
            case 5:
                for (int i = 5; i < 5; i++){
                    DiceList.get(DiceKeys.get(i)).makeInvisible();
                }
                break;
        }
    }

    public void ChangeBackgroundColor(int color) {
        for (Map.Entry<String, Die> die :
                DiceList.entrySet()) {
            die.getValue().getDieFaceView().setBackgroundColor(color);
        }
    }

    public void SetDiceViewPadding(int padding){
        for (Map.Entry<String, Die> die :
                DiceList.entrySet()) {
            die.getValue().getDieFaceView().setPadding(padding, padding, padding, padding);
        }
    }
}
