package com.example.snakescreenprototype;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainMenuUnitTest {
    /**
     * Issue #4 Unit Test
     */
    @Test
    public void testAmIPositive(){

        assertTrue(MainMenu.amIPositive(4));
    }

}
