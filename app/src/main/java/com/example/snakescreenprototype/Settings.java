package com.example.snakescreenprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

/**
 * Settings activity where the user can change game settings such as the snake color and control setup.
 */
public class Settings extends AppCompatActivity {
    /**
     * Button to return to main menu.
     */
    private Button btnMainMenu;

    /**
     * Used to store which control scheme to use (D-Pad or Touch Pad).
     * Defaults to 0, aka D-Pad.
     */
    private int buttonlayout = 0;

    /**
     * Stores current color of the snake.
     * Defaults to purple.
     */
    private String snakeColor = "Purple";

    /**
     * Stores current speed of the game.
     * Defaults to normal.
     */
    private String gameSpeed = "Normal";

    /**
     * Radio button for D-Pad control scheme.
     */
    private RadioButton ArrowBT;

    /**
     * Radio button for touch pad control scheme.
     */
    private RadioButton FullScreenBT;

    /**
     * Radio button for the snake colors, including:
     * - {@link #rbPurple}: radio button for purple snake
     * - {@link #rbYellow}: radio button for yellow snake
     * - {@link #rbRed}: radio button for red snake
     */
    private RadioButton rbPurple;
    private RadioButton rbYellow;
    private RadioButton rbRed;

    /**
     * Radio button for the snake speed, including:
     * - {@link #rbSlow}: radio button for slow game speed
     * - {@link #rbMedium}: radio button for medium (normal) game speed
     * - {@link #rbFast}: radio button for fast game speed
     */
    private RadioButton rbSlow;
    private RadioButton rbMedium;
    private RadioButton rbFast;

    /**
     * This method is called when the activity is first created. It initializes the layout and sets up the Play Again button to restart the game or return to Main Menu.
     * @param savedInstanceState A Bundle object containing the activity's previously saved state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // SharedPreferences class used to share data needed across activities/screens
        // This screen will store key-value pairs
        SharedPreferences sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        ArrowBT = (RadioButton) findViewById(R.id.ArrowsBT);
        ArrowBT.setOnClickListener(new View.OnClickListener() {
            /**
             * This method sets the button layout to 0 and stores the value in shared preferences.
             * @param view The view that was clicked
             */
            @Override
            public void onClick(View view) {
            buttonlayout = 0;
            // Stores 0 to the key "buttonLayout"
            editor.putInt("buttonlayout", buttonlayout);
            editor.apply();
            }
        });

        FullScreenBT = (RadioButton) findViewById(R.id.FullScreenBT);
        FullScreenBT.setOnClickListener(new View.OnClickListener() {
            /**
             * This method sets the button layout to 1 and stores the value in shared preferences.
             * @param view The view that was clicked
             */
            @Override
            public void onClick(View view) {
                buttonlayout = 1;
                // stores 1 to the key "buttonLayout"
                editor.putInt("buttonlayout", buttonlayout);
                editor.apply();
            }
        });
        btnMainMenu = (Button) findViewById(R.id.btnMainMenu);
        btnMainMenu.setOnClickListener(new View.OnClickListener() {
            /**
             * Implementation of the onClick method for the btnMainMenu button.
             * When clicked, this button opens the main menu of the game.
             * @param v the View object that was clicked
             */
            @Override
            public void onClick(View v) {
                openMainMenu();
            }
        });

        rbPurple = (RadioButton) findViewById(R.id.rbPurple);
        rbPurple.setOnClickListener(new View.OnClickListener() {
            /**
             * Sets color of the snake to purple if radio button purple is pressed.
             * @param v the View object that was clicked
             */
            @Override
            public void onClick(View v) {
                snakeColor = "Purple";
                editor.putString("snakeColor", snakeColor);
                editor.apply();

            }
        });

        rbYellow = (RadioButton) findViewById(R.id.rbYellow);
        rbYellow.setOnClickListener(new View.OnClickListener() {
            /**
             * Sets color of the snake to yellow if radio button yellow is pressed.
             * @param v the View object that was clicked
             */
            @Override
            public void onClick(View v) {
                snakeColor = "Yellow";
                //Store yellow to "snakeColor" key
                editor.putString("snakeColor", snakeColor);
                editor.apply();
            }
        });

        rbRed = (RadioButton) findViewById(R.id.rbRed);
        rbRed.setOnClickListener(new View.OnClickListener() {
            /**
             * Sets color of the snake to red if radio button red is pressed.
             * @param v the View object that was clicked
             */
            @Override
            public void onClick(View v) {
                snakeColor = "Red";
                //Store red to "snakeColor" key
                editor.putString("snakeColor", snakeColor);
                editor.apply();
            }
        });

        rbSlow = (RadioButton) findViewById(R.id.rbSlow);
        rbSlow.setOnClickListener(new View.OnClickListener() {
            /**
             * Sets speed of the snake to slow if radio button slow is pressed.
             * @param v the View object that was clicked
             */
            @Override
            public void onClick(View v) {
                gameSpeed = "Slow";
                //Store red to "snakeColor" key
                editor.putString("gameSpeed", gameSpeed);
                editor.apply();
            }
        });

        rbMedium = (RadioButton) findViewById(R.id.rbMedium);
        rbMedium.setOnClickListener(new View.OnClickListener() {
            /**
             * Sets speed of the snake to medium (normal) if radio button medium is pressed.
             * @param v the View object that was clicked
             */
            @Override
            public void onClick(View v) {
                gameSpeed = "Medium";
                //Store red to "snakeColor" key
                editor.putString("gameSpeed", gameSpeed);
                editor.apply();
            }
        });

        rbFast = (RadioButton) findViewById(R.id.rbFast);
        rbFast.setOnClickListener(new View.OnClickListener() {
            /**
             * Sets speed of the snake to fast if radio button fast is pressed.
             * @param v the View object that was clicked
             */
            @Override
            public void onClick(View v) {
                gameSpeed = "Fast";
                //Store red to "snakeColor" key
                editor.putString("gameSpeed", gameSpeed);
                editor.apply();
            }
        });

    }


    /**
     * This method is used to navigate to the main menu screen of the game.
     */
    public void openMainMenu(){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}
