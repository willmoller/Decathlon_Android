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

public class EventDiscus extends AppCompatActivity implements View.OnClickListener {

    private static final Random RANDOM = new Random();
    private Dice rolledDice, reservedDice;
    private ArrayList<ImageView> ivRolledDice, ivBankedDiceSet;
    private ArrayList<String> rolledKeys, bankKeys, bankedKeys;
    private int reserveScore, totalScore, diceAvailable, diceClicked;
    private Button rollDice, keepDice, scoreDice, nextAttempt, resetGame, leaveGame;
    private ImageView rollDie1, rollDie2, rollDie3, rollDie4, rollDie5, reserveDie1, reserveDie2, reserveDie3, reserveDie4, reserveDie5;
    private boolean evensAvailable;
    private TextView attemptText, reserveScoreText, discusRules, score1, score2, score3;
    private int attempt = 1;
    private MediaPlayer mp;

    public static int randomDiceValue(){
        return RANDOM.nextInt(6) + 1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_discus);

        rollDie1 = (ImageView) findViewById(R.id.ivDiscusRollDie1);
        rollDie2 = (ImageView) findViewById(R.id.ivDiscusRollDie2);
        rollDie3 = (ImageView) findViewById(R.id.ivDiscusRollDie3);
        rollDie4 = (ImageView) findViewById(R.id.ivDiscusRollDie4);
        rollDie5 = (ImageView) findViewById(R.id.ivDiscusRollDie5);

        rollDie1.setOnClickListener(this);
        rollDie2.setOnClickListener(this);
        rollDie3.setOnClickListener(this);
        rollDie4.setOnClickListener(this);
        rollDie5.setOnClickListener(this);

        reserveDie1 = (ImageView) findViewById(R.id.ivDiscusBankDie1);
        reserveDie2 = (ImageView) findViewById(R.id.ivDiscusBankDie2);
        reserveDie3 = (ImageView) findViewById(R.id.ivDiscusBankDie3);
        reserveDie4 = (ImageView) findViewById(R.id.ivDiscusBankDie4);
        reserveDie5 = (ImageView) findViewById(R.id.ivDiscusBankDie5);

        rollDice = (Button) findViewById(R.id.buttonDiscusRoll);
        keepDice = (Button) findViewById(R.id.buttonDiscusKeep);
        keepDice.setEnabled(false);
        scoreDice = (Button) findViewById(R.id.buttonDiscusScore);
        scoreDice.setEnabled(false);
        resetGame = (Button) findViewById(R.id.buttonDiscusReset);
        resetGame.setEnabled(false);
        leaveGame = (Button) findViewById(R.id.buttonDiscusDone);
        nextAttempt = (Button) findViewById(R.id.buttonDiscusNextAttempt);
        nextAttempt.setEnabled(false);

        attemptText = (TextView) findViewById(R.id.tvDiscusAttemptsLeft);
        score1 = (TextView) findViewById(R.id.tvDiscusScore1);
        score2 = (TextView) findViewById(R.id.tvDiscusScore2);
        score3 = (TextView) findViewById(R.id.tvDiscusScore3);

        ivRolledDice = new ArrayList<ImageView>();
        rolledKeys = new ArrayList<String>();

        rolledKeys.add("rollDie1");
        rolledKeys.add("rollDie2");
        rolledKeys.add("rollDie3");
        rolledKeys.add("rollDie4");
        rolledKeys.add("rollDie5");

        ivRolledDice.add(findViewById(R.id.ivDiscusRollDie1));
        ivRolledDice.add(findViewById(R.id.ivDiscusRollDie2));
        ivRolledDice.add(findViewById(R.id.ivDiscusRollDie3));
        ivRolledDice.add(findViewById(R.id.ivDiscusRollDie4));
        ivRolledDice.add(findViewById(R.id.ivDiscusRollDie5));

        ivBankedDiceSet = new ArrayList<ImageView>();
        bankKeys = new ArrayList<String>();

        bankKeys.add("rollDie1");
        bankKeys.add("rollDie2");
        bankKeys.add("rollDie3");
        bankKeys.add("rollDie4");
        bankKeys.add("rollDie5");

        ivBankedDiceSet.add(findViewById(R.id.ivDiscusBankDie1));
        ivBankedDiceSet.add(findViewById(R.id.ivDiscusBankDie2));
        ivBankedDiceSet.add(findViewById(R.id.ivDiscusBankDie3));
        ivBankedDiceSet.add(findViewById(R.id.ivDiscusBankDie4));
        ivBankedDiceSet.add(findViewById(R.id.ivDiscusBankDie5));

        rolledDice = new Dice(ivRolledDice, rolledKeys);
        reservedDice = new Dice(ivBankedDiceSet, bankKeys);

        bankedKeys = new ArrayList<String>();

        evensAvailable = false;
        totalScore = 0;
        reserveScore = 0;
        diceClicked = 0;
        diceAvailable = 5;
        reserveScoreText = (TextView) findViewById(R.id.tvDiscusReserveSum);

        rolledDice.MakeVisible();

        mp = new MediaPlayer();

        rollDice.setOnClickListener(v -> {
            try {
                mp.stop();
                mp.release();
                mp = MediaPlayer.create(this, R.raw.dice);
                mp.start();
            } catch(Exception e) { e.printStackTrace(); }

            //diceAvailable = 0;
            final Animation anim1 = AnimationUtils.loadAnimation(EventDiscus.this, R.anim.shake);
            final Animation anim2 = AnimationUtils.loadAnimation(EventDiscus.this, R.anim.shake);
            final Animation anim3 = AnimationUtils.loadAnimation(EventDiscus.this, R.anim.shake);
            final Animation anim4 = AnimationUtils.loadAnimation(EventDiscus.this, R.anim.shake);
            final Animation anim5 = AnimationUtils.loadAnimation(EventDiscus.this, R.anim.shake);

            rolledDice.MakeClickable(rolledKeys);

            rollDice.setEnabled(false);
            scoreDice.setEnabled(false);

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

                    for(int i = 0; i < diceAvailable; i++){
                        if (rolledDice.getDiceList().get(rolledKeys.get(i)).getValue() % 2 == 0){
                            rolledDice.getDiceList().get(rolledKeys.get(i)).getDieFaceView().setBackgroundColor(Color.GREEN);
                            rolledDice.getDiceList().get(rolledKeys.get(i)).MakeClickable();
                            evensAvailable = true;
                        } else {
                            rolledDice.getDiceList().get(rolledKeys.get(i)).getDieFaceView().setBackgroundColor(Color.BLACK);
                            rolledDice.getDiceList().get(rolledKeys.get(i)).MakeUnclickable();
                        }
                    }

                    if(!evensAvailable){
                        keepDice.setEnabled(false);
                        scoreDice.setEnabled(false);
                        nextAttempt.setEnabled(true);
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
                                nextAttempt.setEnabled(false);
                                resetGame.setEnabled(true);
                                break;
                        }
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
            diceClicked = 0;
            evensAvailable = false;
            if(diceAvailable > 0){
                rollDice.setEnabled(true);
            }
            keepDice.setEnabled(false);
            scoreDice.setEnabled(true);
            rolledDice.MakeUnclickable();
        });

        scoreDice.setOnClickListener(v -> {
            scoreDice.setEnabled(false);
            rollDice.setEnabled(false);
            rolledDice.ChangeBackgroundColor(Color.WHITE);
            rolledDice.SetDiceViewPadding(0);

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
                resetGame.setEnabled(true);
            }
        });

        nextAttempt.setOnClickListener(v -> {
            diceAvailable = 5;
            diceClicked = 0;
            evensAvailable = false;
            attempt++;
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
        });

        resetGame.setOnClickListener(v -> {
            resetGame.setEnabled(false);
            diceAvailable = 5;
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
        });

        leaveGame.setOnClickListener(v -> finish());
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
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
            case R.id.ivDiscusRollDie1:
                if (rolledDieIsClicked(v)){
                    diceAvailable++;
                    diceClicked--;
                    reservedDice.getDiceList().get("rollDie1").makeInvisible();
                    bankedKeys.remove("rollDie1");
                    rolledKeys.add("rollDie1");
                    reserveScore -= reservedDice.getDiceList().get("rollDie1").getValue();
                    keepDice.setEnabled(false);
                } else {
                    diceAvailable--;
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
            case R.id.ivDiscusRollDie2:
                if (rolledDieIsClicked(v)){
                    diceAvailable++;
                    diceClicked--;
                    reservedDice.getDiceList().get("rollDie2").makeInvisible();
                    bankedKeys.remove("rollDie2");
                    rolledKeys.add("rollDie2");
                    reserveScore -= reservedDice.getDiceList().get("rollDie2").getValue();
                } else {
                    diceAvailable--;
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
            case R.id.ivDiscusRollDie3:
                if (rolledDieIsClicked(v)){
                    diceAvailable++;
                    diceClicked--;
                    reservedDice.getDiceList().get("rollDie3").makeInvisible();
                    bankedKeys.remove("rollDie3");
                    rolledKeys.add("rollDie3");
                    reserveScore -= reservedDice.getDiceList().get("rollDie3").getValue();
                } else {
                    diceAvailable--;
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
            case R.id.ivDiscusRollDie4:
                if (rolledDieIsClicked(v)){
                    diceAvailable++;
                    diceClicked--;
                    reservedDice.getDiceList().get("rollDie4").makeInvisible();
                    bankedKeys.remove("rollDie4");
                    rolledKeys.add("rollDie4");
                    reserveScore -= reservedDice.getDiceList().get("rollDie4").getValue();
                } else {
                    diceAvailable--;
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
            case R.id.ivDiscusRollDie5:
                if (rolledDieIsClicked(v)){
                    diceAvailable++;
                    diceClicked--;
                    reservedDice.getDiceList().get("rollDie5").makeInvisible();
                    bankedKeys.remove("rollDie5");
                    rolledKeys.add("rollDie5");
                    reserveScore -= reservedDice.getDiceList().get("rollDie5").getValue();
                } else {
                    diceAvailable--;
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
        reserveScoreText.setText(Integer.toString(reserveScore));
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