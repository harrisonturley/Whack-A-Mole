package com.example.harrison.whack_a_mole;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class PlayGame extends AppCompatActivity {

    // TODO: change to 2 minutes after testing
    private static int timerLengthMillis = 30000;
    private static int timerIntervalMillis = 1000;

    private static final String timeStringConstant = "Time left: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        ArrayList<Thread> threadList = new ArrayList<Thread>();

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
                // TODO: create intent for switching to restart activity

                Intent switchToRestartIntent = new Intent(PlayGame.this, RestartGame.class);

                startActivity(switchToRestartIntent);
            }
        }.start();

        for (int currentHole = 0; currentHole < 9; currentHole++) {
            switch (currentHole) {
                case 0:
                    ImageButton moleButton = (ImageButton) findViewById(R.id.moleButton1);
                    ImageButton buttonHole = (ImageButton) findViewById(R.id.hole1);

                    threadList.add(new Thread(new MoleButtonThreads(moleButton, buttonHole)));
                    threadList.get(currentHole).start();
                    break;

                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:

                    break;

                default:
                    throw new IllegalArgumentException();
            }
        }
        }
    // Create game logic below
    // Idea: create another activity for displaying restart and final score?

    // Rules: 2 minutes on the clock
    // Each tap is +1 on the score
    // Possible side rule: each miss is a -1? (Min is 0)
    // After 2 minutes, transition to other screen with score and restart button displayed
    // Display score in top right on play screen

    // Idea: try using a thread for each button, must ensure it is thread safe
    //


}
