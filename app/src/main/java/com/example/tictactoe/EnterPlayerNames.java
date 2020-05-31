package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EnterPlayerNames extends AppCompatActivity {

    Button btnSubmit;
    EditText editTextOne;
    EditText editTextTwo;
    String nameOne;
    String nameTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_player_names);

        btnSubmit = findViewById(R.id.buttonSubmit);
        editTextOne = findViewById(R.id.et_player1);
        editTextTwo = findViewById(R.id.et_player2);

        btnSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EnterPlayerNames.this,MainActivity.class);
                nameOne = editTextOne.getText().toString();
                nameTwo = editTextTwo.getText().toString();
                i.putExtra("ValuePlayerOne",nameOne);
                i.putExtra("ValuePlayerTwo",nameTwo);
                startActivity(i);
                finish();
            }
        });
    }
}
