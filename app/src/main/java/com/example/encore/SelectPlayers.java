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

public class SelectPlayers extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText player1Name;
    Button button_lets_play;
    Spinner selectPlayerCount;
    String spinnerValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_players);

        player1Name = (EditText) findViewById(R.id.textPerson1Name);
        button_lets_play = (Button) findViewById(R.id.buttonBegin);

        button_lets_play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String str = player1Name.getText().toString();

                Intent intent = new Intent(getApplicationContext(), PlayGameActivity.class);
                intent.putExtra("player1Name", str);

                startActivity(intent);
            }
        });

        selectPlayerCount = findViewById(R.id.spinnerPlayerCount);
        if(selectPlayerCount != null){
            selectPlayerCount.setOnItemSelectedListener(this);
        }
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.player_count, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if(selectPlayerCount != null){
            selectPlayerCount.setAdapter(adapter);
        }
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


}