package com.example.encore;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class Event100Metres extends AppCompatActivity {

    public static final Random RANDOM = new Random();
    private Die rollDie1, rollDie2, rollDie3, rollDie4;
    private Die bankDie1, bankDie2, bankDie3, bankDie4, bankDie5, bankDie6, bankDie7, bankDie8;
    int bankedDice, totalScore;
    Button rollDice, keepDice;

    private TextView rollsLeftText, totalScoreText;
    private int rollsLeft = 7;

    public static int randomDiceValue(){
        return RANDOM.nextInt(6) + 1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event100_metres);

        rollDice = (Button) findViewById(R.id.button_100M_roll);
        keepDice = (Button) findViewById(R.id.button_100M_keep);
        rollsLeftText = (TextView) findViewById(R.id.tv100MRollsRemain);
        totalScoreText = (TextView) findViewById(R.id.tv100MScore);
        bankedDice = 0;
        totalScore = 0;



        rollDie1 = new Die(findViewById(R.id.iv100MrollDie1));
        rollDie1.makeVisible();
        rollDie2 = new Die(findViewById(R.id.iv100MrollDie2));
        rollDie2.makeVisible();
        rollDie3 = new Die(findViewById(R.id.iv100MrollDie3));
        rollDie3.makeVisible();
        rollDie4 = new Die(findViewById(R.id.iv100MrollDie4));
        rollDie4.makeVisible();
        bankDie1 = new Die(findViewById(R.id.iv100MbankDie1));
        bankDie2 = new Die(findViewById(R.id.iv100MbankDie2));
        bankDie3 = new Die(findViewById(R.id.iv100MbankDie3));
        bankDie4 = new Die(findViewById(R.id.iv100MbankDie4));
        bankDie5 = new Die(findViewById(R.id.iv100MbankDie5));
        bankDie6 = new Die(findViewById(R.id.iv100MbankDie6));
        bankDie7 = new Die(findViewById(R.id.iv100MbankDie7));
        bankDie8 = new Die(findViewById(R.id.iv100MbankDie8));

        rollDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim1 = AnimationUtils.loadAnimation(Event100Metres.this, R.anim.shake);
                final Animation anim2 = AnimationUtils.loadAnimation(Event100Metres.this, R.anim.shake);
                final Animation anim3 = AnimationUtils.loadAnimation(Event100Metres.this, R.anim.shake);
                final Animation anim4 = AnimationUtils.loadAnimation(Event100Metres.this, R.anim.shake);


                keepDice.setEnabled(true);

                rollsLeft--;

                if (rollsLeft == 1 && bankedDice == 0){
                    rollDice.setEnabled(false);
                } else if (rollsLeft == 0 && bankedDice == 4){
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
                            rollDie1.getDieFaceView().setImageResource(res);
                            rollDie1.setValue(value);
                        }else if (animation == anim2){
                            rollDie2.getDieFaceView().setImageResource(res);
                            rollDie2.setValue(value);
                        }else if (animation == anim3){
                            rollDie3.getDieFaceView().setImageResource(res);
                            rollDie3.setValue(value);
                        } else if (animation == anim4){
                            rollDie4.getDieFaceView().setImageResource(res);
                            rollDie4.setValue(value);
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

                rollDie1.getDieFaceView().startAnimation(anim1);
                rollDie2.getDieFaceView().startAnimation(anim2);
                rollDie3.getDieFaceView().startAnimation(anim3);
                rollDie4.getDieFaceView().startAnimation(anim4);


                rollsLeftText.setText(Integer.toString(rollsLeft));
            }
        });

        keepDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bankRolledDice();
                // make Roll and Keep buttons 'greyed out'
                keepDice.setEnabled(false);

                if (bankedDice == 8){
                    rollDice.setEnabled(false);
                    keepDice.setEnabled(false);
                } else if (rollsLeft > 0){
                    rollDice.setEnabled(true);
                }
            }
        });
    }

    private void bankRolledDice(){
        // move rollDie's to bankDie's
        if (bankedDice == 0){
            bankDie1.getDieFaceView().setImageDrawable(rollDie1.getDieFaceView().getDrawable());
            bankDie1.makeVisible();
            scoreDie(rollDie1);
            bankDie2.getDieFaceView().setImageDrawable(rollDie2.getDieFaceView().getDrawable());
            bankDie2.makeVisible();
            scoreDie(rollDie2);
            bankDie3.getDieFaceView().setImageDrawable(rollDie3.getDieFaceView().getDrawable());
            bankDie3.makeVisible();
            scoreDie(rollDie3);
            bankDie4.getDieFaceView().setImageDrawable(rollDie4.getDieFaceView().getDrawable());
            bankDie4.makeVisible();
            scoreDie(rollDie4);
        } else {
            bankDie5.getDieFaceView().setImageDrawable(rollDie1.getDieFaceView().getDrawable());
            bankDie5.makeVisible();
            scoreDie(rollDie1);
            bankDie6.getDieFaceView().setImageDrawable(rollDie2.getDieFaceView().getDrawable());
            bankDie6.makeVisible();
            scoreDie(rollDie2);
            bankDie7.getDieFaceView().setImageDrawable(rollDie3.getDieFaceView().getDrawable());
            bankDie7.makeVisible();
            scoreDie(rollDie3);
            bankDie8.getDieFaceView().setImageDrawable(rollDie4.getDieFaceView().getDrawable());
            bankDie8.makeVisible();
            scoreDie(rollDie4);
        }

        // add bankDie's to score
        totalScoreText.setText(Integer.toString(totalScore));

        // make rollDie's = 1
        rollDie1.getDieFaceView().setImageResource(R.drawable.die1);
        rollDie1.setValue(0);
        rollDie2.getDieFaceView().setImageResource(R.drawable.die1);
        rollDie2.setValue(0);
        rollDie3.getDieFaceView().setImageResource(R.drawable.die1);
        rollDie3.setValue(0);
        rollDie4.getDieFaceView().setImageResource(R.drawable.die1);
        rollDie4.setValue(0);


        bankedDice += 4;
    }

    private void scoreDie(Die die){

        switch (die.getValue()){
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