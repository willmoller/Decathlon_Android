package com.example.encore;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Random;

public class EventHighJump extends AppCompatActivity {

    private static final Random RANDOM = new Random();
    private Dice rolledDice;
    private ArrayList<ImageView> ivRolledDice;
    private ArrayList<TextView> jumpGoalViews;
    private HashMap<String, TextView> jumpGoals;
    private ArrayList<String> rolledKeys, jumpGoalKeys;
    private int totalScore, rollsLeft, jumpGoal, colorID, diceSum;
    private Button rollDice, resetGame, leaveGame;
    private String jumpGoalKey;

    private TextView rollsLeftText, scoreText, try10, try12, try14, try16, try18, try20, try22, try24, try26, try28, try30;
    private MediaPlayer mp;

    public static int randomDiceValue(){
        return RANDOM.nextInt(6) + 1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_high_jump);

        rollDice = (Button) findViewById(R.id.button_HighJump_Roll);
        rollDice.setEnabled(false);
        resetGame = (Button) findViewById(R.id.button_HighJump_Replay);
        resetGame.setEnabled(false);
        leaveGame = (Button) findViewById(R.id.button_HighJump_Done);

        rollsLeftText = (TextView) findViewById(R.id.tvHighJumpRollsLeft);
        scoreText = (TextView) findViewById(R.id.tvHighJumpScore);

        totalScore = 0;
        rollsLeft = 3;
        jumpGoal = 0;
        jumpGoalKey = "";

        ivRolledDice = new ArrayList<ImageView>();
        rolledKeys = new ArrayList<String>();

        rolledKeys.add("rollDie1");
        rolledKeys.add("rollDie2");
        rolledKeys.add("rollDie3");
        rolledKeys.add("rollDie4");
        rolledKeys.add("rollDie5");

        ivRolledDice.add(findViewById(R.id.ivHighJumpRollDie1));
        ivRolledDice.add(findViewById(R.id.ivHighJumpRollDie2));
        ivRolledDice.add(findViewById(R.id.ivHighJumpRollDie3));
        ivRolledDice.add(findViewById(R.id.ivHighJumpRollDie4));
        ivRolledDice.add(findViewById(R.id.ivHighJumpRollDie5));

        rolledDice = new Dice(ivRolledDice, rolledKeys);
        rolledDice.MakeVisible();

        try10 = (TextView) findViewById(R.id.tvHighJumpGoal10);
        try12 = (TextView) findViewById(R.id.tvHighJumpGoal12);
        try14 = (TextView) findViewById(R.id.tvHighJumpGoal14);
        try16 = (TextView) findViewById(R.id.tvHighJumpGoal16);
        try18 = (TextView) findViewById(R.id.tvHighJumpGoal18);
        try20 = (TextView) findViewById(R.id.tvHighJumpGoal20);
        try22 = (TextView) findViewById(R.id.tvHighJumpGoal22);
        try24 = (TextView) findViewById(R.id.tvHighJumpGoal24);
        try26 = (TextView) findViewById(R.id.tvHighJumpGoal26);
        try28 = (TextView) findViewById(R.id.tvHighJumpGoal28);
        try30 = (TextView) findViewById(R.id.tvHighJumpGoal30);

        jumpGoalKeys = new ArrayList<String>();
        jumpGoalViews = new ArrayList<TextView>();

        jumpGoalKeys.add("try10");
        jumpGoalKeys.add("try12");
        jumpGoalKeys.add("try14");
        jumpGoalKeys.add("try16");
        jumpGoalKeys.add("try18");
        jumpGoalKeys.add("try20");
        jumpGoalKeys.add("try22");
        jumpGoalKeys.add("try24");
        jumpGoalKeys.add("try26");
        jumpGoalKeys.add("try28");
        jumpGoalKeys.add("try30");

        jumpGoalViews.add(try10);
        jumpGoalViews.add(try12);
        jumpGoalViews.add(try14);
        jumpGoalViews.add(try16);
        jumpGoalViews.add(try18);
        jumpGoalViews.add(try20);
        jumpGoalViews.add(try22);
        jumpGoalViews.add(try24);
        jumpGoalViews.add(try26);
        jumpGoalViews.add(try28);
        jumpGoalViews.add(try30);

        jumpGoals = new HashMap<String, TextView>();

        for (int i = 0; i < jumpGoalKeys.size(); i++) {
            jumpGoals.put(jumpGoalKeys.get(i), jumpGoalViews.get(i));
        }

        mp = new MediaPlayer();

        rollDice.setOnClickListener(v -> {

            try {
                mp.stop();
                mp.release();
                mp = MediaPlayer.create(this, R.raw.dice);
                mp.start();
            } catch(Exception e) { e.printStackTrace(); }

            final Animation anim1 = AnimationUtils.loadAnimation(EventHighJump.this, R.anim.shake);
            final Animation anim2 = AnimationUtils.loadAnimation(EventHighJump.this, R.anim.shake);
            final Animation anim3 = AnimationUtils.loadAnimation(EventHighJump.this, R.anim.shake);
            final Animation anim4 = AnimationUtils.loadAnimation(EventHighJump.this, R.anim.shake);
            final Animation anim5 = AnimationUtils.loadAnimation(EventHighJump.this, R.anim.shake);

            diceSum = 0;

            final Animation.AnimationListener animationListener = new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    int value = randomDiceValue();
                    int res = 0;
                    switch (value) {
                        case 1:
                            res = R.drawable.die1;
                            break;
                        case 2:
                            res = R.drawable.die2;
                            break;
                        case 3:
                            res = R.drawable.die3;
                            break;
                        case 4:
                            res = R.drawable.die4;
                            break;
                        case 5:
                            res = R.drawable.die5;
                            break;
                        case 6:
                            res = R.drawable.die6;
                            break;
                    }
                    if (animation == anim1){
                        rolledDice.getDiceList().get("rollDie1").getDieFaceView().setImageResource(res);
                        rolledDice.getDiceList().get("rollDie1").setValue(value);
                        diceSum++;
                    }else if (animation == anim2){
                        rolledDice.getDiceList().get("rollDie2").getDieFaceView().setImageResource(res);
                        rolledDice.getDiceList().get("rollDie2").setValue(value);
                        diceSum++;
                    }else if (animation == anim3){
                        rolledDice.getDiceList().get("rollDie3").getDieFaceView().setImageResource(res);
                        rolledDice.getDiceList().get("rollDie3").setValue(value);
                        diceSum++;
                    } else if (animation == anim4){
                        rolledDice.getDiceList().get("rollDie4").getDieFaceView().setImageResource(res);
                        rolledDice.getDiceList().get("rollDie4").setValue(value);
                        diceSum++;
                    } else if (animation == anim5){
                        rolledDice.getDiceList().get("rollDie5").getDieFaceView().setImageResource(res);
                        rolledDice.getDiceList().get("rollDie5").setValue(value);
                        diceSum++;
                    }
                    if (diceSum == 5){
                        CheckSuccessfulJump();
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            };

            anim1.setAnimationListener(animationListener);
            anim2.setAnimationListener(animationListener);
            anim3.setAnimationListener(animationListener);
            anim4.setAnimationListener(animationListener);
            anim5.setAnimationListener(animationListener);

            rolledDice.getDiceList().get("rollDie1").setAnimation(anim1);
            rolledDice.getDiceList().get("rollDie2").setAnimation(anim2);
            rolledDice.getDiceList().get("rollDie3").setAnimation(anim3);
            rolledDice.getDiceList().get("rollDie4").setAnimation(anim4);
            rolledDice.getDiceList().get("rollDie5").setAnimation(anim5);

            diceSum = 0;
            for (String key :
                    rolledKeys) {
                rolledDice.getDiceList().get(key).getDieFaceView().startAnimation(rolledDice.getDiceList().get(key).getAnimation());
            }
        });

        resetGame.setOnClickListener(v -> {
            resetGame.setEnabled(false);
            rollDice.setEnabled(false);
            rolledDice.MakeOnes();
            for (String key :
                    jumpGoalKeys) {
                jumpGoals.get(key).setBackgroundColor(Color.WHITE);
            }
            jumpGoalKey = "";
            totalScore = 0;
            scoreText.setText("-");
            rollsLeft = 3;
            rollsLeftText.setText(Integer.toString(rollsLeft));
        });

        leaveGame.setOnClickListener(v -> finish());
    }

    private void CheckSuccessfulJump() {

        rollsLeft--;
        rollsLeftText.setText(Integer.toString(rollsLeft));

        diceSum = rolledDice.ScoreDiceNormal();

        if (diceSum >= jumpGoal){
            totalScore = jumpGoal;
            scoreText.setText(Integer.toString(totalScore));
            rollDice.setEnabled(false);
            jumpGoals.get(jumpGoalKey).setBackgroundColor(Color.GREEN);
            jumpGoals.get(jumpGoalKey).setClickable(false);
            for (int i = 0; i < jumpGoalKeys.indexOf(jumpGoalKey); i++){
                jumpGoals.get(jumpGoalKeys.get(i)).setClickable(false);
                jumpGoals.get(jumpGoalKeys.get(i)).setBackgroundColor(Color.GRAY);
            }
            jumpGoalKey = "";
            rollsLeft = 3;
            rollsLeftText.setText(Integer.toString(rollsLeft));
        } else if (rollsLeft == 0){
            rollDice.setEnabled(false);
            resetGame.setEnabled(true);
            jumpGoals.get(jumpGoalKey).setBackgroundColor(Color.RED);
            scoreText.setText(Integer.toString(totalScore));
        } else {

        }


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
        );
    }

    public void setJumpGoal(View v){

        if (!jumpGoalKey.isEmpty()){
            jumpGoals.get(jumpGoalKey).setBackgroundColor(Color.WHITE);
        }

        switch (v.getId()){
            case R.id.tvHighJumpGoal10:
                jumpGoal = 10;
                jumpGoalKey = jumpGoalKeys.get(0);
                break;
            case R.id.tvHighJumpGoal12:
                jumpGoal = 12;
                jumpGoalKey = jumpGoalKeys.get(1);
                break;
            case R.id.tvHighJumpGoal14:
                jumpGoal = 14;
                jumpGoalKey = jumpGoalKeys.get(2);
                break;
            case R.id.tvHighJumpGoal16:
                jumpGoal = 16;
                jumpGoalKey = jumpGoalKeys.get(3);
                break;
            case R.id.tvHighJumpGoal18:
                jumpGoal = 18;
                jumpGoalKey = jumpGoalKeys.get(4);
                break;
            case R.id.tvHighJumpGoal20:
                jumpGoal = 20;
                jumpGoalKey = jumpGoalKeys.get(5);
                break;
            case R.id.tvHighJumpGoal22:
                jumpGoal = 22;
                jumpGoalKey = jumpGoalKeys.get(6);
                break;
            case R.id.tvHighJumpGoal24:
                jumpGoal = 24;
                jumpGoalKey = jumpGoalKeys.get(7);
                break;
            case R.id.tvHighJumpGoal26:
                jumpGoal = 26;
                jumpGoalKey = jumpGoalKeys.get(8);
                break;
            case R.id.tvHighJumpGoal28:
                jumpGoal = 28;
                jumpGoalKey = jumpGoalKeys.get(9);
                break;
            case R.id.tvHighJumpGoal30:
                jumpGoal = 30;
                jumpGoalKey = jumpGoalKeys.get(10);
                break;
        }

        v.setBackgroundColor(Color.YELLOW);
        colorID = Color.YELLOW;
        rollDice.setEnabled(true);
    }
}