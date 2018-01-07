package com.example.harrison.whack_a_mole;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

/*
 Whack-A-Mole
 Created by Harrison Turley
 Please view the README for more information
 */

public class MainActivity extends AppCompatActivity {

    /*ImageButton button1;
    ImageButton button2;
    ImageButton button3;
    ImageButton button4;
    ImageButton button5;
    ImageButton button6;
    ImageButton button7;
    ImageButton button8;
    ImageButton button9;*/
    RelativeLayout layout;

    int activeStates = 9;

    ImageButton moleButtons[] = new ImageButton[activeStates];
    ImageButton holes[] = new ImageButton[activeStates];
    Button startButton;

    boolean activeState[] = new boolean[activeStates];

    int tap_time = 250;

    /**
     * Purpose: Sets the app to the start screen activity
     * @param savedInstanceState contains all relevant saved state data, should be null on first start
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }



    public void setUpApp(){
        moleButtons[0] = (ImageButton) findViewById(R.id.moleButton1);
        moleButtons[1] = (ImageButton) findViewById(R.id.moleButton2);
        moleButtons[2] = (ImageButton) findViewById(R.id.moleButton3);
        moleButtons[3] = (ImageButton) findViewById(R.id.moleButton4);
        moleButtons[4] = (ImageButton) findViewById(R.id.moleButton5);
        moleButtons[5] = (ImageButton) findViewById(R.id.moleButton6);
        moleButtons[6] = (ImageButton) findViewById(R.id.moleButton7);
        moleButtons[7] = (ImageButton) findViewById(R.id.moleButton8);
        moleButtons[8] = (ImageButton) findViewById(R.id.moleButton9);

        holes[0] = (ImageButton) findViewById(R.id.hole1);
        holes[1] = (ImageButton) findViewById(R.id.hole2);
        holes[2] = (ImageButton) findViewById(R.id.hole3);
        holes[3] = (ImageButton) findViewById(R.id.hole4);
        holes[4] = (ImageButton) findViewById(R.id.hole5);
        holes[5] = (ImageButton) findViewById(R.id.hole6);
        holes[6] = (ImageButton) findViewById(R.id.hole7);
        holes[7] = (ImageButton) findViewById(R.id.hole8);
        holes[8] = (ImageButton) findViewById(R.id.hole9);

        startButton = (Button) findViewById(R.id.StartButton);


        for (int i = 0; i < activeStates; i++){
            moleButtons[i].setAlpha(0f);
            moleButtons[i].setEnabled(false);
            holes[i].setAlpha(0f);
            holes[i].setEnabled(false);
        }
    }


    public void startGame(){

        startButton.setEnabled(false);
        startButton.setAlpha(0f);

        for (int i = 0; i < activeStates; i++){
            holes[i].setAlpha(1f);
            holes[i].setEnabled(true);
        }

    }


    public void onStartClick(View v){
        setContentView(R.layout.activity_play_game);
    }


    public void enableButton(ImageButton button){

        button.setEnabled(true);

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });


    }


    public void disableButton(ImageButton button){
        button.setEnabled(false);
        button.setAlpha(0f);
    }

}
