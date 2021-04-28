package com.example.encore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class SelectPlayers extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText playerName;
    Button button_lets_play;
    Spinner selectPlayer;
    String spinnerValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_players);

        playerName = (EditText) findViewById(R.id.textPerson1Name);
        button_lets_play = (Button) findViewById(R.id.buttonBegin);

        button_lets_play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String str = playerName.getText().toString();

                Intent intent = new Intent(getApplicationContext(), PlayGameActivity.class);
                intent.putExtra("playerName", str);

                startActivity(intent);
            }
        });

        selectPlayer = findViewById(R.id.spinnerPlayers);

        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(this, R.array.player_count, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectPlayer.setAdapter(adapter);
        selectPlayer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String spinner_item = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void launchPlayGameActivity(View view) {
        Intent intent = new Intent(this, PlayGameActivity.class);
        startActivity(intent);
    }

    private AdapterView.OnItemSelectedListener OnCatSpinnerCL = new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

            ((TextView) parent.getChildAt(0)).setTextSize(24);

        }

        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

}