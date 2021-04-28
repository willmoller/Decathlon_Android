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

import java.util.ArrayList;
import java.util.Random;

public class Event400Metres extends AppCompatActivity {

    private static final Random RANDOM = new Random();
    private Dice rolledDice;
    private ArrayList<ImageView> ivRolledDice;
    private ArrayList<String> rolledKeys;
    private int totalScore, rollsLeft, dieCount, fullGameScore;
    private Button rollDie, keepDie, resetGame, leaveGame;
    private boolean isFullGame;
    private TextView rollsLeftText, scoreText, fullGameScoreLabel, fullGameScoreText;
    private MediaPlayer mp;

    public static int randomDiceValue(){
        return RANDOM.nextInt(6) + 1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event400_metres);

        rollDie = (Button) findViewById(R.id.button_roll_400M);
        keepDie = (Button) findViewById(R.id.button_keep_400M);
        keepDie.setEnabled(false);
        resetGame = (Button) findViewById(R.id.button_replay_400M);
        resetGame.setEnabled(false);
        leaveGame = (Button) findViewById(R.id.button_done_400M);

        rollsLeftText = (TextView) findViewById(R.id.tv400MRollsLeft);
        scoreText = (TextView) findViewById(R.id.tv400MScore);

        totalScore = 0;
        rollsLeft = 9;
        dieCount = 1;

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

        ivRolledDice.add(findViewById(R.id.iv400MRollDie1));
        ivRolledDice.add(findViewById(R.id.iv400MRollDie2));
        ivRolledDice.add(findViewById(R.id.iv400MRollDie3));
        ivRolledDice.add(findViewById(R.id.iv400MRollDie4));
        ivRolledDice.add(findViewById(R.id.iv400MRollDie5));
        ivRolledDice.add(findViewById(R.id.iv400MRollDie6));
        ivRolledDice.add(findViewById(R.id.iv400MRollDie7));
        ivRolledDice.add(findViewById(R.id.iv400MRollDie8));

        rolledDice = new Dice(ivRolledDice, rolledKeys);
        rolledDice.getDiceList().get("rollDie1").makeVisible();
        rolledDice.getDiceList().get("rollDie2").makeVisible();

        mp = new MediaPlayer();

        Intent intent = getIntent();
        isFullGame = intent.getBooleanExtra("isFullGame", false);
        fullGameScoreLabel = (TextView) findViewById(R.id.tv400MTotalScoreLabel);
        fullGameScoreText = (TextView) findViewById(R.id.tv400MTotalScore);

        if (!isFullGame){
            fullGameScoreLabel.setVisibility(View.INVISIBLE);
            fullGameScoreText.setVisibility(View.INVISIBLE);
            resetGame.setText("Replay");
        } else {
            fullGameScore = intent.getIntExtra("fullGameScore", 0);
            fullGameScoreText.setText(Integer.toString(fullGameScore));
            resetGame.setText("Next");
        }

        rollDie.setOnClickListener(v -> {
            try {
                mp.stop();
                mp.release();
                mp = MediaPlayer.create(this, R.raw.dice);
                mp.start();
            } catch(Exception e) { e.printStackTrace(); }

            final Animation anim1 = AnimationUtils.loadAnimation(Event400Metres.this, R.anim.shake);
            final Animation anim2 = AnimationUtils.loadAnimation(Event400Metres.this, R.anim.shake);
            rollsLeft--;
            rollsLeftText.setText(Integer.toString(rollsLeft));
            keepDie.setEnabled(true);

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

                    if (animation == anim1) {
                        rolledDice.getDiceList().get(rolledKeys.get(dieCount-1)).getDieFaceView().setImageResource(res);
                        rolledDice.getDiceList().get(rolledKeys.get(dieCount-1)).setValue(value);
                    } else {
                        rolledDice.getDiceList().get(rolledKeys.get(dieCount)).getDieFaceView().setImageResource(res);
                        rolledDice.getDiceList().get(rolledKeys.get(dieCount)).setValue(value);
                    }



                    if (rolledDice.getDiceList().get(rolledKeys.get(dieCount-1)).getValue() == 6){
                        rolledDice.getDiceList().get(rolledKeys.get(dieCount-1)).getDieFaceView().setBackgroundColor(Color.BLACK);
                    } else {
                        rolledDice.getDiceList().get(rolledKeys.get(dieCount-1)).getDieFaceView().setBackgroundColor(Color.GREEN);
                    }
                    if (rolledDice.getDiceList().get(rolledKeys.get(dieCount)).getValue() == 6){
                        rolledDice.getDiceList().get(rolledKeys.get(dieCount)).getDieFaceView().setBackgroundColor(Color.BLACK);
                    } else {
                        rolledDice.getDiceList().get(rolledKeys.get(dieCount)).getDieFaceView().setBackgroundColor(Color.GREEN);
                    }

                    if (rollsLeft == ((7 - dieCount) / 2) || (dieCount == 8 && rollsLeft == 0)){
                        rollDie.setEnabled(false);
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            };
            anim1.setAnimationListener(animationListener);
            anim2.setAnimationListener(animationListener);

            rolledDice.getDiceList().get(rolledKeys.get(dieCount-1)).getDieFaceView().startAnimation(anim1);
            rolledDice.getDiceList().get(rolledKeys.get(dieCount)).getDieFaceView().startAnimation(anim2);
        });

        keepDie.setOnClickListener(v -> {
            keepDie.setEnabled(false);
            int dieValue1 = rolledDice.getDiceList().get(rolledKeys.get(dieCount-1)).getValue();
            if (dieValue1 == 6){
                totalScore -= dieValue1;
            } else {
                totalScore += dieValue1;
            }

            int dieValue2 = rolledDice.getDiceList().get(rolledKeys.get(dieCount)).getValue();
            if (dieValue2 == 6){
                totalScore -= dieValue2;
            } else {
                totalScore += dieValue2;
            }

            scoreText.setText(Integer.toString(totalScore));
            if (dieCount < 7){
                rolledDice.getDiceList().get(rolledKeys.get(dieCount + 1)).makeVisible();
                rolledDice.getDiceList().get(rolledKeys.get(dieCount + 2)).makeVisible();
            }

            if (dieCount < 7){
                rollDie.setEnabled(true);
            } else {
                rollDie.setEnabled(false);
                resetGame.setEnabled(true);
                fullGameScore += totalScore;
                fullGameScoreText.setText(Integer.toString(fullGameScore));
            }
            dieCount += 2;
        });

        resetGame.setOnClickListener(v -> {
            if (isFullGame){
                Intent newIntent = new Intent(Event400Metres.this, Event110MetreHurdles.class);
                newIntent.putExtra("isFullGame", isFullGame);
                newIntent.putExtra("fullGameScore", fullGameScore);
                startActivity(newIntent);
                finish();
            } else {
                totalScore = 0;
                rollsLeft = 9;
                dieCount = 1;

                scoreText.setText("-");
                rollsLeftText.setText(Integer.toString(rollsLeft));

                rolledDice.MakeOnes();
                rolledDice.MakeHidden();
                rolledDice.ChangeBackgroundColor(Color.WHITE);

                rolledDice.getDiceList().get("rollDie1").makeVisible();
                rolledDice.getDiceList().get("rollDie2").makeVisible();

                rollDie.setEnabled(true);
                keepDie.setEnabled(false);
                resetGame.setEnabled(false);
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