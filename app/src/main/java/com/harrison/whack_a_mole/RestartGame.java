package com.harrison.whack_a_mole;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class RestartGame extends AppCompatActivity {

    private int highScoreValue = 0;

    private static final String scoreIntentConst = "score";
    private static final String highScoreIntentConst = "highScore";

    /**
     * Purpose: To set up the restart screen with required information
     * @param savedInstanceState contains all relevant saved state data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restart_game);
        Intent passedIntent = getIntent();

        // Getting the stored score value from the intent
        int scoreValue = passedIntent.getIntExtra(scoreIntentConst, 0);
        highScoreValue = passedIntent.getIntExtra(highScoreIntentConst, 0);

        //Getting each text view for the score and high score fields
        TextView scoreText = (TextView) findViewById(R.id.lastScore);
        TextView highScore = (TextView) findViewById(R.id.highScore);

        //Setting the string for the score field
        String scoreString = getResources().getString(R.string.score) + " " + scoreValue;
        scoreText.setText(scoreString);

        //Setting the string for the high score field
        String highScoreString = "High Score:" + " " + highScoreValue;
        highScore.setText(highScoreString);
    }


    /**
     * Purpose: To switch from the current activity to the PlayGame activity, by setting this method in a button's onClick attribute
     * @param v is the view for using with XML attributes
     */
    public void onClick(View v){
        Intent switchIntent = new Intent(this, PlayGame.class);
        switchIntent.putExtra(highScoreIntentConst, highScoreValue);
        startActivity(switchIntent);
    }


    /**
     * Purpose: To override the back button on the device such that only in-app navigation is enabled
     */
    @Override
    public void onBackPressed(){ }

}
