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
public class MainMenuTest { //extends Activity
    /*
    Rule,Before,Activity creates an activity that is able to run the app.
    Most of the code for each class is android testing so there is not a lot of opportunity
    to unit test. 
    
    MainMenuTest will be testing the launch of the game, the startbutton as well as the settings button.
    
    -Koen
    */
    
    @Rule
    public ActivityTestRule<MainMenu> mainActivityRule = new ActivityTestRule(MainMenu.class);

    private MainMenu mainActivity = null;

    @Before
    public void setUp() throws Exception {
        mainActivity = mainActivityRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }

    @Test
    public void testLaunch() {
        View startButton = mainActivity.findViewById(R.id.btnStartGame);
        View settingButton = mainActivity.findViewById(R.id.btnSetting);
        assertNotNull(startButton);
        assertNotNull(settingButton);
    }

    @Test
    public void testStartButtonActivity() {
        Button startButton = null;
        startButton = (Button) mainActivity.findViewById(R.id.btnStartGame);
        View.OnClickListener okButtonListener = spy(new MockOnClickOkListener());
        startButton.setOnClickListener(okButtonListener);

        onView(withId(R.id.btnStartGame)).perform(click());
        verify(okButtonListener, times(1)).onClick(startButton);
    }

    @Test
    public void testSettingButtonActivity() {
        Button settingButton = null;
        settingButton = (Button) mainActivity.findViewById(R.id.btnSetting);
        View.OnClickListener okButtonListener = spy(new MockOnClickOkListener());
        settingButton.setOnClickListener(okButtonListener);

        onView(withId(R.id.btnSetting)).perform(click());
        verify(okButtonListener, times(1)).onClick(settingButton);
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
