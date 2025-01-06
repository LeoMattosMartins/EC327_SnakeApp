package com.example.snakescreenprototype;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import android.view.View;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MainGameScreenTest { //extends Activity {
    @Rule
    public ActivityTestRule<MainGameScreen> mActivityRule = new ActivityTestRule(MainGameScreen.class);

    @Test
    public void test_checkGameOver() {

        MainGameScreen activity = mActivityRule.getActivity();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                boolean result = activity.checkGameOver(10,10);
                assertFalse (result);
                activity.timer.cancel();
            }
        });

    }

    @Test
    public void test_addPoint_must_be_even() {

        MainGameScreen activity = mActivityRule.getActivity();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                System.out.print("test====" + activity.positionX);
                activity.addPoint();

                assertTrue (activity.positionX % 2 == 0);
                activity.timer.cancel();
            }
        });

    }

    @Test
    public void testLaunch() {
        MainGameScreen activity = mActivityRule.getActivity();
        View surfaceView = activity.findViewById(R.id.surfaceView);
        View scoreView = activity.findViewById(R.id.scoreTV);
        assertNotNull(surfaceView);
        assertNotNull(scoreView);
        activity.timer.cancel();
    }


}