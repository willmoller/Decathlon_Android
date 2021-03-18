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

public class Event100Metres extends AppCompatActivity {

    private static final Random RANDOM = new Random();
    private Dice rolledDice, bankedDiceSet1, bankedDiceSet2;
    private ArrayList<ImageView> ivRolledDice, ivBankedDiceSet1, ivBankedDiceSet2;
    private ArrayList<String> rolledKeys, bankKeys1, bankKeys2;
    private int bankedDice, totalScore;
    private Button rollDice, keepDice, resetGame, leaveGame;

    private TextView rollsLeftText, totalScoreText;
    private int rollsLeft = 7;
    private MediaPlayer mp;

    public static int randomDiceValue(){
        return RANDOM.nextInt(6) + 1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event100_metres);

        rollDice = (Button) findViewById(R.id.button_100M_roll);
        keepDice = (Button) findViewById(R.id.button_100M_keep);
        resetGame = (Button) findViewById(R.id.button_100M_replay);
        resetGame.setEnabled(false);
        leaveGame = (Button) findViewById(R.id.button_100M_done);

        rollsLeftText = (TextView) findViewById(R.id.tv100MRollsRemain);
        totalScoreText = (TextView) findViewById(R.id.tv100MScore);
        bankedDice = 0;
        totalScore = 0;

        ivRolledDice = new ArrayList<ImageView>();
        rolledKeys = new ArrayList<String>();

        rolledKeys.add("rollDie1");
        ivRolledDice.add(findViewById(R.id.iv100MrollDie1));

        rolledKeys.add("rollDie2");
        ivRolledDice.add(findViewById(R.id.iv100MrollDie2));

        rolledKeys.add("rollDie3");
        ivRolledDice.add(findViewById(R.id.iv100MrollDie3));

        rolledKeys.add("rollDie4");
        ivRolledDice.add(findViewById(R.id.iv100MrollDie4));

        ivBankedDiceSet1 = new ArrayList<ImageView>();
        ivBankedDiceSet2 = new ArrayList<ImageView>();
        bankKeys1 = new ArrayList<String>();
        bankKeys2 = new ArrayList<String>();

        bankKeys1.add("bankDie1");
        bankKeys1.add("bankDie2");
        bankKeys1.add("bankDie3");
        bankKeys1.add("bankDie4");
        bankKeys2.add("bankDie5");
        bankKeys2.add("bankDie6");
        bankKeys2.add("bankDie7");
        bankKeys2.add("bankDie8");

        ivBankedDiceSet1.add(findViewById(R.id.iv100MbankDie1));
        ivBankedDiceSet1.add(findViewById(R.id.iv100MbankDie2));
        ivBankedDiceSet1.add(findViewById(R.id.iv100MbankDie3));
        ivBankedDiceSet1.add(findViewById(R.id.iv100MbankDie4));
        ivBankedDiceSet2.add(findViewById(R.id.iv100MbankDie5));
        ivBankedDiceSet2.add(findViewById(R.id.iv100MbankDie6));
        ivBankedDiceSet2.add(findViewById(R.id.iv100MbankDie7));
        ivBankedDiceSet2.add(findViewById(R.id.iv100MbankDie8));

        rolledDice = new Dice(ivRolledDice, rolledKeys);
        bankedDiceSet1 = new Dice(ivBankedDiceSet1, bankKeys1);
        bankedDiceSet2 = new Dice(ivBankedDiceSet2, bankKeys2);

        rolledDice.MakeVisible();

        mp = MediaPlayer.create(Event100Metres.this, R.raw.dice);

        rollDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp.release();
                        mp = MediaPlayer.create(Event100Metres.this, R.raw.dice);
                    } mp.start();
                } catch(Exception e) { e.printStackTrace(); }

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

                rolledDice.getDiceList().get("rollDie1").getDieFaceView().startAnimation(anim1);
                rolledDice.getDiceList().get("rollDie2").getDieFaceView().startAnimation(anim2);
                rolledDice.getDiceList().get("rollDie3").getDieFaceView().startAnimation(anim3);
                rolledDice.getDiceList().get("rollDie4").getDieFaceView().startAnimation(anim4);

                rollsLeftText.setText(Integer.toString(rollsLeft));
            }
        });

        keepDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bankRolledDice();
                // make Keep button unclickable
                keepDice.setEnabled(false);

                if (bankedDice == 8){
                    rollDice.setEnabled(false);
                    keepDice.setEnabled(false);
                } else if (rollsLeft > 0){
                    rollDice.setEnabled(true);
                }
            }
        });

        resetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bankedDiceSet1.MakeHidden();
                bankedDiceSet2.MakeHidden();
                rollDice.setEnabled(true);
                totalScore = 0;
                totalScoreText.setText(Integer.toString(totalScore));
                rollsLeft = 7;
                rollsLeftText.setText(Integer.toString(rollsLeft));
                bankedDice = 0;
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

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
    }

    private void bankRolledDice(){
        // move rollDie's to bankDie's
        if (bankedDice == 0){
            bankedDiceSet1.getDiceList().get("bankDie1").setValue(rolledDice.getDiceList().get("rollDie1").getValue());
            bankedDiceSet1.getDiceList().get("bankDie1").getDieFaceView().setImageDrawable(rolledDice.getDiceList().get("rollDie1").getDieFaceView().getDrawable());
            bankedDiceSet1.getDiceList().get("bankDie2").setValue(rolledDice.getDiceList().get("rollDie2").getValue());
            bankedDiceSet1.getDiceList().get("bankDie2").getDieFaceView().setImageDrawable(rolledDice.getDiceList().get("rollDie2").getDieFaceView().getDrawable());
            bankedDiceSet1.getDiceList().get("bankDie3").setValue(rolledDice.getDiceList().get("rollDie3").getValue());
            bankedDiceSet1.getDiceList().get("bankDie3").getDieFaceView().setImageDrawable(rolledDice.getDiceList().get("rollDie3").getDieFaceView().getDrawable());
            bankedDiceSet1.getDiceList().get("bankDie4").setValue(rolledDice.getDiceList().get("rollDie4").getValue());
            bankedDiceSet1.getDiceList().get("bankDie4").getDieFaceView().setImageDrawable(rolledDice.getDiceList().get("rollDie4").getDieFaceView().getDrawable());

            totalScore += bankedDiceSet1.ScoreDice();
            bankedDiceSet1.MakeVisible();
        } else {
            bankedDiceSet2.getDiceList().get("bankDie5").setValue(rolledDice.getDiceList().get("rollDie1").getValue());
            bankedDiceSet2.getDiceList().get("bankDie5").getDieFaceView().setImageDrawable(rolledDice.getDiceList().get("rollDie1").getDieFaceView().getDrawable());
            bankedDiceSet2.getDiceList().get("bankDie6").setValue(rolledDice.getDiceList().get("rollDie2").getValue());
            bankedDiceSet2.getDiceList().get("bankDie6").getDieFaceView().setImageDrawable(rolledDice.getDiceList().get("rollDie2").getDieFaceView().getDrawable());
            bankedDiceSet2.getDiceList().get("bankDie7").setValue(rolledDice.getDiceList().get("rollDie3").getValue());
            bankedDiceSet2.getDiceList().get("bankDie7").getDieFaceView().setImageDrawable(rolledDice.getDiceList().get("rollDie3").getDieFaceView().getDrawable());
            bankedDiceSet2.getDiceList().get("bankDie8").setValue(rolledDice.getDiceList().get("rollDie4").getValue());
            bankedDiceSet2.getDiceList().get("bankDie8").getDieFaceView().setImageDrawable(rolledDice.getDiceList().get("rollDie4").getDieFaceView().getDrawable());

            totalScore += bankedDiceSet2.ScoreDice();
            bankedDiceSet2.MakeVisible();
            resetGame.setEnabled(true);
        }

        // add bankDie's to score
        totalScoreText.setText(Integer.toString(totalScore));

        bankedDice += 4;
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
                //View.SYSTEM_UI_FLAG_IMMERSIVE
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_FULLSCREEN
        );
    }
}