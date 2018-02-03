package com.harrison.whack_a_mole;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class PlayGame extends AppCompatActivity {

    int overallScore = 0;

    // TODO: change to 2 minutes after testing
    final static int timerLengthMillis = 30000;
    private static int timerIntervalMillis = 1000;
    private static final int numOfMoles = 9;
    private static int upperTimerLimit = 500;
    private static int lowerTimerLimit = 1000;

    private ImageButton[] moleButtons = new ImageButton[numOfMoles];
    private ImageButton[] holeButtons = new ImageButton[numOfMoles];
    private final boolean[] buttonStatus = new boolean[numOfMoles];
    private final boolean[] isFinished = new boolean[numOfMoles];
    private CountDownTimer[] buttonTimers = new CountDownTimer[numOfMoles];

    private static final String timeStringConstant = "Time left: ";
    private static final String scoreStringConstant = "Score: ";



    /**
     * Purpose: To create the initial state of the game, such as starting each new thread
     * @param savedInstanceState contains all relevant data from saved state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        //Timer until finished
        new CountDownTimer(timerLengthMillis, timerIntervalMillis){
            TextView timeText = (TextView) findViewById(R.id.countdownTimer);

            @Override
            public void onTick(long timeLeftMillis) {
                // Setting the time left text to the current time on each tick of the countdown
                timeText.setText(timeStringConstant + TimeUnit.MILLISECONDS.toSeconds(timeLeftMillis));
            }

            @Override
            public void onFinish() {
                // Switch to restart activity

                Intent switchToRestartIntent = new Intent(PlayGame.this, RestartGame.class);
                switchToRestartIntent.putExtra("Score", overallScore);
                startActivity(switchToRestartIntent);
            }
        }.start();

        updateScoreText();
        enableButtonsForGame();
        gameLogic();

    }


    public void enableButtonsForGame(){
        //Populating each array of buttons for access elsewhere
        holeButtons[0] = (ImageButton) findViewById(R.id.hole1);
        holeButtons[1] = (ImageButton) findViewById(R.id.hole2);
        holeButtons[2] = (ImageButton) findViewById(R.id.hole3);
        holeButtons[3] = (ImageButton) findViewById(R.id.hole4);
        holeButtons[4] = (ImageButton) findViewById(R.id.hole5);
        holeButtons[5] = (ImageButton) findViewById(R.id.hole6);
        holeButtons[6] = (ImageButton) findViewById(R.id.hole7);
        holeButtons[7] = (ImageButton) findViewById(R.id.hole8);
        holeButtons[8] = (ImageButton) findViewById(R.id.hole9);

        moleButtons[0] = (ImageButton) findViewById(R.id.moleButton1);
        moleButtons[1] = (ImageButton) findViewById(R.id.moleButton2);
        moleButtons[2] = (ImageButton) findViewById(R.id.moleButton3);
        moleButtons[3] = (ImageButton) findViewById(R.id.moleButton4);
        moleButtons[4] = (ImageButton) findViewById(R.id.moleButton5);
        moleButtons[5] = (ImageButton) findViewById(R.id.moleButton6);
        moleButtons[6] = (ImageButton) findViewById(R.id.moleButton7);
        moleButtons[7] = (ImageButton) findViewById(R.id.moleButton8);
        moleButtons[8] = (ImageButton) findViewById(R.id.moleButton9);

        //Setting the holes to remove points when tapped
        for (int index = 0; index < numOfMoles; index++){
            onClickSubtractScore(holeButtons[index]);
        }

        //Setting the moles to add points when tapped
        for (int index = 0; index < numOfMoles; index++){
            onClickAddScore(moleButtons[index], index);
        }
    }



    /**
     * Purpose: To set an image button to add to the global current score each time it is clicked
     * @param buttonToListen is the button to have the click listener set
     */
    public void onClickSubtractScore(ImageButton buttonToListen){
        buttonToListen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                if (overallScore > 0) {
                    overallScore--;
                } else {
                    overallScore = 0;
                }
                updateScoreText();
            }
        });
    }



    /**
     * Purpose: To set an image button to subtract from the global current score each time it is clicked
     * @param buttonToListen is the button to have the click listener set
     */
    public void onClickAddScore(ImageButton buttonToListen, int buttonNumber){
        final int currentButtonIndex = buttonNumber;

        buttonToListen.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                overallScore++;
                updateScoreText();

                //Cancelling the previously created timer
                buttonTimers[currentButtonIndex].cancel();
                //Need to disable button for a time here
                disableMoleButton(currentButtonIndex);

                //Waiting a random amount of time disabled before possibly re-enabling
                Random randomNumGenerator = new Random();
                int timerStartTime = randomNumGenerator.nextInt(upperTimerLimit) + lowerTimerLimit;
                new CountDownTimer(timerStartTime, timerIntervalMillis){

                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        isFinished[currentButtonIndex] = true;
                        restartButton(moleButtons[currentButtonIndex], currentButtonIndex);
                    }
                }.start();
            }
        });
    }



    /**
     * Purpose: To update the score counter text whenever the score changes
     */
    public void updateScoreText(){
        TextView scoreText = (TextView) findViewById(R.id.scoreCounter);
        scoreText.setText(scoreStringConstant + overallScore);
    }


    /**
     * Purpose: To get a random boolean value based on the range of the parameters
     * @param upperBound is the upper bound of the number generator (exclusive)
     * @return the random boolean generated
     */
    boolean getRandomTrueFalse(int upperBound){
        Random randNumGenerator = new Random();
        int randomStatus = randNumGenerator.nextInt(upperBound);

        return randomStatus == 1;
    }


    // Rules: 2 minutes on the clock
    // Each tap is +1 on the score
    // Possible side rule: each miss is a -1? (Min is 0)
    // After 2 minutes, transition to other screen with score and restart button displayed
    // Display score in top right on play screen



    public void gameLogic(){
        //Try: Have an array of 9 booleans, and randomly assign them with a num generator (0 or 1 ints)
        Random getRandomNum = new Random();

        // Setting proper boolean values for buttonStatus and isFinished
        for (int index = 0; index < numOfMoles; index++) {
            //Creating 1/3 chance that a button is enabled on start (generating random nums between 0 and 2)
            restartButton(moleButtons[index], index);
        }
    }


    public void restartButton(ImageButton button, int buttonNumber){

        Random getRandomNum = new Random();
        //Creating 1/2 chance that a button is enabled
        buttonStatus[buttonNumber] = getRandomTrueFalse(2);

        final int currentButtonNumber = buttonNumber;
        int timerStartTime = getRandomNum.nextInt(upperTimerLimit) + lowerTimerLimit;
        buttonTimers[buttonNumber] = new CountDownTimer(timerStartTime, timerIntervalMillis){

            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                isFinished[currentButtonNumber] = true;
                restartButton(moleButtons[currentButtonNumber], currentButtonNumber);
            }
        }.start();

        //Set button visibility? Must test for selecting button hidden behind
        if (buttonStatus[buttonNumber]){
            enableMoleButton(buttonNumber);

        } else {
            disableMoleButton(buttonNumber);
        }
    }

    public void disableMoleButton(int buttonNumber){
        moleButtons[buttonNumber].setEnabled(false);
        moleButtons[buttonNumber].setAlpha(0f);
        holeButtons[buttonNumber].bringToFront();
    }


    public void enableMoleButton(int buttonNumber){
        moleButtons[buttonNumber].setEnabled(true);
        moleButtons[buttonNumber].setAlpha(1f);
        moleButtons[buttonNumber].bringToFront();
    }
}
