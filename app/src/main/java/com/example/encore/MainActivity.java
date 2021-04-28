package com.example.encore;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button singleEventButton, fullGame, rules;
    Spinner singleEventSpinner;
    String spinnerValue;
    Intent intent;
    private boolean isFullGame;
    private String sharedPrefFile = "com.example.encore.sharedPrefs";
    private String name = "";
    private int score = 0;
    private String NAME_KEY = "name";
    private String SCORE_KEY = "score";
    private EditText tvName;
    SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = findViewById(R.id.tvName);

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        // shared preferences to get the user's name on each play, no need to re-type after first time.
        name = mPreferences.getString(NAME_KEY, "");
        tvName.setText(String.format("%s", name));

        singleEventSpinner = findViewById(R.id.spinnerSingleEvent);

        singleEventButton = (Button) findViewById(R.id.button_single_event);
        fullGame = (Button) findViewById(R.id.button_all_events);
        rules = (Button) findViewById(R.id.button_rules);

        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.event_names, android.R.layout.simple_list_item_1);
        singleEventSpinner.setAdapter(adapter);
        singleEventSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerValue = (String)singleEventSpinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fullGame.setOnClickListener(v -> {
            isFullGame = true;
            intent = new Intent(MainActivity.this, Event100Metres.class);
            intent.putExtra("isFullGame", isFullGame);
            startActivity(intent);
        });

        singleEventButton.setOnClickListener(v -> {
            isFullGame = false;
            switch (spinnerValue){
                case "100 Metres":
                    intent = new Intent(MainActivity.this, Event100Metres.class);
                    intent.putExtra("isFullGame", isFullGame);
                    startActivity(intent);
                    break;
                case "110 Metre Hurdles":
                    intent = new Intent(MainActivity.this, Event110MetreHurdles.class);
                    intent.putExtra("isFullGame", isFullGame);
                    startActivity(intent);
                    break;
                case "1500 Metres":
                    intent = new Intent(MainActivity.this, Event1500Metres.class);
                    intent.putExtra("isFullGame", isFullGame);
                    startActivity(intent);
                    break;
                case "400 Metres":
                    intent = new Intent(MainActivity.this, Event400Metres.class);
                    intent.putExtra("isFullGame", isFullGame);
                    startActivity(intent);
                    break;
                case "Discus":
                    intent = new Intent(MainActivity.this, EventDiscus.class);
                    intent.putExtra("isFullGame", isFullGame);
                    startActivity(intent);
                    break;
                case "High Jump":
                    intent = new Intent(MainActivity.this, EventHighJump.class);
                    intent.putExtra("isFullGame", isFullGame);
                    startActivity(intent);
                    break;
                case "Javelin":
                    intent = new Intent(MainActivity.this, EventJavelin.class);
                    intent.putExtra("isFullGame", isFullGame);
                    startActivity(intent);
                    break;
                case "Long Jump":
                    intent = new Intent(MainActivity.this, EventLongJump.class);
                    intent.putExtra("isFullGame", isFullGame);
                    startActivity(intent);
                    break;
                case "Pole Vault":
                    intent = new Intent(MainActivity.this, EventPoleVault.class);
                    intent.putExtra("isFullGame", isFullGame);
                    startActivity(intent);
                    break;
                case "Shot Put":
                    intent = new Intent(MainActivity.this, EventShotPut.class);
                    intent.putExtra("isFullGame", isFullGame);
                    startActivity(intent);
                    break;
            }
        });

        rules.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://knizia.de/wp-content/uploads/reiner/pdfs/Knizia-Website-Decathlon.pdf"));
            startActivity(browserIntent);
        });

    }

    @Override
    protected void onPause(){
        super.onPause();
        SharedPreferences.Editor prefEditor = mPreferences.edit();
        prefEditor.putString(NAME_KEY, name);
        prefEditor.apply();
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

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt(SCORE_KEY, score);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String spinner_item = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void launchSelectPlayersActivity(View view) {
        Intent intent = new Intent(this, SelectPlayers.class);
        startActivity(intent);
    }

    public void launchSingleEventActivity(View view) {

    }
}