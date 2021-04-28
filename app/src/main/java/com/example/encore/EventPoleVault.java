package com.example.encore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EventPoleVault extends AppCompatActivity implements View.OnClickListener {

    private static final Random RANDOM = new Random();
    private Dice rolledDice;
    private ArrayList<ImageView> ivRolledDice;
    private ArrayList<TextView> jumpGoalViews;
    private HashMap<String, TextView> jumpGoals;
    private ArrayList<String> rolledKeys, jumpGoalKeys;
    private int totalScore, rollsLeft, jumpGoal, colorID, diceSum, diceCount, fullGameScore;
    private Button rollDice, resetGame, leaveGame;
    private String jumpGoalKey, action;
    private ImageView rollDie1, rollDie2, rollDie3, rollDie4, rollDie5, rollDie6, rollDie7, rollDie8;
    private TextView rollsLeftText, scoreText, actionPrompt, fullGameScoreLabel, fullGameScoreText,
        try10, try12, try14, try16, try18, try20, try22, try24, try26, try28, try30, try32, try34,
            try36, try38, try40, try42, try44, try46, try48;
    private MediaPlayer mp;
    private boolean rolledOne, isFullGame;
    public static int randomDiceValue(){
        return RANDOM.nextInt(6) + 1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_pole_vault);

        rollDie1 = (ImageView) findViewById(R.id.ivPoleVaultRollDie1);
        rollDie2 = (ImageView) findViewById(R.id.ivPoleVaultRollDie2);
        rollDie3 = (ImageView) findViewById(R.id.ivPoleVaultRollDie3);
        rollDie4 = (ImageView) findViewById(R.id.ivPoleVaultRollDie4);
        rollDie5 = (ImageView) findViewById(R.id.ivPoleVaultRollDie5);
        rollDie6 = (ImageView) findViewById(R.id.ivPoleVaultRollDie6);
        rollDie7 = (ImageView) findViewById(R.id.ivPoleVaultRollDie7);
        rollDie8 = (ImageView) findViewById(R.id.ivPoleVaultRollDie8);

        rollDie1.setOnClickListener(this);
        rollDie2.setOnClickListener(this);
        rollDie3.setOnClickListener(this);
        rollDie4.setOnClickListener(this);
        rollDie5.setOnClickListener(this);
        rollDie6.setOnClickListener(this);
        rollDie7.setOnClickListener(this);
        rollDie8.setOnClickListener(this);

        rollDice = (Button) findViewById(R.id.buttonPoleVaultRoll);
        rollDice.setEnabled(false);
        resetGame = (Button) findViewById(R.id.buttonPoleVaultReset);
        resetGame.setEnabled(false);
        leaveGame = (Button) findViewById(R.id.buttonPoleVaultDone);

        rollsLeftText = (TextView) findViewById(R.id.tvPoleVaultRollsLeft);
        scoreText = (TextView) findViewById(R.id.tvPoleVaultScore);
        actionPrompt = (TextView) findViewById(R.id.tvPoleVaultAction);

        totalScore = 0;
        rollsLeft = 3;
        jumpGoal = 0;
        jumpGoalKey = "";
        diceCount = 0;
        rolledOne = false;
        action = "Pick a Height";

        ivRolledDice = new ArrayList<ImageView>();
        rolledKeys = new ArrayList<String>();

        rolledKeys.add("rollDie1");
        rolledKeys.add("rollDie2");
        rolledKeys.add("rollDie3");
        rolledKeys.add("rollDie4");
        rolledKeys.add("rollDie5");
        rolledKeys.add("rollDie6");
        rolledKeys.add("rollDie7");
        rolledKeys.add("rollDie8");

        ivRolledDice.add(findViewById(R.id.ivPoleVaultRollDie1));
        ivRolledDice.add(findViewById(R.id.ivPoleVaultRollDie2));
        ivRolledDice.add(findViewById(R.id.ivPoleVaultRollDie3));
        ivRolledDice.add(findViewById(R.id.ivPoleVaultRollDie4));
        ivRolledDice.add(findViewById(R.id.ivPoleVaultRollDie5));
        ivRolledDice.add(findViewById(R.id.ivPoleVaultRollDie6));
        ivRolledDice.add(findViewById(R.id.ivPoleVaultRollDie7));
        ivRolledDice.add(findViewById(R.id.ivPoleVaultRollDie8));

        rolledDice = new Dice(ivRolledDice, rolledKeys);
        rolledDice.MakeVisible();
        rolledDice.MakeUnclickable();

        try10 = (TextView) findViewById(R.id.tvPoleVaultGoal10);
        try12 = (TextView) findViewById(R.id.tvPoleVaultGoal12);
        try14 = (TextView) findViewById(R.id.tvPoleVaultGoal14);
        try16 = (TextView) findViewById(R.id.tvPoleVaultGoal16);
        try18 = (TextView) findViewById(R.id.tvPoleVaultGoal18);
        try20 = (TextView) findViewById(R.id.tvPoleVaultGoal20);
        try22 = (TextView) findViewById(R.id.tvPoleVaultGoal22);
        try24 = (TextView) findViewById(R.id.tvPoleVaultGoal24);
        try26 = (TextView) findViewById(R.id.tvPoleVaultGoal26);
        try28 = (TextView) findViewById(R.id.tvPoleVaultGoal28);
        try30 = (TextView) findViewById(R.id.tvPoleVaultGoal30);
        try32 = (TextView) findViewById(R.id.tvPoleVaultGoal32);
        try34 = (TextView) findViewById(R.id.tvPoleVaultGoal34);
        try36 = (TextView) findViewById(R.id.tvPoleVaultGoal36);
        try38 = (TextView) findViewById(R.id.tvPoleVaultGoal38);
        try40 = (TextView) findViewById(R.id.tvPoleVaultGoal40);
        try42 = (TextView) findViewById(R.id.tvPoleVaultGoal42);
        try44 = (TextView) findViewById(R.id.tvPoleVaultGoal44);
        try46 = (TextView) findViewById(R.id.tvPoleVaultGoal46);
        try48 = (TextView) findViewById(R.id.tvPoleVaultGoal48);

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
        jumpGoalKeys.add("try32");
        jumpGoalKeys.add("try34");
        jumpGoalKeys.add("try36");
        jumpGoalKeys.add("try38");
        jumpGoalKeys.add("try40");
        jumpGoalKeys.add("try42");
        jumpGoalKeys.add("try44");
        jumpGoalKeys.add("try46");
        jumpGoalKeys.add("try48");

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
        jumpGoalViews.add(try32);
        jumpGoalViews.add(try34);
        jumpGoalViews.add(try36);
        jumpGoalViews.add(try38);
        jumpGoalViews.add(try40);
        jumpGoalViews.add(try42);
        jumpGoalViews.add(try44);
        jumpGoalViews.add(try46);
        jumpGoalViews.add(try48);

        jumpGoals = new HashMap<String, TextView>();

        for (int i = 0; i < jumpGoalKeys.size(); i++) {
            jumpGoals.put(jumpGoalKeys.get(i), jumpGoalViews.get(i));
        }

        mp = new MediaPlayer();

        Intent intent = getIntent();
        isFullGame = intent.getBooleanExtra("isFullGame", false);
        fullGameScoreLabel = (TextView) findViewById(R.id.tvPoleVaultTotalScoreLabel);
        fullGameScoreText = (TextView) findViewById(R.id.tvPoleVaultTotalScore);

        if (!isFullGame){
            fullGameScoreLabel.setVisibility(View.INVISIBLE);
            fullGameScoreText.setVisibility(View.INVISIBLE);
            resetGame.setText("Replay");
        } else {
            fullGameScore = intent.getIntExtra("fullGameScore", 0);
            fullGameScoreText.setText(Integer.toString(fullGameScore));
            resetGame.setText("Next");
        }

        rollDice.setOnClickListener(v -> {
            try {
                mp.stop();
                mp.release();
                mp = MediaPlayer.create(this, R.raw.dice);
                mp.start();
            } catch(Exception e) { e.printStackTrace(); }

            final Animation anim1 = AnimationUtils.loadAnimation(EventPoleVault.this, R.anim.shake);
            final Animation anim2 = AnimationUtils.loadAnimation(EventPoleVault.this, R.anim.shake);
            final Animation anim3 = AnimationUtils.loadAnimation(EventPoleVault.this, R.anim.shake);
            final Animation anim4 = AnimationUtils.loadAnimation(EventPoleVault.this, R.anim.shake);
            final Animation anim5 = AnimationUtils.loadAnimation(EventPoleVault.this, R.anim.shake);
            final Animation anim6 = AnimationUtils.loadAnimation(EventPoleVault.this, R.anim.shake);
            final Animation anim7 = AnimationUtils.loadAnimation(EventPoleVault.this, R.anim.shake);
            final Animation anim8 = AnimationUtils.loadAnimation(EventPoleVault.this, R.anim.shake);

            diceSum = 0;
            if (diceCount < 8){
                for(int i = diceCount; i < 8; i++){
                    rolledDice.getDiceList().get(rolledDice.getDiceKeys().get(i)).makeInvisible();
                }
            }

            final Animation.AnimationListener animationListener = new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    for (String key :
                            rolledKeys) {
                        rolledDice.getDiceList().get(key).getDieFaceView().setBackgroundColor(Color.WHITE);
                    }
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    int value = randomDiceValue();
                    int res = 0;
                    switch (value) {
                        case 1:
                            res = R.drawable.die1;
                            rolledOne = true;
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
                        if (value == 1){
                            rolledDice.getDiceList().get("rollDie1").getDieFaceView().setBackgroundColor(Color.BLACK);
                        }
                    }else if (animation == anim2){
                        rolledDice.getDiceList().get("rollDie2").getDieFaceView().setImageResource(res);
                        rolledDice.getDiceList().get("rollDie2").setValue(value);
                        diceSum++;
                        if (value == 1){
                            rolledDice.getDiceList().get("rollDie2").getDieFaceView().setBackgroundColor(Color.BLACK);
                        }
                    }else if (animation == anim3){
                        rolledDice.getDiceList().get("rollDie3").getDieFaceView().setImageResource(res);
                        rolledDice.getDiceList().get("rollDie3").setValue(value);
                        diceSum++;
                        if (value == 1){
                            rolledDice.getDiceList().get("rollDie3").getDieFaceView().setBackgroundColor(Color.BLACK);
                        }
                    } else if (animation == anim4){
                        rolledDice.getDiceList().get("rollDie4").getDieFaceView().setImageResource(res);
                        rolledDice.getDiceList().get("rollDie4").setValue(value);
                        diceSum++;
                        if (value == 1){
                            rolledDice.getDiceList().get("rollDie4").getDieFaceView().setBackgroundColor(Color.BLACK);
                        }
                    } else if (animation == anim5){
                        rolledDice.getDiceList().get("rollDie5").getDieFaceView().setImageResource(res);
                        rolledDice.getDiceList().get("rollDie5").setValue(value);
                        diceSum++;
                        if (value == 1){
                            rolledDice.getDiceList().get("rollDie5").getDieFaceView().setBackgroundColor(Color.BLACK);
                        }
                    } else if (animation == anim6){
                        rolledDice.getDiceList().get("rollDie6").getDieFaceView().setImageResource(res);
                        rolledDice.getDiceList().get("rollDie6").setValue(value);
                        diceSum++;
                        if (value == 1){
                            rolledDice.getDiceList().get("rollDie6").getDieFaceView().setBackgroundColor(Color.BLACK);
                        }
                    } else if (animation == anim7){
                        rolledDice.getDiceList().get("rollDie7").getDieFaceView().setImageResource(res);
                        rolledDice.getDiceList().get("rollDie7").setValue(value);
                        diceSum++;
                        if (value == 1){
                            rolledDice.getDiceList().get("rollDie7").getDieFaceView().setBackgroundColor(Color.BLACK);
                        }
                    } else if (animation == anim8){
                        rolledDice.getDiceList().get("rollDie8").getDieFaceView().setImageResource(res);
                        rolledDice.getDiceList().get("rollDie8").setValue(value);
                        diceSum++;
                        if (value == 1){
                            rolledDice.getDiceList().get("rollDie8").getDieFaceView().setBackgroundColor(Color.BLACK);
                        }
                    }
                    if (diceSum == diceCount){
                        rolledDice.MakeUnclickable();
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
            anim6.setAnimationListener(animationListener);
            anim7.setAnimationListener(animationListener);
            anim8.setAnimationListener(animationListener);

            rolledDice.getDiceList().get("rollDie1").setAnimation(anim1);
            rolledDice.getDiceList().get("rollDie2").setAnimation(anim2);
            rolledDice.getDiceList().get("rollDie3").setAnimation(anim3);
            rolledDice.getDiceList().get("rollDie4").setAnimation(anim4);
            rolledDice.getDiceList().get("rollDie5").setAnimation(anim5);
            rolledDice.getDiceList().get("rollDie6").setAnimation(anim6);
            rolledDice.getDiceList().get("rollDie7").setAnimation(anim7);
            rolledDice.getDiceList().get("rollDie8").setAnimation(anim8);

            diceSum = 0;
            for (String key :
                    rolledKeys) {
                rolledDice.getDiceList().get(key).getDieFaceView().startAnimation(rolledDice.getDiceList().get(key).getAnimation());
            }
        });

        resetGame.setOnClickListener(v -> {
            if (isFullGame){
                Intent newIntent = new Intent(EventPoleVault.this, EventJavelin.class);
                newIntent.putExtra("isFullGame", isFullGame);
                newIntent.putExtra("fullGameScore", fullGameScore);
                startActivity(newIntent);
                finish();
            } else {
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
            }
        });

        leaveGame.setOnClickListener(v -> finish());
    }

    private void CheckSuccessfulJump() {
        rollsLeft--;
        rollsLeftText.setText(Integer.toString(rollsLeft));

        diceSum = rolledDice.ScoreDiceNormalSubset(diceCount);

        if (diceSum >= jumpGoal && !rolledOne){
            totalScore = jumpGoal;
            scoreText.setText(Integer.toString(totalScore));
            rollDice.setEnabled(false);
            jumpGoals.get(jumpGoalKey).setBackgroundColor(Color.GREEN);
            jumpGoals.get(jumpGoalKey).setClickable(false);
            for (int i = 0; i < jumpGoalKeys.indexOf(jumpGoalKey); i++){
                jumpGoals.get(jumpGoalKeys.get(i)).setClickable(false);
                jumpGoals.get(jumpGoalKeys.get(i)).setBackgroundColor(Color.GRAY);
            }
            for (int i = jumpGoalKeys.indexOf(jumpGoalKey + 1); i < jumpGoalKeys.size(); i++){
                jumpGoals.get(jumpGoalKeys.get(i)).setClickable(true);
                jumpGoalViews.get(i).setEnabled(true);
            }
            jumpGoalKey = "";
            rollsLeft = 3;
            rollsLeftText.setText(Integer.toString(rollsLeft));
            action = "Select a new Height";
            actionPrompt.setText(action);
        } else if (rollsLeft == 0){
            rollDice.setEnabled(true);
            resetGame.setEnabled(true);
            fullGameScore += totalScore;
            fullGameScoreText.setText(Integer.toString(fullGameScore));
            jumpGoals.get(jumpGoalKey).setBackgroundColor(Color.RED);
            scoreText.setText(Integer.toString(totalScore));
        }
        diceSum = 0;
    }

    public void setJumpGoal(View v){

        if (!jumpGoalKey.isEmpty()){
            jumpGoals.get(jumpGoalKey).setBackgroundColor(Color.WHITE);
        }

        switch (v.getId()){
            case R.id.tvPoleVaultGoal10:
                jumpGoal = 10;
                jumpGoalKey = jumpGoalKeys.get(0);
                break;
            case R.id.tvPoleVaultGoal12:
                jumpGoal = 12;
                jumpGoalKey = jumpGoalKeys.get(1);
                break;
            case R.id.tvPoleVaultGoal14:
                jumpGoal = 14;
                jumpGoalKey = jumpGoalKeys.get(2);
                break;
            case R.id.tvPoleVaultGoal16:
                jumpGoal = 16;
                jumpGoalKey = jumpGoalKeys.get(3);
                break;
            case R.id.tvPoleVaultGoal18:
                jumpGoal = 18;
                jumpGoalKey = jumpGoalKeys.get(4);
                break;
            case R.id.tvPoleVaultGoal20:
                jumpGoal = 20;
                jumpGoalKey = jumpGoalKeys.get(5);
                break;
            case R.id.tvPoleVaultGoal22:
                jumpGoal = 22;
                jumpGoalKey = jumpGoalKeys.get(6);
                break;
            case R.id.tvPoleVaultGoal24:
                jumpGoal = 24;
                jumpGoalKey = jumpGoalKeys.get(7);
                break;
            case R.id.tvPoleVaultGoal26:
                jumpGoal = 26;
                jumpGoalKey = jumpGoalKeys.get(8);
                break;
            case R.id.tvPoleVaultGoal28:
                jumpGoal = 28;
                jumpGoalKey = jumpGoalKeys.get(9);
                break;
            case R.id.tvPoleVaultGoal30:
                jumpGoal = 30;
                jumpGoalKey = jumpGoalKeys.get(10);
                break;
            case R.id.tvPoleVaultGoal32:
                jumpGoal = 32;
                jumpGoalKey = jumpGoalKeys.get(11);
                break;
            case R.id.tvPoleVaultGoal34:
                jumpGoal = 34;
                jumpGoalKey = jumpGoalKeys.get(12);
                break;
            case R.id.tvPoleVaultGoal36:
                jumpGoal = 36;
                jumpGoalKey = jumpGoalKeys.get(13);
                break;
            case R.id.tvPoleVaultGoal38:
                jumpGoal = 38;
                jumpGoalKey = jumpGoalKeys.get(14);
                break;
            case R.id.tvPoleVaultGoal40:
                jumpGoal = 40;
                jumpGoalKey = jumpGoalKeys.get(15);
                break;
            case R.id.tvPoleVaultGoal42:
                jumpGoal = 42;
                jumpGoalKey = jumpGoalKeys.get(16);
                break;
            case R.id.tvPoleVaultGoal44:
                jumpGoal = 44;
                jumpGoalKey = jumpGoalKeys.get(17);
                break;
            case R.id.tvPoleVaultGoal46:
                jumpGoal = 46;
                jumpGoalKey = jumpGoalKeys.get(18);
                break;
            case R.id.tvPoleVaultGoal48:
                jumpGoal = 48;
                jumpGoalKey = jumpGoalKeys.get(19);
                break;
        }
        rolledDice.MakeClickable();

        v.setBackgroundColor(Color.YELLOW);
        colorID = Color.YELLOW;
        action = "Pick a number of dice";
        actionPrompt.setText(action);

        rolledDice.MakeClickable();
        rolledDice.MakeVisible();
        rolledDice.MakeOnes();
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.ivPoleVaultRollDie1:
                // can't be selected, must roll at least 2 dice
                break;
            case R.id.ivPoleVaultRollDie2:
                // only the first 2 dice are rolled
                diceCount = 2;
                // hide other dice
                // change this and all dice to left to have green backgrounds
                // when 'Roll' is clicked, hide all dice to the right of this one.
                // reset all dice to 1 and show all 8 dice once a new height is picked (assuming current height is achieved)
                break;
            case R.id.ivPoleVaultRollDie3:
                diceCount = 3;
                break;
            case R.id.ivPoleVaultRollDie4:
                diceCount = 4;
                break;
            case R.id.ivPoleVaultRollDie5:
                diceCount = 5;
                break;
            case R.id.ivPoleVaultRollDie6:
                diceCount = 6;
                break;
            case R.id.ivPoleVaultRollDie7:
                diceCount = 7;
                break;
            case R.id.ivPoleVaultRollDie8:
                diceCount = 8;
                break;
        }
        for (TextView view :
                jumpGoalViews) {
            view.setEnabled(false);
        }
        rollDice.setEnabled(true);
        for (int i = 0; i < 8; i++) {
            if (i < diceCount){
                rolledDice.getDiceList().get(rolledKeys.get(i)).getDieFaceView().setBackgroundColor(Color.GREEN);
            } else {
                rolledDice.getDiceList().get(rolledKeys.get(i)).getDieFaceView().setBackgroundColor(Color.WHITE);
            }
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
}