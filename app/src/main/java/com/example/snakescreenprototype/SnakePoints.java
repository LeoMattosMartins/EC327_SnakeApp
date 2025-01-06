package com.example.snakescreenprototype;

/**
 * The SnakePoints class represents a point on the game board where the snake is located.
 * It contains the x and y coordinates of the point and the snake's location.
 */
public class SnakePoints {

    /**
     * The x and y coordinates of the snake.
     * These values are used to determine the position of the snake.
     */
    private  int positionX, positionY;

    /**
     * Constructs a new SnakePoints object with the given x and y coordinates.
     * @param positionX the x coordinate of the point
     * @param positionY the y coordinate of the point
     */
    public SnakePoints(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    /**
     * This method is used to get the x-coordinate of a SnakePoint.
     * @return An integer representing the x-coordinate of the SnakePoint.
     */
    public int getPositionX() {
        return positionX;
    }

    /**
     * This method sets the x-coordinate of the snake point.
     * @param positionX an integer representing the new x-coordinate of the snake point
     */
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    /**
     * This method is used to get the y-coordinate of a SnakePoint.
     * @return An integer representing the y-coordinate of the SnakePoint.
     */
    public int getPositionY() {
        return positionY;
    }

    /**
     * This method sets the y-coordinate of the snake point.
     * @param positionY an integer representing the new y-coordinate of the snake point
     */
    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

}
