package com.example.encore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinalScore extends AppCompatActivity {

    private int finalScore;
    private TextView finalScoreText, tvName;
    private Button done;
    SharedPreferences mPreferences;
    private String sharedPrefFile = "com.example.encore.sharedPrefs";
    private String name = "";
    private String NAME_KEY = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_score);

        tvName = (TextView) findViewById(R.id.tvFinalScoreName);

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        // shared preferences to get the user's name on each play, no need to re-type after first time.
        name = mPreferences.getString(NAME_KEY, "");
        tvName.setText(String.format(name) + "'s");

        done = (Button) findViewById(R.id.buttonReturnHome);

        Intent intent = getIntent();
        finalScoreText = (TextView) findViewById(R.id.tvFinalScore);
        finalScore = intent.getIntExtra("fullGameScore", 0);
        finalScoreText.setText(Integer.toString(finalScore));

        done.setOnClickListener(v -> finish());
    }
}