package com.example.harrison.whack_a_mole;

import android.widget.ImageButton;

public class MoleButtonThreads implements Runnable{

    //Idea: try setting it so that it sets the global button to the specific button
    //Then in run, count the number of taps on that button

    private final ImageButton moleButton;
    private final ImageButton buttonHole;

    public MoleButtonThreads(ImageButton moleButton, ImageButton buttonHole) {
        this.moleButton = moleButton;
        this.buttonHole = buttonHole;
    }

    @Override
    public void run() {

    }
}
