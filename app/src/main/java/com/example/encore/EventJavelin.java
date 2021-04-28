package com.example.encore;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class EventJavelin extends AppCompatActivity implements View.OnClickListener {

    private static final Random RANDOM = new Random();
    private Dice rolledDice, reservedDice;
    private ArrayList<ImageView> ivRolledDice, ivBankedDiceSet;
    private ArrayList<String> rolledKeys, bankKeys, bankedKeys;
    private int reserveScore, totalScore, diceAvailable, diceClicked, fullGameScore, intScore1, intScore2, intScore3;
    private Button rollDice, keepDice, scoreDice, nextAttempt, resetGame, leaveGame;
    private ImageView rollDie1, rollDie2, rollDie3, rollDie4, rollDie5, rollDie6, reserveDie1,
            reserveDie2, reserveDie3, reserveDie4, reserveDie5, reserveDie6;
    private boolean oddsAvailable, isFullGame;
    private TextView attemptText, reserveScoreText, discusRules, score1, score2, score3, fullGameScoreLabel, fullGameScoreText;
    private int attempt = 1;
    private MediaPlayer mp;

    public static int randomDiceValue(){
        return RANDOM.nextInt(6) + 1;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_javelin);

        rollDie1 = (ImageView) findViewById(R.id.ivJavelinRollDie1);
        rollDie2 = (ImageView) findViewById(R.id.ivJavelinRollDie2);
        rollDie3 = (ImageView) findViewById(R.id.ivJavelinRollDie3);
        rollDie4 = (ImageView) findViewById(R.id.ivJavelinRollDie4);
        rollDie5 = (ImageView) findViewById(R.id.ivJavelinRollDie5);
        rollDie6 = (ImageView) findViewById(R.id.ivJavelinRollDie6);

        rollDie1.setOnClickListener(this);
        rollDie2.setOnClickListener(this);
        rollDie3.setOnClickListener(this);
        rollDie4.setOnClickListener(this);
        rollDie5.setOnClickListener(this);
        rollDie6.setOnClickListener(this);

        reserveDie1 = (ImageView) findViewById(R.id.ivJavelinBankDie1);
        reserveDie2 = (ImageView) findViewById(R.id.ivJavelinBankDie2);
        reserveDie3 = (ImageView) findViewById(R.id.ivJavelinBankDie3);
        reserveDie4 = (ImageView) findViewById(R.id.ivJavelinBankDie4);
        reserveDie5 = (ImageView) findViewById(R.id.ivJavelinBankDie5);
        reserveDie6 = (ImageView) findViewById(R.id.ivJavelinBankDie6);

        rollDice = (Button) findViewById(R.id.buttonJavelinRoll);
        keepDice = (Button) findViewById(R.id.buttonJavelinKeep);
        keepDice.setEnabled(false);
        scoreDice = (Button) findViewById(R.id.buttonJavelinScore);
        scoreDice.setEnabled(false);
        resetGame = (Button) findViewById(R.id.buttonJavelinReset);
        resetGame.setEnabled(false);
        leaveGame = (Button) findViewById(R.id.buttonJavelinDone);
        nextAttempt = (Button) findViewById(R.id.buttonJavelinNextAttempt);
        nextAttempt.setEnabled(false);

        attemptText = (TextView) findViewById(R.id.tvJavelinAttempt);
        score1 = (TextView) findViewById(R.id.tvJavelinScore1);
        score2 = (TextView) findViewById(R.id.tvJavelinScore2);
        score3 = (TextView) findViewById(R.id.tvJavelinScore3);

        ivRolledDice = new ArrayList<ImageView>();
        rolledKeys = new ArrayList<String>();

        rolledKeys.add("rollDie1");
        rolledKeys.add("rollDie2");
        rolledKeys.add("rollDie3");
        rolledKeys.add("rollDie4");
        rolledKeys.add("rollDie5");
        rolledKeys.add("rollDie6");

        ivRolledDice.add(findViewById(R.id.ivJavelinRollDie1));
        ivRolledDice.add(findViewById(R.id.ivJavelinRollDie2));
        ivRolledDice.add(findViewById(R.id.ivJavelinRollDie3));
        ivRolledDice.add(findViewById(R.id.ivJavelinRollDie4));
        ivRolledDice.add(findViewById(R.id.ivJavelinRollDie5));
        ivRolledDice.add(findViewById(R.id.ivJavelinRollDie6));

        ivBankedDiceSet = new ArrayList<ImageView>();
        bankKeys = new ArrayList<String>();

        bankKeys.add("rollDie1");
        bankKeys.add("rollDie2");
        bankKeys.add("rollDie3");
        bankKeys.add("rollDie4");
        bankKeys.add("rollDie5");
        bankKeys.add("rollDie6");

        ivBankedDiceSet.add(findViewById(R.id.ivJavelinBankDie1));
        ivBankedDiceSet.add(findViewById(R.id.ivJavelinBankDie2));
        ivBankedDiceSet.add(findViewById(R.id.ivJavelinBankDie3));
        ivBankedDiceSet.add(findViewById(R.id.ivJavelinBankDie4));
        ivBankedDiceSet.add(findViewById(R.id.ivJavelinBankDie5));
        ivBankedDiceSet.add(findViewById(R.id.ivJavelinBankDie6));

        rolledDice = new Dice(ivRolledDice, rolledKeys);
        reservedDice = new Dice(ivBankedDiceSet, bankKeys);

        bankedKeys = new ArrayList<String>();

        oddsAvailable = false;
        totalScore = 0;
        reserveScore = 0;
        diceClicked = 0;
        diceAvailable = 6;
        reserveScoreText = (TextView) findViewById(R.id.tvJavelinReserveSum);

        rolledDice.MakeVisible();

        mp = new MediaPlayer();

        Intent intent = getIntent();
        isFullGame = intent.getBooleanExtra("isFullGame", false);
        fullGameScoreLabel = (TextView) findViewById(R.id.tvJavelinTotalScoreLabel);
        fullGameScoreText = (TextView) findViewById(R.id.tvJavelinTotalScore);

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

            final Animation anim1 = AnimationUtils.loadAnimation(EventJavelin.this, R.anim.shake);
            final Animation anim2 = AnimationUtils.loadAnimation(EventJavelin.this, R.anim.shake);
            final Animation anim3 = AnimationUtils.loadAnimation(EventJavelin.this, R.anim.shake);
            final Animation anim4 = AnimationUtils.loadAnimation(EventJavelin.this, R.anim.shake);
            final Animation anim5 = AnimationUtils.loadAnimation(EventJavelin.this, R.anim.shake);
            final Animation anim6 = AnimationUtils.loadAnimation(EventJavelin.this, R.anim.shake);

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
                    } else if (animation == anim6){
                        rolledDice.getDiceList().get("rollDie6").getDieFaceView().setImageResource(res);
                        rolledDice.getDiceList().get("rollDie6").setValue(value);
                    }

                    for(int i = 0; i < diceAvailable; i++){
                        if (rolledDice.getDiceList().get(rolledKeys.get(i)).getValue() % 2 == 0){
                            rolledDice.getDiceList().get(rolledKeys.get(i)).getDieFaceView().setBackgroundColor(Color.BLACK);
                            rolledDice.getDiceList().get(rolledKeys.get(i)).MakeUnclickable();
                        } else {
                            rolledDice.getDiceList().get(rolledKeys.get(i)).getDieFaceView().setBackgroundColor(Color.GREEN);
                            rolledDice.getDiceList().get(rolledKeys.get(i)).MakeClickable();
                            oddsAvailable = true;
                        }
                    }

                    if(!oddsAvailable){
                        keepDice.setEnabled(false);
                        scoreDice.setEnabled(false);
                        nextAttempt.setEnabled(true);
                        switch (attempt){
                            case 1:
                                score1.setText(Integer.toString(0));
                                score1.setTextColor(Color.RED);
                                intScore1 = 0;
                                break;
                            case 2:
                                score2.setText(Integer.toString(0));
                                score2.setTextColor(Color.RED);
                                intScore2 = 0;
                                break;
                            case 3:
                                score3.setText(Integer.toString(0));
                                score3.setTextColor(Color.RED);
                                intScore3 = 0;
                                nextAttempt.setEnabled(false);
                                resetGame.setEnabled(true);
                                int maxValue = Integer.max(intScore1, intScore2);
                                maxValue = Integer.max(maxValue, intScore3);
                                fullGameScore += maxValue;
                                fullGameScoreText.setText(Integer.toString(fullGameScore));
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
            anim6.setAnimationListener(animationListener);

            rolledDice.getDiceList().get("rollDie1").setAnimation(anim1);
            rolledDice.getDiceList().get("rollDie2").setAnimation(anim2);
            rolledDice.getDiceList().get("rollDie3").setAnimation(anim3);
            rolledDice.getDiceList().get("rollDie4").setAnimation(anim4);
            rolledDice.getDiceList().get("rollDie5").setAnimation(anim5);
            rolledDice.getDiceList().get("rollDie6").setAnimation(anim6);

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
            oddsAvailable = false;
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
                    intScore1 = reserveScore;
                    break;
                case 2:
                    score2.setText(Integer.toString(reserveScore));
                    score2.setTextColor(Color.GREEN);
                    intScore2 = reserveScore;
                    break;
                case 3:
                    score3.setText(Integer.toString(reserveScore));
                    score3.setTextColor(Color.GREEN);
                    intScore3 = reserveScore;
                    break;
            }
            if (attempt < 3){
                nextAttempt.setEnabled(true);
            } else {
                resetGame.setEnabled(true);
                int maxValue = Integer.max(intScore1, intScore2);
                maxValue = Integer.max(maxValue, intScore3);
                fullGameScore += maxValue;
                fullGameScoreText.setText(Integer.toString(fullGameScore));
            }
        });

        nextAttempt.setOnClickListener(v -> {
            diceAvailable = 6;
            diceClicked = 0;
            oddsAvailable = false;
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
            if (isFullGame){
                Intent newIntent = new Intent(EventJavelin.this, Event1500Metres.class);
                newIntent.putExtra("isFullGame", isFullGame);
                newIntent.putExtra("fullGameScore", fullGameScore);
                startActivity(newIntent);
                finish();
            } else {
                resetGame.setEnabled(false);
                oddsAvailable = false;
                diceAvailable = 6;
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
            case R.id.ivJavelinRollDie1:
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
            case R.id.ivJavelinRollDie2:
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
            case R.id.ivJavelinRollDie3:
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
            case R.id.ivJavelinRollDie4:
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
            case R.id.ivJavelinRollDie5:
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
            case R.id.ivJavelinRollDie6:
                if (rolledDieIsClicked(v)){
                    diceAvailable++;
                    diceClicked--;
                    reservedDice.getDiceList().get("rollDie6").makeInvisible();
                    bankedKeys.remove("rollDie6");
                    rolledKeys.add("rollDie6");
                    reserveScore -= reservedDice.getDiceList().get("rollDie6").getValue();
                } else {
                    diceAvailable--;
                    diceClicked++;
                    reservedDice.getDiceList().get("rollDie6").setValue(rolledDice.getDiceList().get("rollDie6").getValue());
                    reservedDice.getDiceList().get("rollDie6").getDieFaceView().setImageDrawable(rolledDice.getDiceList().get("rollDie6").getDieFaceView().getDrawable());
                    reservedDice.getDiceList().get("rollDie6").makeVisible();
                    bankedKeys.add("rollDie6");
                    rolledKeys.remove("rollDie6");
                    reserveScore += reservedDice.getDiceList().get("rollDie6").getValue();
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