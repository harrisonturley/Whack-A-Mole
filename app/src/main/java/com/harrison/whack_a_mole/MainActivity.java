package com.harrison.whack_a_mole;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    int tap_time = 250;

    /**
     * Purpose: Sets the test to the start screen activity
     * @param savedInstanceState contains all relevant saved state data, should be null on first start
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onStartClick(View v){
        Intent switchToRestartIntent = new Intent(this, PlayGame.class);
        startActivity(switchToRestartIntent);
    }
}
