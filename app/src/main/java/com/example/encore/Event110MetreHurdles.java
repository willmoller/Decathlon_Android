package com.example.encore;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class Event110MetreHurdles extends AppCompatActivity {

    private static final Random RANDOM = new Random();
    private Dice rolledDice;
    private ArrayList<ImageView> ivRolledDice;
    private ArrayList<String> rolledKeys;
    private int totalScore;
    private Button rollDice, keepDice, resetGame, leaveGame;

    private TextView rollsLeftText, totalScoreText;
    private int rollsLeft = 6;
    private MediaPlayer mp;

    public static int randomDiceValue(){
        return RANDOM.nextInt(6) + 1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event110_metre_hurdles);

        rollDice = (Button) findViewById(R.id.button_110MHurdles_Roll);
        keepDice = (Button) findViewById(R.id.button_110MHurdles_Keep);
        keepDice.setEnabled(false);
        resetGame = (Button) findViewById(R.id.button_110MHurdles_Reset);
        resetGame.setEnabled(false);
        leaveGame = (Button) findViewById(R.id.button_110MHurdles_Done);

        rollsLeftText = (TextView) findViewById(R.id.tv110MHurdlesRollsLeft);
        totalScoreText = (TextView) findViewById(R.id.tv110MHurdlesScore);
        totalScore = 0;

        ivRolledDice = new ArrayList<ImageView>();
        rolledKeys = new ArrayList<String>();

        rolledKeys.add("rollDie1");
        rolledKeys.add("rollDie2");
        rolledKeys.add("rollDie3");
        rolledKeys.add("rollDie4");
        rolledKeys.add("rollDie5");

        ivRolledDice.add(findViewById(R.id.iv110MHurdlesRollDie1));
        ivRolledDice.add(findViewById(R.id.iv110MHurdlesRollDie2));
        ivRolledDice.add(findViewById(R.id.iv110MHurdlesRollDie3));
        ivRolledDice.add(findViewById(R.id.iv110MHurdlesRollDie4));
        ivRolledDice.add(findViewById(R.id.iv110MHurdlesRollDie5));

        rolledDice = new Dice(ivRolledDice, rolledKeys);
        rolledDice.MakeVisible();



        rollDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim1 = AnimationUtils.loadAnimation(Event110MetreHurdles.this, R.anim.shake);
                final Animation anim2 = AnimationUtils.loadAnimation(Event110MetreHurdles.this, R.anim.shake);
                final Animation anim3 = AnimationUtils.loadAnimation(Event110MetreHurdles.this, R.anim.shake);
                final Animation anim4 = AnimationUtils.loadAnimation(Event110MetreHurdles.this, R.anim.shake);
                final Animation anim5 = AnimationUtils.loadAnimation(Event110MetreHurdles.this, R.anim.shake);

                keepDice.setEnabled(true);

                rollsLeft--;

                if (rollsLeft == 0){
                    rollDice.setEnabled(false);
                }

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
                        }else if (animation == anim2){
                            rolledDice.getDiceList().get("rollDie2").getDieFaceView().setImageResource(res);
                            rolledDice.getDiceList().get("rollDie2").setValue(value);
                        }else if (animation == anim3){
                            rolledDice.getDiceList().get("rollDie3").getDieFaceView().setImageResource(res);
                            rolledDice.getDiceList().get("rollDie3").setValue(value);
                        } else if (animation == anim4){
                            rolledDice.getDiceList().get("rollDie4").getDieFaceView().setImageResource(res);
                            rolledDice.getDiceList().get("rollDie4").setValue(value);
                        } else if (animation == anim5){
                            rolledDice.getDiceList().get("rollDie5").getDieFaceView().setImageResource(res);
                            rolledDice.getDiceList().get("rollDie5").setValue(value);
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

                rolledDice.getDiceList().get("rollDie1").getDieFaceView().startAnimation(anim1);
                rolledDice.getDiceList().get("rollDie2").getDieFaceView().startAnimation(anim2);
                rolledDice.getDiceList().get("rollDie3").getDieFaceView().startAnimation(anim3);
                rolledDice.getDiceList().get("rollDie4").getDieFaceView().startAnimation(anim4);
                rolledDice.getDiceList().get("rollDie5").getDieFaceView().startAnimation(anim5);

                rollsLeftText.setText(Integer.toString(rollsLeft));
            }
        });

        keepDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (String key :
                        rolledKeys) {
                    totalScore += rolledDice.getDiceList().get(key).getValue();
                }
                totalScoreText.setText(Integer.toString(totalScore));
                rollDice.setEnabled(false);
                keepDice.setEnabled(false);
                resetGame.setEnabled(true);
                leaveGame.setEnabled(true);
            }
        });

        resetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice.setEnabled(true);
                totalScore = 0;
                totalScoreText.setText(Integer.toString(totalScore));
                rollsLeft = 7;
                rollsLeftText.setText(Integer.toString(rollsLeft));
                rolledDice.MakeOnes();
                resetGame.setEnabled(false);
            }
        });

        leaveGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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