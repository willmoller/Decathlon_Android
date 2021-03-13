package com.example.encore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class PlayGameActivity extends AppCompatActivity {

    public static final Random RANDOM = new Random();
    private Button rollDice;
    private ImageView imageView1, imageView2;

    public static int randomDiceValue(){
        return RANDOM.nextInt(6) + 1;
    }

    TextView player1Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        player1Name = (TextView) findViewById(R.id.textViewPlayer1TurnLabel);

        Intent intent = getIntent();
        String str = intent.getStringExtra("player1Name");
        player1Name.setText("Your turn " + str + "!");

        rollDice = (Button) findViewById(R.id.button_roll);
        imageView1 = (ImageView) findViewById(R.id.imageFirstDie);
        imageView2 = (ImageView) findViewById(R.id.imageSecondDie);

        rollDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim1 = AnimationUtils.loadAnimation(PlayGameActivity.this, R.anim.shake);
                final Animation anim2 = AnimationUtils.loadAnimation(PlayGameActivity.this, R.anim.shake);

                final Animation.AnimationListener animationListener = new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        int value = randomDiceValue();
                        int res = 0;
                        switch (value){
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
                            imageView1.setImageResource(res);
                        }else if (animation == anim2){
                            imageView2.setImageResource(res);
                        }
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                };

                anim1.setAnimationListener(animationListener);
                anim2.setAnimationListener(animationListener);

                imageView1.startAnimation(anim1);
                imageView2.startAnimation(anim2);
            }
        });


    }
}