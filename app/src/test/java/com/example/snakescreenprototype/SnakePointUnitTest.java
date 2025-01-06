package com.example.snakescreenprototype;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SnakePointUnitTest {
    @Test
    public void TestSnakePoint() {

        //SNAKEPOINT UNIT TEST
        /* Checks the SnakePoint Class and the variables in it
        -Koen Lin
        */

        SnakePoints s1 = new SnakePoints(5, 6);
        int xpos1 = s1.getPositionX();
        int ypos1 = s1.getPositionY();
        assertEquals(5, xpos1);
        assertEquals(6, ypos1);
        s1.setPositionX(12);
        s1.setPositionY(10);
        assertEquals(12, s1.getPositionX());
        assertEquals(10, s1.getPositionY());
    }
}

