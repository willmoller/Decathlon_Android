package com.example.encore;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class EventShotPut extends AppCompatActivity {

    private static final Random RANDOM = new Random();
    private Dice rolledDice;
    private ArrayList<ImageView> ivRolledDice;
    private ArrayList<String> rolledKeys;
    private int totalScore, attempt, dieCount, fullGameScore, intScore1, intScore2, intScore3;
    private Button rollDice, scoreDice, resetGame, leaveGame, nextAttempt;

    private TextView attemptsLeftText, score1Text, score2Text, score3Text, fullGameScoreLabel, fullGameScoreText;
    private boolean isFullGame;
    private MediaPlayer mp;

    public static int randomDiceValue(){
        return RANDOM.nextInt(6) + 1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_shot_put);

        rollDice = (Button) findViewById(R.id.button_ShotPut_Roll);
        scoreDice = (Button) findViewById(R.id.button_ShotPut_Score);
        scoreDice.setEnabled(false);
        resetGame = (Button) findViewById(R.id.button_ShotPut_Reset);
        resetGame.setEnabled(false);
        leaveGame = (Button) findViewById(R.id.button_ShotPut_Done);
        nextAttempt = (Button) findViewById(R.id.button_ShotPut_NextAttempt);
        nextAttempt.setEnabled(false);

        attemptsLeftText = (TextView) findViewById(R.id.tvShotPutAttempt);
        score1Text = (TextView) findViewById(R.id.tvShotPutScore1);
        score2Text = (TextView) findViewById(R.id.tvShotPutScore2);
        score3Text = (TextView) findViewById(R.id.tvShotPutScore3);

        totalScore = 0;
        attempt = 1;
        dieCount = 0;

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

        ivRolledDice.add(findViewById(R.id.ivShotPutRollDie1));
        ivRolledDice.add(findViewById(R.id.ivShotPutRollDie2));
        ivRolledDice.add(findViewById(R.id.ivShotPutRollDie3));
        ivRolledDice.add(findViewById(R.id.ivShotPutRollDie4));
        ivRolledDice.add(findViewById(R.id.ivShotPutRollDie5));
        ivRolledDice.add(findViewById(R.id.ivShotPutRollDie6));
        ivRolledDice.add(findViewById(R.id.ivShotPutRollDie7));
        ivRolledDice.add(findViewById(R.id.ivShotPutRollDie8));

        rolledDice = new Dice(ivRolledDice, rolledKeys);
        rolledDice.getDiceList().get("rollDie1").makeVisible();

        mp = new MediaPlayer();

        Intent intent = getIntent();
        isFullGame = intent.getBooleanExtra("isFullGame", false);
        fullGameScoreLabel = (TextView) findViewById(R.id.tvShotPutTotalScoreLabel);
        fullGameScoreText = (TextView) findViewById(R.id.tvShotPutTotalScore);

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

            final Animation anim1 = AnimationUtils.loadAnimation(EventShotPut.this, R.anim.shake);

            scoreDice.setEnabled(true);
            dieCount++;

            if (dieCount == 8){
                rollDice.setEnabled(false);
            }

            final Animation.AnimationListener animationListener = new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @RequiresApi(api = Build.VERSION_CODES.N)
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

                    rolledDice.getDiceList().get(rolledKeys.get(dieCount-1)).getDieFaceView().setImageResource(res);
                    rolledDice.getDiceList().get(rolledKeys.get(dieCount-1)).setValue(value);

                    if (rolledDice.getDiceList().get(rolledKeys.get(dieCount-1)).getValue() == 1){
                        rolledDice.getDiceList().get(rolledKeys.get(dieCount-1)).getDieFaceView().setBackgroundColor(Color.BLACK);
                        rollDice.setEnabled(false);
                        scoreDice.setEnabled(false);
                        switch (attempt){
                            case 1:
                                score1Text.setText(Integer.toString(0));
                                score1Text.setTextColor(Color.RED);
                                break;
                            case 2:
                                score2Text.setText(Integer.toString(0));
                                score2Text.setTextColor(Color.RED);
                                break;
                            case 3:
                                score3Text.setText(Integer.toString(0));
                                score3Text.setTextColor(Color.RED);
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
                    } else {
                        rolledDice.getDiceList().get(rolledKeys.get(dieCount-1)).getDieFaceView().setBackgroundColor(Color.GREEN);
                        if (dieCount < 8){
                            rolledDice.getDiceList().get(rolledKeys.get(dieCount)).makeVisible();
                        }
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            };
            anim1.setAnimationListener(animationListener);

            rolledDice.getDiceList().get(rolledKeys.get(dieCount-1)).getDieFaceView().startAnimation(anim1);
        });

        scoreDice.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                rollDice.setEnabled(false);
                scoreDice.setEnabled(false);

                totalScore = rolledDice.ScoreDiceNormal();
                switch (attempt){
                    case 1:
                        score1Text.setText(Integer.toString(totalScore));
                        score1Text.setTextColor(Color.GREEN);
                        nextAttempt.setEnabled(true);
                        intScore1 = totalScore;
                        break;
                    case 2:
                        score2Text.setText(Integer.toString(totalScore));
                        score2Text.setTextColor(Color.GREEN);
                        nextAttempt.setEnabled(true);
                        intScore2 = totalScore;
                        break;
                    case 3:
                        score3Text.setText(Integer.toString(totalScore));
                        score3Text.setTextColor(Color.GREEN);
                        resetGame.setEnabled(true);
                        intScore3 = totalScore;
                        int maxValue = Integer.max(intScore1, intScore2);
                        maxValue = Integer.max(maxValue, intScore3);
                        fullGameScore += maxValue;
                        fullGameScoreText.setText(Integer.toString(fullGameScore));
                        break;
                }
            }
        });

        nextAttempt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextAttempt.setEnabled(false);
                rollDice.setEnabled(true);
                attempt++;
                attemptsLeftText.setText(Integer.toString(attempt));

                totalScore = 0;
                dieCount = 0;

                rolledDice.MakeHidden();
                rolledDice.ChangeBackgroundColor(Color.WHITE);
                rolledDice.MakeOnes();
                rolledDice.getDiceList().get("rollDie1").makeVisible();

            }
        });

        resetGame.setOnClickListener(v -> {
            if (isFullGame){
                Intent newIntent = new Intent(EventShotPut.this, EventHighJump.class);
                newIntent.putExtra("isFullGame", isFullGame);
                newIntent.putExtra("fullGameScore", fullGameScore);
                startActivity(newIntent);
                finish();
            } else {
                resetGame.setEnabled(false);
                nextAttempt.setEnabled(false);
                rollDice.setEnabled(true);
                attempt = 1;
                attemptsLeftText.setText(Integer.toString(attempt));

                totalScore = 0;
                dieCount = 0;

                rolledDice.MakeHidden();
                rolledDice.ChangeBackgroundColor(Color.WHITE);
                rolledDice.MakeOnes();
                rolledDice.getDiceList().get("rollDie1").makeVisible();
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