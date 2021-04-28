package com.example.encore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinalScore extends AppCompatActivity {

    private int finalScore;
    private TextView finalScoreText;
    private Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_score);

        done = (Button) findViewById(R.id.buttonReturnHome);

        Intent intent = getIntent();
        finalScoreText = (TextView) findViewById(R.id.tv1500MTotalScore);
        finalScore = intent.getIntExtra("fullGameScore", 0);
        finalScoreText.setText(Integer.toString(finalScore));

        done.setOnClickListener(v -> finish());
    }
}