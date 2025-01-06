package com.example.snakescreenprototype;

import static org.junit.Assert.assertEquals;

import android.graphics.Paint;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class MainGameScreenColorsTest {

    @Rule
    public ActivityTestRule<MainGameScreen> mActivityRule = new ActivityTestRule(MainGameScreen.class);


    @Test
    public void paintSnakeHeadTest(){
       MainGameScreen activity = mActivityRule.getActivity();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Paint p1 = activity.createPaintSnakeHeadColor();
                assertEquals(MainGameScreen.snakeHeadColor,p1.getColor());
                assertEquals(Paint.Style.FILL,p1.getStyle());
                assertEquals(true,p1.isAntiAlias());
                activity.timer.cancel();
            }
        });

    }

    @Test
    public void paintSnakeBodyTest(){
       MainGameScreen activity = mActivityRule.getActivity();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Paint p2 = activity.createPaintSnakeBodyColor();
                assertEquals(MainGameScreen.snakeBodyColor,p2.getColor());
                assertEquals(Paint.Style.FILL,p2.getStyle());
                assertEquals(true,p2.isAntiAlias());
                activity.timer.cancel();
            }
        });
    }

    @Test
    public void paintAppleTest(){
        MainGameScreen activity = mActivityRule.getActivity();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Paint p3 = activity.createPaintAppleColor();
                assertEquals(MainGameScreen.appleColor,p3.getColor());
                assertEquals(Paint.Style.FILL,p3.getStyle());
                assertEquals(true,p3.isAntiAlias());
                activity.timer.cancel();
            }
        });
    }

    @Test
    public void paintObstacleTest(){
       MainGameScreen activity = mActivityRule.getActivity();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Paint p4 = activity.createPaintObstacleColor();
                assertEquals(MainGameScreen.obstacleColor,p4.getColor());
                assertEquals(Paint.Style.FILL,p4.getStyle());
                assertEquals(true,p4.isAntiAlias());
                activity.timer.cancel();
            }
        });
    }
}

