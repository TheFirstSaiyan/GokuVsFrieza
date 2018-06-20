package com.example.nikhileshwar.gokuvsfrieza;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int playerTurn =0;
    private Boolean gameActive=true;

    private int[] gridState={3,3,3,3,3,3,3,3,3};
    private int[][] gamePositions={{0,1,2},{0,3,6},{0,4,8},{2,5,8},{6,7,8},{2,4,6},{3,4,5}};
    public void clicked(View view)
    {
        if(gameActive) {
            ImageView img = (ImageView) view;
            int index = Integer.parseInt(img.getTag().toString());
            System.out.println("index" + index);
            if (playerTurn == 0 && gridState[index] == 3) {
                img.setImageResource(R.drawable.ssj4);
                gridState[index] = 0;
                img.animate().rotation((img.getRotation() + 360));
                playerTurn = 1;
            } else if (playerTurn == 1 && gridState[index] == 3) {
                img.setImageResource(R.drawable.goldenfrieza);
                gridState[index] = 1;
                img.animate().rotation((img.getRotation() + 360));
                playerTurn = 0;
            }

            for (int i = 0; i < gamePositions.length; i++) {
                int gokuWin = 0;
                int friezaWin = 0;

                for (int j = 0; j < 3; j++) {
                    if (gridState[gamePositions[i][j]] == 0)
                        gokuWin++;
                    else if (gridState[gamePositions[i][j]] == 1)
                        friezaWin++;


                }
                if (gokuWin == 3)
                {
                    toast("goku wins");
                    gameActive=false;
                }
                else if (friezaWin == 3)
                {
                    toast("frieza wins");
                    gameActive=false;
                }


            }
            int draw = 0;
            if(gameActive)
            {
                for (int i = 0; i < gridState.length; i++) {
                    if (gridState[i] == 3)
                        draw++;
                }
                if (draw == 0)
                {
                    toast("draw");
                    gameActive=false;
                }
            }

            if(!gameActive)
            {
                LinearLayout linearLayout=(LinearLayout)findViewById(R.id.linearLayout);
                    linearLayout.setVisibility(View.VISIBLE);
            }
        }
    }
        private void toast(String msg)
        {

            Toast.makeText(MainActivity.this,msg,Toast.LENGTH_LONG).show();
        }
        public void playAgain(View view)
        {

            LinearLayout linearLayout=(LinearLayout)findViewById(R.id.linearLayout);

            linearLayout.setVisibility(View.INVISIBLE);
            playerTurn =0;
            gameActive=true;

           for(int i=0;i<gridState.length;i++)
           {
               gridState[i]=3;
           }
            android.support.v7.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);
           // GridLayout gridLayout= findViewById(R.id.gridLayout);
           for(int i=0;i<gridLayout.getChildCount();i++)
           {
               ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
           }
        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
