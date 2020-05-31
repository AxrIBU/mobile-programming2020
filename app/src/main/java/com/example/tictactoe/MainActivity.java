package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button[][] buttons = new Button[3][3];
    private TextView tvPlayerOne;
    private TextView tvPlayerTwo;

    private boolean playerOneTurn = true;
    private int playerOnePoints;
    private int playerTwoPoints;
    private int moves;

    private String playerOneName;
    private String playerTwoName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPlayerOne = findViewById(R.id.textview_player1);
        tvPlayerTwo = findViewById(R.id.textview_player2);

        playerOnePoints = 0;
        playerTwoPoints = 0;

        playerOneName = getIntent().getExtras().getString("ValuePlayerOne");
        playerTwoName = getIntent().getExtras().getString("ValuePlayerTwo");

        tvPlayerOne.setText(playerOneName + ": " + playerOnePoints);
        tvPlayerTwo.setText(playerTwoName + ": " + playerTwoPoints);

        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                String btnIdString = "button"+i+j;
                int resourceId = getResources().getIdentifier(btnIdString,  "id", getPackageName());
                buttons[i][j] = findViewById(resourceId);
                buttons[i][j].setOnClickListener(this);
            }
        }

        ImageButton reset = findViewById(R.id.resetbtn_id);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    @Override
    public void onClick(View v) {
         if (!((Button) v).getText().toString().equals("")) {
             return;
         }
         if(playerOneTurn) {
             ((Button) v).setText("X");
         }
         else {
             ((Button) v).setText("O");
         }
        moves++;

         if(check4wins()){
             if(playerOneTurn) {
                 playerOneWins();
             }
             else {
                 playerTwoWins();
             }
         }else if(moves == 9) {
             draw();
         }
         else {
             playerOneTurn = !playerOneTurn;
         }
    }
    public void playerOneWins(){
        playerOnePoints++;
        displayToastText("Winner: " + playerOneName);
        resetAllButtonText();
        updateScore();
    }

    public void playerTwoWins(){
        playerTwoPoints++;
        displayToastText("Winner: " + playerTwoName);
        resetAllButtonText();
        updateScore();
    }

    public void draw(){
        displayToastText("It's a DRAW!");
        resetAllButtonText();
    }

    public void resetAllButtonText(){
        for(int i = 0; i < 3; i++ )
        {
            for(int j = 0; j < 3; j++)
            {
                buttons[i][j].setText("");
            }
        }
        moves = 0;
        playerOneTurn = true;
    }

    public void resetGame(){
        playerOnePoints = 0;
        playerTwoPoints = 0;
        updateScore();
        resetAllButtonText();
        displayToastText("The game has been reset!");
    }

    public void updateScore(){
        tvPlayerOne.setText(playerOneName + ": " + playerOnePoints);
        tvPlayerTwo.setText(playerTwoName + ": " + playerTwoPoints);
    }

    public void displayToastText(CharSequence text){
        Context context = getApplicationContext();
        CharSequence _text = text;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, _text, duration);
        toast.show();
    }
    public boolean check4wins(){
        String[][] spot = new String[3][3];
        for(int i = 0; i < 3; i++ )
        {
            for(int j = 0; j < 3; j++)
            {
                spot[i][j] = buttons[i][j].getText().toString();
            }
        }

        for(int i = 0; i < 3; i++)
        {
            if (spot[i][0].equals(spot[i][1]) && spot[i][0].equals(spot[i][2]) && !spot[i][0].equals(""))
            {
                return true;
            }
            if(spot[0][i].equals(spot[1][i]) && spot[0][i].equals(spot[2][i]) && !spot[0][i].equals(""))
            {
                return true;
            }
        }
        if(spot[0][0].equals(spot[1][1]) && spot[0][0].equals(spot[2][2]) && !spot[0][0].equals(""))
        {
            return true;
        }
        if(spot[0][2].equals(spot[1][1]) && spot[0][2].equals(spot[2][0]) && !spot[0][2].equals(""))
        {
            return true;
        }
        return false;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean("PlayerOneTurn", playerOneTurn);
        outState.putInt("Moves", moves);
        outState.putInt("PlayerOnePoints", playerOnePoints);
        outState.putInt("PlayerTwoPoints", playerTwoPoints);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        playerOneTurn = savedInstanceState.getBoolean("PlayerOneTurn");
        moves = savedInstanceState.getInt("Moves");
        playerOnePoints = savedInstanceState.getInt("PlayerOnePoints");
        playerTwoPoints = savedInstanceState.getInt("PlayerTwoPoints");
    }
}
