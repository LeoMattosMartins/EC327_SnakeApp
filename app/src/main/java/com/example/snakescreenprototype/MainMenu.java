package com.example.snakescreenprototype;

import static java.lang.Boolean.valueOf;
import static com.example.snakescreenprototype.MainGameScreen.highScoreAttempt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * This class represents the main menu activity for the Snake game. It allows the user to choose between
 * starting a new game, viewing the high scores, going to settings, and exiting the application.
 */
public class MainMenu extends AppCompatActivity {

    /**
     * Initializes button used to start the game.
     */
    private Button btnStartGame;

    /**
     * Initializes button used to go to the settings activity.
     */
    private Button btnSetting;

    /**
     * This method is called when the activity is first created. It is responsible for initializing the activity, creating the user interface, and binding data to the views.
     * @param savedInstanceState a Bundle object containing the activity's previously saved state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.loadLibrary("cpp_code");

        android.widget.TextView highScoreTable = findViewById(R.id.HighScoreTable);
        highScoreTable.setText(highScore(highScoreAttempt));
        
        btnStartGame = (Button) findViewById(R.id.btnStartGame);
        btnStartGame.setOnClickListener(new View.OnClickListener() {
            /**
             * When "Start Game" button is clicked, open main game screen
             */
            @Override
            public void onClick(View v) {
                openGame();
            }
        });
        /**
         * Create a variable for the button labeled "Settings"
         */
        btnSetting = (Button) findViewById(R.id.btnSetting);
        btnSetting.setOnClickListener(new View.OnClickListener() {
            /**
             * When "Settings" button is clicked, open settings screen
             */
            @Override
            public void onClick(View v) {
                openSettings();
            }
        });
    }

    /**
     * Opens the main game screen activity.
     */
    public void openGame(){
        Intent intent = new Intent(this, MainGameScreen.class);
        startActivity(intent);
    }

    /**
     * Opens the settings screen activity.
     */
    public void openSettings(){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    /**
     *
     * @param num A number of type T
     * @return true iff num is greater than 0
     * @param <T> A type that extends Number
     */
    static <T extends Number> boolean amIPositive(T num){
        return (int)num > 0;
    }


    private native String highScore(int hsa);

}
