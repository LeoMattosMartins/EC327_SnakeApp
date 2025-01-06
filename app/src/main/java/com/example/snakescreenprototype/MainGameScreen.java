package com.example.snakescreenprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;


/**
 * The main game screen activity that extends AppCompatActivity and implements SurfaceHolder.Callback.
 * This class is responsible for handling user input, updating the game state, and rendering graphics on the screen.
 */
public class MainGameScreen extends AppCompatActivity implements SurfaceHolder.Callback {

    /**
     * Represents the list of points that make up the snake.
     */
    private final List<SnakePoints> snakePointsList = new ArrayList<>();

    /**
     * The SurfaceView used to render graphics on the screen.
     */
    private SurfaceView surfaceView;
    /**
     * The TextView used to display the player's score.
     */
    private TextView scoreTV;

    /**
     * Stores the highest score attempt by the player in the current game.
     */
    static int highScoreAttempt;

    /**
     * The SurfaceHolder used to draw the snake on the SurfaceView's canvas.
     */
    private SurfaceHolder surfaceHolder;

    /**
     * The current moving position of the snake.
     * Values must be right, left, top, or bottom. Defaults to the right.
     */
    public int movingPosition = 1;

    /**
     * The point size of the snake.
     * Value can be changed to make a bigger snake. Defaults to 24.
     */
    private static final int pointSize = 24;

    /**
     * The default number number of points for the snake tail.
     * Defaults to 1 point.
     */
    private static final int defaultTailPoints = 1;

    /**
     * The color of the snake's head in hexadecimal format.
     */
    public static int snakeHeadColor = 0xff5e63a6;

    /**
     * The color of the snake's body in hexadecimal format.
     */
    public static int snakeBodyColor = 0xff434778;

    /**
     * The color of the apple in hexadecimal format.
     */
    public static final int appleColor = 0xffc4252d;

    /**
     * The color of the obstacle in the form of a Color object.
     */
    public static final int obstacleColor = Color.GRAY;

    /**
     * The speed at which the snake moves, in units per second.
     * This value must be between 1 and 1000.
     * Defaults snake speed to 850.
     */
    private static int snakeMovingSpeed = 850;

    /**
     * The x and y coordinates of a random point on the SurfaceView.
     * These values are used to determine the position of the random point.
     */
    public int positionX, positionY;

    /**
     * The x and y coordinates of all on-screen obstacles
     */
    private int[] obstacleX = new int[10];
    private int[] obstacleY = new int[10];

    /**
     * The number of obstacles currently on-screen
     */
    private int numObstacles = 0;

    /**
     * The number of points that an obstacle is added per
     */
    private int pointsPerObstacle = 250;

    /**
     * A timer used to move the snake or change its position after a specific time interval.
     * The interval is determined by the value of {@link #snakeMovingSpeed}, which sets the snake's speed.
     */
    public Timer timer;

    /**
     * The Canvas object used to draw the snake and display it on the SurfaceView.
     */
    private Canvas canvas = null;

    /**
     * Paint objects used to define the color of points in the game, including:
     * - {@link #pointSnakeHeadColor}: the color of the snake's head
     * - {@link #pointSnakeBodyColor}: the color of the snake's body
     * - {@link #pointAppleColor}: the color of an apple
     * - {@link #pointObstacleColor}: the color of an obstacle
     */
    Paint pointSnakeHeadColor = null;
    Paint pointSnakeBodyColor = null;
    Paint pointAppleColor = null;
    Paint pointObstacleColor = null;

    /**
     * The chomping sound that plays when the snake eats an apple.
     */
    MediaPlayer chomp;

    /**
     * The sound that plays when Game Over.
     */
    MediaPlayer GOSound;

    /**
     * This method retrieves the value of the "buttonlayout" extra passed through an intent from the previous activity.
     * If the extra value is not found, the method returns a default value of 0.
     * @return An integer value representing the "buttonlayout" extra received from the previous activity.
     */
    protected int retreivebuttonlayout(){
        int receivedValue = getIntent().getIntExtra("buttonlayout",0);
        return receivedValue;
    }

    /**
     * Called when the activity is starting. Initializes the game's layout, sets up the game's logic, sets buttons to listen.
     *
     * @param savedInstanceState A Bundle containing the activity's previously saved state, or null if there was no saved state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // SharedPreferences class used to share data needed across activities/screens
        // Retrieving data from the settings screen
        SharedPreferences sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        // Int that stores the button layout for the game from settings
        // Defaults to 0 (D-pad). Other options is 1 (Touch Pad)
        int buttonlayout = sharedPreferences.getInt("buttonlayout", 0);
        if (buttonlayout==0){
            setContentView(R.layout.activity_main_game_screen);} else if (buttonlayout==1) {
            setContentView(R.layout.activity_main_game_screen_no_buttons);}

        // string that stores the snake color from the settings
        // Checks string to determine snake color
        String snakeColor = sharedPreferences.getString("snakeColor","Purple");
        if(snakeColor == "Purple"){
            snakeHeadColor = 0xff5e63a6;
            snakeBodyColor = 0xff434778;
        } else if (snakeColor == "Red"){
            snakeHeadColor = 0xffE81606;
            snakeBodyColor = 0xffa32100;
        } else if (snakeColor == "Yellow"){
            snakeHeadColor = 0xffFFEB3B;
            snakeBodyColor = 0xfff0cd07;
        }

        // string that stores the game speed from the settings
        // Checks string to determine game speed
        String gameSpeed = sharedPreferences.getString("gameSpeed","Medium");
        if(gameSpeed == "Medium"){
            snakeMovingSpeed = 850;
        } else if(gameSpeed == "Fast"){
            snakeMovingSpeed = 900;
        } else if(gameSpeed == "Slow"){
            snakeMovingSpeed = 800;
        }
        // {Leo, 26APR} - Added the C++ implementation file "cpp_code"
        System.loadLibrary("cpp_code");

        // getting surfaceview and score textview from xml file
        surfaceView = findViewById(R.id.surfaceView);
        scoreTV = findViewById(R.id.scoreTV);


        //getting ImageButtons from xml files
        /*final AppCompatImageButton topBtn = findViewById(R.id.topBtn);*/
        final AppCompatImageButton leftBtn = findViewById(R.id.leftBtn);
        final AppCompatImageButton rightBtn = findViewById(R.id.rightBtn);
        /*final AppCompatImageButton bottomBtn = findViewById(R.id.bottomBtn);*/

        //adding callback to surfaceview
        surfaceView.getHolder().addCallback(this);

        // setting chomp to corresponding mp3 file
        chomp = MediaPlayer.create(MainGameScreen.this, R.raw.chomp);
        GOSound = MediaPlayer.create(MainGameScreen.this, R.raw.vineboom);

        leftBtn.setOnClickListener(new View.OnClickListener() {
            /**
             * Decreases the moving position of the snake by 1 when the corresponding button is clicked,
             * and sets the moving position to 3 (indicating "left") if the moving position becomes -1 (indicating "up").
             * @param v the view that was clicked.
             */
            @Override
            public void onClick(View v) {
                movingPosition = leftclick(movingPosition);
            }
        });

        rightBtn.setOnClickListener(new View.OnClickListener() {
            /**
             * Increases the moving position of the snake by 1 when the corresponding button is clicked,
             * and sets the moving position to 0 (indicating "right") if the moving position becomes +1 (indicating "down").
             * @param v the view that was clicked.
             */
            @Override
            public void onClick(View v) {
                movingPosition = rightClick(movingPosition);
            }
        });
    }

    /**
     * Called when the surface is created. Initializes the surfaceHolder and calls the init() method to set up
     * the data for the snake and the surfaceView.
     * @param surfaceHolder The SurfaceHolder object associated with the surfaceView.
     */
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        // when surface is created then get surfaceHolder from it and assign to surfaceHolder
        this.surfaceHolder = surfaceHolder;

        // init data for snake/surfaceview
        init();
    }

    /**
     * Called when the surface dimensions change, such as when the device's orientation changes.
     * @param holder the SurfaceHolder whose surface has changed
     * @param format the new PixelFormat of the surface
     * @param width the new width of the surface
     * @param height the new height of the surface
     */
    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    /**
     * This method is called when the surface of the game screen is destroyed.
     * @param holder The SurfaceHolder whose surface is being destroyed
     */
    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    /**
     * Initializes the game by clearing the snake points/snake length, setting the default score to zero,
     * setting the default moving position, and creating the initial snake tail points. It also adds random
     * points(apples) on the screen to be eaten by the snake and starts moving the snake.
     */
    private void init(){

        //clear snake points/ snake length
        snakePointsList.clear();

        // set default score to 0;

        // {Leo, 26APR} - Using new Score function to start the score for a new game;
        //                implementation can be found in ccp_leo/ccp_code.cpp
        scoreTV.setText(String.valueOf(Score("Start")));

        // setting default moving position
        movingPosition = 1;

        // default snake position on screen
        int startPositionX = (pointSize) * defaultTailPoints;

        //making snake's default length/points
        for(int i = 0; i < defaultTailPoints; i++){

            // adding points to snake tail
            SnakePoints snakePoints = new SnakePoints(startPositionX, pointSize);
            snakePointsList.add(snakePoints);

            // increasing value for next point as snake's tail
            startPositionX = startPositionX - doubleit(pointSize);
        }

        // add random points on the screen to be eaten by the snake
        addPoint();

        // start moving snake/ start game
        moveSnake();

        // reset obstacles
        obstacleX = new int[10];
        obstacleY = new int[10];
        numObstacles = 0;
    }

    /**
     * Adds a new point(apple) to the screen to be eaten by the snake.
     */
    public void addPoint(){

        // getting surfaceView height and width to add point on the surface to be eaten by the snake
        int surfaceWidth = surfaceView.getWidth() - doubleit(pointSize);
        int surfaceHeight = surfaceView.getHeight() - doubleit(pointSize);

        positionX = setX(surfaceWidth, pointSize);
        positionY = setY(surfaceHeight, pointSize);
    }

    /**
     * Adds a new obstacle onto the screen, which kills the player if they run into it
     */
    private void addObstacle(){
        int surfaceWidth = surfaceView.getWidth() - doubleit(pointSize);
        int surfaceHeight = surfaceView.getHeight() - doubleit(pointSize);
        Random rand = new Random();
        int rand_intW = rand.nextInt(surfaceWidth);
        int rand_intH = rand.nextInt(surfaceHeight);
        // Note: there is the possibility that an obstacle spawns onto the apple.
        obstacleX[numObstacles] = setX(surfaceWidth - rand_intW , pointSize);
        obstacleY[numObstacles] = setY(surfaceHeight - rand_intH, pointSize);
        numObstacles++;
    }

    /**
     * Moves the snake continuously on the screen, updates the score and checks for Game Over conditions.
     * The snake grows when it eats an apple and a new apple is added on the screen.
     * The snake is controlled by the user input for direction.
     * If the snake hits the edge of the screen or touches itself, the game is over.
     * The Game Over dialog is shown with an option to play again or return to the main menu.
     */
    private void moveSnake(){

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            /**
             * Controls the movement of the snake and updates the game board.
             * Handles collisions with the game board walls and the snake's own body.
             */
            public void run() {
                //getting head position
                int headPositionX = snakePointsList.get(0).getPositionX();
                int headPositionY = snakePointsList.get(0).getPositionY();

                // check if snake eaten a point
                if(headPositionX == positionX && headPositionY == positionY){

                    // grow snake after eaten point
                    growSnake();

                    // add another random point on the screen
                    addPoint();
                }

                // if score is a greater multiple of pointsPerObstacle than numObstacles
                // and there are less than 10 obstacles on-screen
                if( Score("Show")/pointsPerObstacle > numObstacles
                    && numObstacles < 10){
                    addObstacle();
                }

                switch (movingPosition){

                    //Moving Top
                    case 0:

                        // move snake's head to top
                        // other points follow snake's head point to move the snake
                        snakePointsList.get(0).setPositionX(headPositionX);
                        snakePointsList.get(0).setPositionY(headPositionY - doubleit(pointSize));
                        break;

                    //Moving Right
                    case 1:

                        // move snake's head to right
                        // other points follow snake's head point to move the snake
                        snakePointsList.get(0).setPositionX(headPositionX + doubleit(pointSize));
                        snakePointsList.get(0).setPositionY(headPositionY);
                        break;


                    //Moving Bottom
                    case 2:

                        // move snake's head to bottom
                        // other points follow snake's head point to move the snake
                        snakePointsList.get(0).setPositionX(headPositionX);
                        snakePointsList.get(0).setPositionY(headPositionY + doubleit(pointSize));
                        break;

                    //Moving Left
                    case 3:

                        // move snake's head to left
                        // other points follow snake's head point to move the snake
                        snakePointsList.get(0).setPositionX(headPositionX - doubleit(pointSize));
                        snakePointsList.get(0).setPositionY(headPositionY);
                        break;



                }

                // check if game over. If snake touches edge, itself, or an obstacle
                if(checkGameOver(headPositionX, headPositionY)){

                    GOSound.start();
                    //stop timer / stop moving snake
                    timer.purge();
                    timer.cancel();

                    // show game over dialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainGameScreen.this);
                    // {Leo, 26APR} - Using new Score function to show score
                    builder.setMessage("Your score = " + Score("Show"));
                    builder.setTitle("Game Over");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                        /**
                         * Sets a positive button on the dialog with the label "Play Again".
                         * When clicked, it restarts the game and re-initializes the game data.
                         * @param dialog The dialog interface that the button belongs to
                         * @param which An integer indicating which button was clicked
                         */
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            //restart game/ re-init data
                            init();
                        }
                    });
                    builder.setNegativeButton("Return to Main Menu", new DialogInterface.OnClickListener() {
                        /**
                         * Sets a negative button with a "Return to Main Menu" label and an onClickListener that
                         * handles the action of opening the main menu.
                         * @param dialog The dialog interface that the button belongs to
                         * @param which An integer indicating which button was clicked
                         */
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            //restart game/ re-init data
                            openMainMenu();
                        }
                    });
                    highScoreAttempt = Score("Show");
                    // timer runs in background so we need to show dialog on main thread
                    runOnUiThread(new Runnable() {
                        /**
                         * Displays the dialog using the AlertDialog.Builder object previously set up.
                         */
                        @Override
                        public void run() {
                            builder.show();
                        }
                    });

                }

                else{
                    // lock canvas onto surfaceHolder to draw it
                    canvas = surfaceHolder.lockCanvas();
                    if (canvas != null) {
                    // Clear canvas with white
                    canvas.drawColor(Color.WHITE, PorterDuff.Mode.CLEAR);
                    // {Leo, 26APR} - Changed background colour from black to light blue
                    canvas.drawColor(0xff4cb9e0);

                    // change snake's head position, other snake points will follow snake's head
                    // {Leo, 26APR} - Changed the shape of the snake's head to a square instead of a circle
                    canvas.drawRect(snakePointsList.get(0).getPositionX() - pointSize, snakePointsList.get(0).getPositionY() - pointSize,snakePointsList.get(0).getPositionX() + pointSize, snakePointsList.get(0).getPositionY() + pointSize, createPaintSnakeHeadColor() );

                    // draw random point circles on the surface to be eaten by snake
                    canvas.drawRect(positionX - pointSize, positionY - pointSize, positionX + pointSize, positionY + pointSize, createPaintAppleColor());

                    // draw obstacles
                    for(int i=0; i<numObstacles; i++){
                        canvas.drawRect(obstacleX[i] - pointSize, obstacleY[i] - pointSize, obstacleX[i] + pointSize, obstacleY[i] + pointSize, createPaintObstacleColor());
                    }

                    //other points follow snake head. 0 Is snake head
                    for(int i = 1; i < snakePointsList.size(); i++){

                        int getTempPositionX = snakePointsList.get(i).getPositionX();
                        int getTempPositionY = snakePointsList.get(i).getPositionY();

                        // move points across the head
                        snakePointsList.get(i).setPositionX(headPositionX);
                        snakePointsList.get(i).setPositionY(headPositionY);
                        canvas.drawRect(snakePointsList.get(i).getPositionX() - pointSize, snakePointsList.get(i).getPositionY() - pointSize,snakePointsList.get(i).getPositionX() + pointSize, snakePointsList.get(i).getPositionY() + pointSize, createPaintSnakeBodyColor() );

                        // change head position
                        headPositionX = getTempPositionX;
                        headPositionY = getTempPositionY;
                    }

                    // unlock canvas to draw on surfaceview
                    surfaceHolder.unlockCanvasAndPost(canvas);
                    Score("AddTime");
                    runOnUiThread(new Runnable() {
                        /**
                         * Updates the score displayed on the screen by setting the text of the score TextView to the current score.
                         */
                        @Override
                        public void run() {
                            scoreTV.setText(String.valueOf(Score("Show")));
                        }
                    });
                    }
                }
            }
        }, 1000 - snakeMovingSpeed, 1000 -  snakeMovingSpeed);
    }

    /**
     * Increases the length of the snake by one by adding a new point to the end of the snake's body.
     */
    private void growSnake(){

        //Play chomping noise when eating apple
        chomp.start();

        // create new snake point
        SnakePoints snakePoints = new SnakePoints(0,0);

        // add point ot snake tail
        snakePointsList.add(snakePoints);

        Score("AddApple");
        //setting score to TextView
        runOnUiThread(new Runnable() {
            /**
             * Updates the score displayed on the screen by setting the text of the score TextView to the current score.
             */
            @Override
            public void run() {
                scoreTV.setText(String.valueOf(Score("Show")));
            }
        });
    }

    /**
     * Checks whether the game is over by determining if the snake has collided with a wall or with itself.
     * @param headPositionX the x-coordinate of the snake's head
     * @param headPositionY the y-coordinate of the snake's head
     * @return true if the game is over, false otherwise
     */
    public boolean checkGameOver(int headPositionX, int headPositionY){
        boolean gameOver = false;

        // check if snake's head touches edges
        if(snakePointsList.get(0).getPositionX() < 0 ||
                snakePointsList.get(0).getPositionY() < 0 ||
                snakePointsList.get(0).getPositionX() >= surfaceView.getWidth() ||
                snakePointsList.get(0).getPositionY() >= surfaceView.getHeight()){
            gameOver = true;
        }
        else{

            // check if snake's head touches itself
            for(int i = 0; i < snakePointsList.size(); i++){

                if(headPositionX == snakePointsList.get(i).getPositionX() &&
                        headPositionY == snakePointsList.get(i).getPositionY()){
                    gameOver = true;
                    break;
                }
            }

            // check if snake's head touches an obstacle
            for(int i = 0; i < numObstacles; i++){

                if(headPositionX == obstacleX[i] && headPositionY == obstacleY[i]){
                    gameOver = true;
                    break;
                }
            }
        }
        Score("Add");
        return gameOver;

    }

    /**
     * Creates and returns a Paint object for the color of the snake's head.
     * @return the Paint object for the color of the snake's head.
     */
    public Paint createPaintSnakeHeadColor(){

        // check if color isn't defined
        if(pointSnakeHeadColor == null){
            pointSnakeHeadColor = new Paint();
            pointSnakeHeadColor.setColor(snakeHeadColor);
            pointSnakeHeadColor.setStyle(Paint.Style.FILL);
            pointSnakeHeadColor.setAntiAlias(true); // smoothness
        }

        return pointSnakeHeadColor;
    }

    /**
     * Creates and returns a Paint object for the color of the apple.
     * @return the Paint object for the color of the apple.
     */
    public Paint createPaintAppleColor(){

        // check if color isn't defined
        if(pointAppleColor == null){
            pointAppleColor = new Paint();
            pointAppleColor.setColor(appleColor);
            pointAppleColor.setStyle(Paint.Style.FILL);
            pointAppleColor.setAntiAlias(true); // smoothness
        }

        return pointAppleColor;
    }

    /**
     * Creates and returns a Paint object for the color of the obstacle.
     * @return the Paint object for the color of the obstacle.
     */
    public Paint createPaintObstacleColor(){

        // check if color isn't defined
        if(pointObstacleColor == null){
            pointObstacleColor = new Paint();
            pointObstacleColor.setColor(obstacleColor);
            pointObstacleColor.setStyle(Paint.Style.FILL);
            pointObstacleColor.setAntiAlias(true); // smoothness
        }

        return pointObstacleColor;
    }

    /**
     * Creates and returns a Paint object for the color of the snake's body.
     * @return the Paint object for the color of the snake's body.
     */
    public Paint createPaintSnakeBodyColor(){

        // check if color isn't defined
        if(pointSnakeBodyColor == null){
            pointSnakeBodyColor = new Paint();
            pointSnakeBodyColor.setColor(snakeBodyColor);
            pointSnakeBodyColor.setStyle(Paint.Style.FILL);
            pointSnakeBodyColor.setAntiAlias(true); // smoothness
        }

        return pointSnakeBodyColor;
    }

    /**
     * Method to open the main menu activity, which allows the user to select a new game mode or view high scores.
     */
    public void openMainMenu(){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    /**
     * Calculates the score based on the current game state and returns it as an integer.
     * This method calls a native function that takes a string argument and returns an integer score.
     * @param jString the string representation of the current game state.
     * @return the integer score calculated by the native function.
     */
    private native int Score(String jString);

    private native int doubleit(int jint);

    private native int rightClick(int jint);

    private native int leftclick(int jint);

    private native int setX(int jint1, int jint2);

    private native int setY(int jint1, int jint2);

}
