package com.example;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {
    private Calculator calculator;

    @Before
    public void setUp() {
        calculator = new Calculator();
    }

    @After
    public void tearDown() {
        calculator = null;
    }

    @Test
    public void testAddMethod() {
        int result = calculator.add(10, 20);
        assertEquals(30, result);
    }

    @Test
    public void testSubtractMethod() {
        int result = calculator.subtract(20, 5);
        assertEquals(15, result);
    }
}