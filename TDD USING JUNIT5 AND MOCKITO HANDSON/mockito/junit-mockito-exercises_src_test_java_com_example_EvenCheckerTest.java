package com.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

public class EvenCheckerTest {
    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 10, -2, 0})
    public void testIsEvenWithEvenNumbers(int number) {
        EvenChecker checker = new EvenChecker();
        assertTrue(checker.isEven(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 9, -1})
    public void testIsEvenWithOddNumbers(int number) {
        EvenChecker checker = new EvenChecker();
        assertFalse(checker.isEven(number));
    }
}