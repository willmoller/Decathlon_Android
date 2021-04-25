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
import java.util.Random;

public class EventLongJump extends AppCompatActivity implements View.OnClickListener {

    private static final Random RANDOM = new Random();
    private Dice rolledDice, reservedDice;
    private ArrayList<ImageView> ivRolledDice, ivBankedDiceSet;
    private ArrayList<String> rolledKeys, bankKeys, bankedKeys;
    private int reserveScore, totalScore, diceClicked;
    private Button rollDice, keepDice, startJump, nextAttempt, resetGame, leaveGame;
    private ImageView rollDie1, rollDie2, rollDie3, rollDie4, rollDie5, reserveDie1, reserveDie2, reserveDie3, reserveDie4, reserveDie5;

    private TextView attemptText, reserveScoreText, runUp, runUpRules, jumpRules, score1, score2, score3;
    private int attempt = 1;
    private MediaPlayer mp;
    private boolean isJumpAttempt = false;

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

        bankKeys.add("rollDie1");
        bankKeys.add("rollDie2");
        bankKeys.add("rollDie3");
        bankKeys.add("rollDie4");
        bankKeys.add("rollDie5");

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
        diceClicked = 0;
        reserveScoreText = (TextView) findViewById(R.id.tvLongJumpReserveSum);

        rolledDice.MakeVisible();

        mp = new MediaPlayer();

        rollDice.setOnClickListener(v -> {
            try {
                mp.stop();
                mp.release();
                mp = MediaPlayer.create(this, R.raw.dice);
                mp.start();
            } catch(Exception e) { e.printStackTrace(); }

            diceClicked = 0;
            final Animation anim1 = AnimationUtils.loadAnimation(EventLongJump.this, R.anim.shake);
            final Animation anim2 = AnimationUtils.loadAnimation(EventLongJump.this, R.anim.shake);
            final Animation anim3 = AnimationUtils.loadAnimation(EventLongJump.this, R.anim.shake);
            final Animation anim4 = AnimationUtils.loadAnimation(EventLongJump.this, R.anim.shake);
            final Animation anim5 = AnimationUtils.loadAnimation(EventLongJump.this, R.anim.shake);

            rolledDice.MakeClickable(rolledKeys);

            rollDice.setEnabled(false);
            startJump.setEnabled(false);

            if (isJumpAttempt){




            } else {
                if (reserveScore >= 9){
                    rollDice.setEnabled(false);
                    // what should happen if the attempt fails?
                }
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

            rolledDice.getDiceList().get("rollDie1").setAnimation(anim1);
            rolledDice.getDiceList().get("rollDie2").setAnimation(anim2);
            rolledDice.getDiceList().get("rollDie3").setAnimation(anim3);
            rolledDice.getDiceList().get("rollDie4").setAnimation(anim4);
            rolledDice.getDiceList().get("rollDie5").setAnimation(anim5);

            for (String key :
                    rolledKeys) {
                rolledDice.getDiceList().get(key).getDieFaceView().startAnimation(rolledDice.getDiceList().get(key).getAnimation());
            }
        });

        keepDice.setOnClickListener(v -> {

            for (String key :
                    bankedKeys) {
                rolledDice.getDiceList().get(key).getDieFaceView().setClickable(false);
                rolledDice.getDiceList().get(key).makeInvisible();
            }

            keepDice.setEnabled(false);

            rolledDice.MakeUnclickable();

            if(!isJumpAttempt){
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
                } else if (diceClicked == 5){
                    rollDice.setEnabled(false);
                    startJump.setEnabled(true);
                } else {
                    startJump.setEnabled(true);
                    rollDice.setEnabled(true);
                }
            } else if (isJumpAttempt && rolledKeys.size() > 0){
                rollDice.setEnabled(true);
            } else {
                startJump.setEnabled(true);
            }
        });

        startJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diceClicked = 0;
                startJump.setEnabled(false);
                rolledDice.ChangeBackgroundColor(Color.WHITE);
                rolledDice.SetDiceViewPadding(0);

                if (isJumpAttempt){
                    isJumpAttempt = false;
                    if (attempt == 3){
                        resetGame.setEnabled(true);
                    }
                    runUp.setText(R.string.long_jump_jump_text);
                    startJump.setText(R.string.long_jump_start_jump_text);

                    for (String key :
                            bankKeys) {
                        rolledKeys.add(key);
                    }
                    
                    switch (attempt){
                        case 1:
                            score1.setText(Integer.toString(reserveScore));
                            score1.setTextColor(Color.GREEN);
                            break;
                        case 2:
                            score2.setText(Integer.toString(reserveScore));
                            score2.setTextColor(Color.GREEN);
                            break;
                        case 3:
                            score3.setText(Integer.toString(reserveScore));
                            score3.setTextColor(Color.GREEN);
                            break;
                    }
                    if (attempt < 3){
                        nextAttempt.setEnabled(true);
                    } else {
                        finishEvent();
                    }
                } else {
                    reserveScore = 0;
                    reserveScoreText.setText(Integer.toString(reserveScore));
                    isJumpAttempt = true;
                    runUp.setText(R.string.long_jump_jump_text);
                    startJump.setText(R.string.long_jump_score_jump_text);
                    reservedDice.MakeHidden();

                    for (String key :
                            bankedKeys) {
                        rolledDice.getDiceList().get(key).setValue(reservedDice.getDiceList().get(key).getValue());
                        rolledDice.getDiceList().get(key).getDieFaceView().setImageDrawable(reservedDice.getDiceList().get(key).getDieFaceView().getDrawable());
                        rolledDice.getDiceList().get(key).makeVisible();
                    }

                    for (String key :
                            rolledKeys) {
                        rolledDice.getDiceList().get(key).makeInvisible();
                        rolledDice.getDiceList().get(key).MakeUnclickable();
                    }

                    rolledKeys.removeAll(rolledKeys);

                    for (String key :
                            bankedKeys) {
                        rolledKeys.add(key);
                    }
                    bankedKeys.removeAll(bankedKeys);
                }

                //runUp.setText();
            }

            private void finishEvent() {

            }
        });

        nextAttempt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diceClicked = 0;
                attempt++;
                attemptText.setText(Integer.toString(attempt));

                reserveScore = 0;
                reserveScoreText.setText(Integer.toString(reserveScore));

                rolledKeys.removeAll(rolledKeys);
                for (String key :
                        bankKeys) {
                    rolledKeys.add(key);
                }

                //Collections.sort(rolledKeys);
                bankedKeys.removeAll(bankedKeys);

                rolledDice.MakeVisible();
                rolledDice.ChangeBackgroundColor(Color.WHITE);
                rolledDice.SetDiceViewPadding(0);
                rolledDice.MakeOnes();
                reservedDice.MakeHidden();

                rollDice.setEnabled(true);
                nextAttempt.setEnabled(false);
                reserveScoreText.setTextColor(Color.BLACK);
            }
        });

        resetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame.setEnabled(false);
                diceClicked = 0;
                attempt = 1;
                attemptText.setText(Integer.toString(attempt));

                reserveScore = 0;
                reserveScoreText.setText(Integer.toString(reserveScore));

                rolledKeys.removeAll(rolledKeys);
                for (String key :
                        bankKeys) {
                    rolledKeys.add(key);
                }

                bankedKeys.removeAll(bankedKeys);

                rolledDice.MakeVisible();
                rolledDice.ChangeBackgroundColor(Color.WHITE);
                rolledDice.SetDiceViewPadding(0);
                rolledDice.MakeOnes();
                reservedDice.MakeHidden();

                rollDice.setEnabled(true);
                nextAttempt.setEnabled(false);
                reserveScoreText.setTextColor(Color.BLACK);
                score1.setText(R.string.score_text_empty);
                score2.setText(R.string.score_text_empty);
                score3.setText(R.string.score_text_empty);
                score1.setTextColor(Color.BLACK);
                score2.setTextColor(Color.BLACK);
                score3.setTextColor(Color.BLACK);
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
                    diceClicked--;
                    reservedDice.getDiceList().get("rollDie1").makeInvisible();
                    bankedKeys.remove("rollDie1");
                    rolledKeys.add("rollDie1");
                    reserveScore -= reservedDice.getDiceList().get("rollDie1").getValue();
                    keepDice.setEnabled(false);
                } else {
                    diceClicked++;
                    reservedDice.getDiceList().get("rollDie1").setValue(rolledDice.getDiceList().get("rollDie1").getValue());
                    reservedDice.getDiceList().get("rollDie1").getDieFaceView().setImageDrawable(rolledDice.getDiceList().get("rollDie1").getDieFaceView().getDrawable());
                    reservedDice.getDiceList().get("rollDie1").makeVisible();
                    bankedKeys.add("rollDie1");
                    rolledKeys.remove("rollDie1");
                    reserveScore += reservedDice.getDiceList().get("rollDie1").getValue();
                    keepDice.setEnabled(true);
                }
                if (diceClicked > 0){
                    keepDice.setEnabled(true);
                } else {
                    keepDice.setEnabled(false);
                }
                break;
            case R.id.ivLongJumpRollDie2:
                if (rolledDieIsClicked(v)){
                    diceClicked--;
                    reservedDice.getDiceList().get("rollDie2").makeInvisible();
                    bankedKeys.remove("rollDie2");
                    rolledKeys.add("rollDie2");
                    reserveScore -= reservedDice.getDiceList().get("rollDie2").getValue();
                } else {
                    diceClicked++;
                    reservedDice.getDiceList().get("rollDie2").setValue(rolledDice.getDiceList().get("rollDie2").getValue());
                    reservedDice.getDiceList().get("rollDie2").getDieFaceView().setImageDrawable(rolledDice.getDiceList().get("rollDie2").getDieFaceView().getDrawable());
                    reservedDice.getDiceList().get("rollDie2").makeVisible();
                    bankedKeys.add("rollDie2");
                    rolledKeys.remove("rollDie2");
                    reserveScore += reservedDice.getDiceList().get("rollDie2").getValue();
                }
                if (diceClicked > 0){
                    keepDice.setEnabled(true);
                } else {
                    keepDice.setEnabled(false);
                }
                break;
            case R.id.ivLongJumpRollDie3:
                if (rolledDieIsClicked(v)){
                    diceClicked--;
                    reservedDice.getDiceList().get("rollDie3").makeInvisible();
                    bankedKeys.remove("rollDie3");
                    rolledKeys.add("rollDie3");
                    reserveScore -= reservedDice.getDiceList().get("rollDie3").getValue();
                } else {
                    diceClicked++;
                    reservedDice.getDiceList().get("rollDie3").setValue(rolledDice.getDiceList().get("rollDie3").getValue());
                    reservedDice.getDiceList().get("rollDie3").getDieFaceView().setImageDrawable(rolledDice.getDiceList().get("rollDie3").getDieFaceView().getDrawable());
                    reservedDice.getDiceList().get("rollDie3").makeVisible();
                    bankedKeys.add("rollDie3");
                    rolledKeys.remove("rollDie3");
                    reserveScore += reservedDice.getDiceList().get("rollDie3").getValue();
                }
                if (diceClicked > 0){
                    keepDice.setEnabled(true);
                } else {
                    keepDice.setEnabled(false);
                }
                break;
            case R.id.ivLongJumpRollDie4:
                if (rolledDieIsClicked(v)){
                    diceClicked--;
                    reservedDice.getDiceList().get("rollDie4").makeInvisible();
                    bankedKeys.remove("rollDie4");
                    rolledKeys.add("rollDie4");
                    reserveScore -= reservedDice.getDiceList().get("rollDie4").getValue();
                } else {
                    diceClicked++;
                    reservedDice.getDiceList().get("rollDie4").setValue(rolledDice.getDiceList().get("rollDie4").getValue());
                    reservedDice.getDiceList().get("rollDie4").getDieFaceView().setImageDrawable(rolledDice.getDiceList().get("rollDie4").getDieFaceView().getDrawable());
                    reservedDice.getDiceList().get("rollDie4").makeVisible();
                    bankedKeys.add("rollDie4");
                    rolledKeys.remove("rollDie4");
                    reserveScore += reservedDice.getDiceList().get("rollDie4").getValue();
                }
                if (diceClicked > 0){
                    keepDice.setEnabled(true);
                } else {
                    keepDice.setEnabled(false);
                }
                break;
            case R.id.ivLongJumpRollDie5:
                if (rolledDieIsClicked(v)){
                    diceClicked--;
                    reservedDice.getDiceList().get("rollDie5").makeInvisible();
                    bankedKeys.remove("rollDie5");
                    rolledKeys.add("rollDie5");
                    reserveScore -= reservedDice.getDiceList().get("rollDie5").getValue();
                } else {
                    diceClicked++;
                    reservedDice.getDiceList().get("rollDie5").setValue(rolledDice.getDiceList().get("rollDie5").getValue());
                    reservedDice.getDiceList().get("rollDie5").getDieFaceView().setImageDrawable(rolledDice.getDiceList().get("rollDie5").getDieFaceView().getDrawable());
                    reservedDice.getDiceList().get("rollDie5").makeVisible();
                    bankedKeys.add("rollDie5");
                    rolledKeys.remove("rollDie5");
                    reserveScore += reservedDice.getDiceList().get("rollDie5").getValue();
                }
                if (diceClicked > 0){
                    keepDice.setEnabled(true);
                } else {
                    keepDice.setEnabled(false);
                }
                break;
        }
        if (!isJumpAttempt && reserveScore > 8){
            reserveScoreText.setTextColor(Color.RED);
        } else {
            reserveScoreText.setTextColor(Color.BLACK);
        }
        reserveScoreText.setText(Integer.toString(reserveScore));
    }
}