package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    boolean gameOn = true;
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void onTap(View view){
        TextView status = findViewById(R.id.status);
        ImageView img = (ImageView) view;
        Button resetBtn = findViewById(R.id.resetBtn);
        int imgNo = Integer.parseInt(img.getTag().toString());
        // If Box is empty
        if (gameState[imgNo]==2 && gameOn){
            gameState[imgNo] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 1) {
                img.setImageResource(R.drawable.x);
                status.setText("Player 1's turn!");
                activePlayer = 0;
            } else {
                img.setImageResource(R.drawable.tick);
                status.setText("Player 2's turn!");
                activePlayer = 1;
            }

            img.animate().translationYBy(1000f).setDuration(300);

            // Checking if any player won
            for (int[] eachPosition:  winningPositions){
                    if (gameState[eachPosition[0]] == gameState[eachPosition[1]] &&
                        gameState[eachPosition[1]] == gameState[eachPosition[2]] &&
                        gameState[eachPosition[0]] != 2 && gameState[eachPosition[1]] != 2 &&
                        gameState[eachPosition[2]] != 2
                    )
                    {
                        status.setText(activePlayer==1?"Player 1 Won":"Player 2 Won");
                        gameOn = false;
                        resetBtn.setVisibility(View.VISIBLE);
                        return;
                    }
            }
            if (checkDraw()) {
                status.setText("Game Draw");
                gameOn = false;
                resetBtn.setVisibility(View.VISIBLE);
            }
        }
        // If Box isn't empty i.e To give a warning
        else{
            if(!gameOn){
                status.setText("Game Ended, Reset");
            }
            else{
                status.setText("Already Clicked, try again!!!");
            }
        }

    }

    public void reset(View view){
        TextView status = findViewById(R.id.status);
        Button resetBtn = findViewById(R.id.resetBtn);
        activePlayer = 0;
        Arrays.fill(gameState, 2);
        status.setText("Tap to Start Playing");
        gameOn = true;
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView10)).setImageResource(0);
        resetBtn.setVisibility(View.GONE);
    }
    public boolean checkDraw(){
        for (int eachState:gameState)
            if (eachState == 2) return false;

        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}