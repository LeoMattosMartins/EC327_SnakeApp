package com.example.snakescreenprototype;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import android.view.View;
import android.widget.Button;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
@RunWith(AndroidJUnit4.class)

public class SettingsTest {
    
    /*
    Rule,Before,Activity creates an activity that is able to run the app.
    Most of the code for each class is android testing so there is not a lot of opportunity
    to unit test. 
    
    SettingsTest will be testing each button in the settings page.
    It will make sure the colors(purple,red,yellow) the control setup(screen,buttons),
    and the speed settings(slow,med,fast) are all responsive and functional.
    
    -Koen
    */
    
    @Rule
    public ActivityTestRule<Settings> settingActivityRule = new ActivityTestRule(Settings.class);

    private Settings settingActivity = null;

    @Before
    public void setUp() throws Exception {
        settingActivity = settingActivityRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {
        settingActivity = null;
    }

    @Test
    public void testColorSettingPurple(){
        Button PurpleButton = null;
        PurpleButton = (Button) settingActivity.findViewById(R.id.rbPurple);
        View.OnClickListener okButtonListener = spy(new SettingsTest.MockOnClickOkListener());
        PurpleButton.setOnClickListener(okButtonListener);


        onView(withId(R.id.rbPurple)).perform(click());
        verify(okButtonListener, times(1)).onClick(PurpleButton);
    }

    @Test
    public void testColorSettingYellow(){
        Button YellowButton = null;
        YellowButton = (Button) settingActivity.findViewById(R.id.rbYellow);
        View.OnClickListener okButtonListener = spy(new SettingsTest.MockOnClickOkListener());
        YellowButton.setOnClickListener(okButtonListener);


        onView(withId(R.id.rbYellow)).perform(click());
        verify(okButtonListener, times(1)).onClick(YellowButton);
    }

    @Test
    public void testColorSettingRed(){
        Button RedButton = null;
        RedButton = (Button) settingActivity.findViewById(R.id.rbRed);
        View.OnClickListener okButtonListener = spy(new SettingsTest.MockOnClickOkListener());
        RedButton.setOnClickListener(okButtonListener);


        onView(withId(R.id.rbRed)).perform(click());
        verify(okButtonListener, times(1)).onClick(RedButton);
    }

    @Test
    public void testArrowSetup(){
        Button arrowButton = null;
        arrowButton = (Button) settingActivity.findViewById(R.id.ArrowsBT);
        View.OnClickListener okButtonListener = spy(new SettingsTest.MockOnClickOkListener());
        arrowButton.setOnClickListener(okButtonListener);


        onView(withId(R.id.ArrowsBT)).perform(click());
        verify(okButtonListener, times(1)).onClick(arrowButton);
    }

    @Test
    public void testFullScreenButtonSetup(){
        Button screenButton = null;
        screenButton = (Button) settingActivity.findViewById(R.id.FullScreenBT);
        View.OnClickListener okButtonListener = spy(new SettingsTest.MockOnClickOkListener());
        screenButton.setOnClickListener(okButtonListener);


        onView(withId(R.id.FullScreenBT)).perform(click());
        verify(okButtonListener, times(1)).onClick(screenButton);
    }


    @Test
    public void slowSpeedSetting(){
        Button slowButton = null;
        slowButton = (Button) settingActivity.findViewById(R.id.rbSlow);
        View.OnClickListener okButtonListener = spy(new SettingsTest.MockOnClickOkListener());
        slowButton.setOnClickListener(okButtonListener);


        onView(withId(R.id.rbSlow)).perform(click());
        verify(okButtonListener, times(1)).onClick(slowButton);
    }

    @Test
    public void mediumSpeedSetting(){
        Button mediumButton = null;
        mediumButton = (Button) settingActivity.findViewById(R.id.rbMedium);
        View.OnClickListener okButtonListener = spy(new SettingsTest.MockOnClickOkListener());
        mediumButton.setOnClickListener(okButtonListener);


        onView(withId(R.id.rbMedium)).perform(click());
        verify(okButtonListener, times(1)).onClick(mediumButton);
    }

    @Test
    public void fastSpeedSetting(){
        Button fastButton = null;
        fastButton = (Button) settingActivity.findViewById(R.id.rbFast);
        View.OnClickListener okButtonListener = spy(new SettingsTest.MockOnClickOkListener());
        fastButton.setOnClickListener(okButtonListener);


        onView(withId(R.id.rbFast)).perform(click());
        verify(okButtonListener, times(1)).onClick(fastButton);
    }
    protected class MockOnClickOkListener implements View.OnClickListener {

        public void onClick(View v) {
//            String country = mEditText.getText().toString();
//            if (!showPicture(country)) {
//                mWarningTextView.setVisibility(View.VISIBLE);
//            } else if (View.VISIBLE == mWarningTextView.getVisibility()) {
//                mWarningTextView.setVisibility(View.INVISIBLE);
//            }
        }
    }
}
