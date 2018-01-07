package com.example.harrison.whack_a_mole;

import android.os.CountDownTimer;
import android.widget.ImageButton;

public class MoleButtonThreads implements Runnable{

    //Idea: try setting it so that it sets the global button to the specific button
    //Then in run, count the number of taps on that button
    private final ImageButton moleButton;
    private final ImageButton buttonHole;

    /**
     * Purpose: To create a new thread object for controlling the game buttons
     * @param moleButton to give access to the mole image button
     * @param buttonHole to give access to the hole image button
     */
    public MoleButtonThreads(ImageButton moleButton, ImageButton buttonHole) {
        this.moleButton = moleButton;
        this.buttonHole = buttonHole;
    }

    /**
     * Purpose: To run the thread to control the mole and hole buttons according to game rules
     */
    @Override
    public void run() {
        // Counting down until button must disappear
        // Need to randomize time for countdown
        final boolean notFinished[] = {false};
        int randomTime = (int) Math.random() * 4000 + 1000;

        // Create a timer with the same countdown as PlayGame, which sets a boolean to true when finished to exit the thread
        new CountDownTimer (PlayGame.timerLengthMillis, 1000){

            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                //Try using array?
                notFinished[0] = true;
            }
        }.start();


        while (!notFinished[0]) {


            //Creating a timer to control how long the button is visible for
            new CountDownTimer(randomTime, 1000) {

                @Override
                public void onTick(long currentTime) {

                }

                @Override
                public void onFinish() {


                }
            }.start();


        }

    }
}
