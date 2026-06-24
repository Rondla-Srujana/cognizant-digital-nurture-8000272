package com.example;

import org.junit.jupiter.api.Test;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

public class PerformanceTesterTest {
    @Test
    public void testPerformTaskTimeout() {
        PerformanceTester tester = new PerformanceTester();
        assertTimeout(Duration.ofMillis(200), () -> {
            tester.performTask();
        });
    }
}