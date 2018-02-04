package com.harrison.whack_a_mole;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class RestartGame extends AppCompatActivity {

    /**
     * Purpose: To set up the restart screen with required information
     * @param savedInstanceState contains all relevant saved state data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restart_game);

        Intent passedIntent = getIntent();
        int scoreValue = passedIntent.getIntExtra("Score", 0);

        TextView scoreText = (TextView) findViewById(R.id.lastScore);
        TextView highScore = (TextView) findViewById(R.id.highScore);

        String scoreString = getResources().getString(R.string.score) + " " + scoreValue;
        scoreText.setText(scoreString);

        // TODO: integrate high score text once Firebase has been implemented
    }


    /**
     * Purpose: To switch from the current activity to the PlayGame activity, by setting this method in a button's onClick attribute
     * @param v is the view for using with XML attributes
     */
    public void onClick(View v){
        Intent switchIntent = new Intent(this, PlayGame.class);
        startActivity(switchIntent);
    }


    /**
     * Purpose: To override the back button on the device such that only in-app navigation is enabled
     */
    @Override
    public void onBackPressed(){
        return;
    }

}
