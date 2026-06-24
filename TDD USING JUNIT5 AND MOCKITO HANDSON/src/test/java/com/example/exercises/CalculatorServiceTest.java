package com.example.exercises;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorServiceTest {
    private final CalculatorService calculatorService = new CalculatorService();
    @Test
    public void testAdd() {
        assertEquals(12, calculatorService.add(5, 7));
    }
    @ParameterizedTest
    @CsvSource({"1,2,3", "5,5,10", "0,0,0", "-2,3,1"})
    public void testAdd_WithMultipleInputs(int a, int b, int expectedResult) {
        assertEquals(expectedResult, calculatorService.add(a, b));
    }
}
