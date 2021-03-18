package com.example.encore;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class EventLongJump extends AppCompatActivity implements View.OnClickListener {

    private static final Random RANDOM = new Random();
    private Dice rolledDice, reservedDice;
    private ArrayList<ImageView> ivRolledDice, ivBankedDiceSet;
    private ArrayList<String> rolledKeys, bankKeys, bankedKeys;
    private int reserveScore, totalScore;
    private Button rollDice, keepDice, startJump, nextAttempt, resetGame, leaveGame;
    private ImageView rollDie1, rollDie2, rollDie3, rollDie4, rollDie5, reserveDie1, reserveDie2, reserveDie3, reserveDie4, reserveDie5;

    private TextView attemptText, reserveScoreText, runUp, runUpRules, jumpRules, score1, score2, score3;
    private int attempt = 1;
    private MediaPlayer mp;
    private boolean firstRoll = true;

    public static int randomDiceValue(){
        return RANDOM.nextInt(6) + 1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_long_jump);

        rollDie1 = (ImageView) findViewById(R.id.ivLongJumpRollDie1);
        rollDie2 = (ImageView) findViewById(R.id.ivLongJumpRollDie2);
        rollDie3 = (ImageView) findViewById(R.id.ivLongJumpRollDie3);
        rollDie4 = (ImageView) findViewById(R.id.ivLongJumpRollDie4);
        rollDie5 = (ImageView) findViewById(R.id.ivLongJumpRollDie5);

        rollDie1.setOnClickListener(this);
        rollDie2.setOnClickListener(this);
        rollDie3.setOnClickListener(this);
        rollDie4.setOnClickListener(this);
        rollDie5.setOnClickListener(this);

        reserveDie1 = (ImageView) findViewById(R.id.ivLongJumpBankDie1);
        reserveDie2 = (ImageView) findViewById(R.id.ivLongJumpBankDie2);
        reserveDie3 = (ImageView) findViewById(R.id.ivLongJumpBankDie3);
        reserveDie4 = (ImageView) findViewById(R.id.ivLongJumpBankDie4);
        reserveDie5 = (ImageView) findViewById(R.id.ivLongJumpBankDie5);

        rollDice = (Button) findViewById(R.id.buttonLongJumpRoll);
        keepDice = (Button) findViewById(R.id.buttonLongJumpBank);
        keepDice.setEnabled(false);
        startJump = (Button) findViewById(R.id.buttonLongJumpStartJump);
        startJump.setEnabled(false);
        resetGame = (Button) findViewById(R.id.buttonLongJumpReset);
        resetGame.setEnabled(false);
        leaveGame = (Button) findViewById(R.id.buttonLongJumpDone);
        nextAttempt = (Button) findViewById(R.id.buttonLongJumpNextAttempt);
        nextAttempt.setEnabled(false);

        attemptText = (TextView) findViewById(R.id.tvLongJumpAttemptsLeft);
        runUp = (TextView) findViewById(R.id.tvLongJumpRunUp);
        score1 = (TextView) findViewById(R.id.tvLongJumpScore1);
        score2 = (TextView) findViewById(R.id.tvLongJumpScore2);
        score3 = (TextView) findViewById(R.id.tvLongJumpScore3);

        ivRolledDice = new ArrayList<ImageView>();
        rolledKeys = new ArrayList<String>();

        rolledKeys.add("rollDie1");
        rolledKeys.add("rollDie2");
        rolledKeys.add("rollDie3");
        rolledKeys.add("rollDie4");
        rolledKeys.add("rollDie5");

        ivRolledDice.add(findViewById(R.id.ivLongJumpRollDie1));
        ivRolledDice.add(findViewById(R.id.ivLongJumpRollDie2));
        ivRolledDice.add(findViewById(R.id.ivLongJumpRollDie3));
        ivRolledDice.add(findViewById(R.id.ivLongJumpRollDie4));
        ivRolledDice.add(findViewById(R.id.ivLongJumpRollDie5));

        ivBankedDiceSet = new ArrayList<ImageView>();
        bankKeys = new ArrayList<String>();

        bankKeys.add("bankDie1");
        bankKeys.add("bankDie2");
        bankKeys.add("bankDie3");
        bankKeys.add("bankDie4");
        bankKeys.add("bankDie5");

        ivBankedDiceSet.add(findViewById(R.id.ivLongJumpBankDie1));
        ivBankedDiceSet.add(findViewById(R.id.ivLongJumpBankDie2));
        ivBankedDiceSet.add(findViewById(R.id.ivLongJumpBankDie3));
        ivBankedDiceSet.add(findViewById(R.id.ivLongJumpBankDie4));
        ivBankedDiceSet.add(findViewById(R.id.ivLongJumpBankDie5));

        rolledDice = new Dice(ivRolledDice, rolledKeys);
        reservedDice = new Dice(ivBankedDiceSet, bankKeys);

        bankedKeys = new ArrayList<String>();

        totalScore = 0;
        reserveScore = 0;
        reserveScoreText = (TextView) findViewById(R.id.tvLongJumpReserveSum);

        rolledDice.MakeVisible();

        rollDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim1 = AnimationUtils.loadAnimation(EventLongJump.this, R.anim.shake);
                final Animation anim2 = AnimationUtils.loadAnimation(EventLongJump.this, R.anim.shake);
                final Animation anim3 = AnimationUtils.loadAnimation(EventLongJump.this, R.anim.shake);
                final Animation anim4 = AnimationUtils.loadAnimation(EventLongJump.this, R.anim.shake);
                final Animation anim5 = AnimationUtils.loadAnimation(EventLongJump.this, R.anim.shake);

                if (firstRoll) {
                    rolledDice.MakeClickable();
                    firstRoll = false;
                }

                rollDice.setEnabled(false);
                keepDice.setEnabled(true);

                if (reserveScore >= 9){
                    rollDice.setEnabled(false);
                    // what should happen if the attempt fails?
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
            }
        });

        keepDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (String key :
                        bankedKeys) {
                    rolledDice.getDiceList().get(key).getDieFaceView().setClickable(false);
                }

                keepDice.setEnabled(false);

                if (reserveScore > 8){
                    startJump.setEnabled(false);
                    nextAttempt.setEnabled(true);
                    rollDice.setEnabled(false);
                    switch (attempt){
                        case 1:
                            score1.setText(Integer.toString(0));
                            score1.setTextColor(Color.RED);
                            break;
                        case 2:
                            score2.setText(Integer.toString(0));
                            score2.setTextColor(Color.RED);
                            break;
                        case 3:
                            score3.setText(Integer.toString(0));
                            score3.setTextColor(Color.RED);
                            break;
                    }
                } else {
                    startJump.setEnabled(true);
                    rollDice.setEnabled(true);
                }
            }
        });

        nextAttempt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attempt++;
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

    public boolean rolledDieIsClicked(View view) {
        ColorDrawable viewColor = (ColorDrawable) view.getBackground();
        int colorID = viewColor.getColor();
        int colorID2 = Color.BLACK;
        if (colorID == colorID2){
            view.setBackgroundColor(Color.WHITE);
            view.setPadding(0, 0, 0, 0);
            return true;
        } else {
            view.setBackgroundColor(Color.BLACK);
            view.setPadding(10, 10, 10, 10);
            return false;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivLongJumpRollDie1:
                if (rolledDieIsClicked(v)){
                    reservedDice.getDiceList().get("bankDie1").makeInvisible();
                    bankedKeys.remove("rollDie1");
                    reserveScore -= reservedDice.getDiceList().get("bankDie1").getValue();
                } else {
                    reservedDice.getDiceList().get("bankDie1").setValue(rolledDice.getDiceList().get("rollDie1").getValue());
                    reservedDice.getDiceList().get("bankDie1").getDieFaceView().setImageDrawable(rolledDice.getDiceList().get("rollDie1").getDieFaceView().getDrawable());
                    reservedDice.getDiceList().get("bankDie1").makeVisible();
                    bankedKeys.add("rollDie1");
                    reserveScore += reservedDice.getDiceList().get("bankDie1").getValue();
                }
//                if (reserveScore > 8){
//                    reserveScoreText.setTextColor(Color.RED);
//                }
                //reserveScoreText.setText(Integer.toString(reserveScore));
                break;
            case R.id.ivLongJumpRollDie2:
                if (rolledDieIsClicked(v)){
                    reservedDice.getDiceList().get("bankDie2").makeInvisible();
                    bankedKeys.remove("rollDie2");
                    reserveScore -= reservedDice.getDiceList().get("bankDie2").getValue();
                } else {
                    reservedDice.getDiceList().get("bankDie2").setValue(rolledDice.getDiceList().get("rollDie2").getValue());
                    reservedDice.getDiceList().get("bankDie2").getDieFaceView().setImageDrawable(rolledDice.getDiceList().get("rollDie2").getDieFaceView().getDrawable());
                    reservedDice.getDiceList().get("bankDie2").makeVisible();
                    bankedKeys.add("rollDie2");
                    reserveScore += reservedDice.getDiceList().get("bankDie2").getValue();
                }
                //reserveScoreText.setText(Integer.toString(reserveScore));
                break;
            case R.id.ivLongJumpRollDie3:
                if (rolledDieIsClicked(v)){
                    reservedDice.getDiceList().get("bankDie3").makeInvisible();
                    bankedKeys.remove("rollDie3");
                    reserveScore -= reservedDice.getDiceList().get("bankDie3").getValue();
                } else {
                    reservedDice.getDiceList().get("bankDie3").setValue(rolledDice.getDiceList().get("rollDie3").getValue());
                    reservedDice.getDiceList().get("bankDie3").getDieFaceView().setImageDrawable(rolledDice.getDiceList().get("rollDie3").getDieFaceView().getDrawable());
                    reservedDice.getDiceList().get("bankDie3").makeVisible();
                    bankedKeys.add("rollDie3");
                    reserveScore += reservedDice.getDiceList().get("bankDie3").getValue();
                }
                //reserveScoreText.setText(Integer.toString(reserveScore));
                break;
            case R.id.ivLongJumpRollDie4:
                if (rolledDieIsClicked(v)){
                    reservedDice.getDiceList().get("bankDie4").makeInvisible();
                    bankedKeys.remove("rollDie4");
                    reserveScore -= reservedDice.getDiceList().get("bankDie4").getValue();
                } else {
                    reservedDice.getDiceList().get("bankDie4").setValue(rolledDice.getDiceList().get("rollDie4").getValue());
                    reservedDice.getDiceList().get("bankDie4").getDieFaceView().setImageDrawable(rolledDice.getDiceList().get("rollDie4").getDieFaceView().getDrawable());
                    reservedDice.getDiceList().get("bankDie4").makeVisible();
                    bankedKeys.add("rollDie4");
                    reserveScore += reservedDice.getDiceList().get("bankDie4").getValue();
                }
                //reserveScoreText.setText(Integer.toString(reserveScore));
                break;
            case R.id.ivLongJumpRollDie5:
                if (rolledDieIsClicked(v)){
                    reservedDice.getDiceList().get("bankDie5").makeInvisible();
                    bankedKeys.remove("rollDie5");
                    reserveScore -= reservedDice.getDiceList().get("bankDie5").getValue();
                } else {
                    reservedDice.getDiceList().get("bankDie5").setValue(rolledDice.getDiceList().get("rollDie5").getValue());
                    reservedDice.getDiceList().get("bankDie5").getDieFaceView().setImageDrawable(rolledDice.getDiceList().get("rollDie5").getDieFaceView().getDrawable());
                    reservedDice.getDiceList().get("bankDie5").makeVisible();
                    bankedKeys.add("rollDie5");
                    reserveScore += reservedDice.getDiceList().get("bankDie5").getValue();
                }
                //reserveScoreText.setText(Integer.toString(reserveScore));
                break;
        }
        if (reserveScore > 8){
            reserveScoreText.setTextColor(Color.RED);
        } else {
            reserveScoreText.setTextColor(Color.BLACK);
        }
        reserveScoreText.setText(Integer.toString(reserveScore));

    }
}