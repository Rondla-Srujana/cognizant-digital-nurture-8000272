package com.example.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParameterizedLogging {
    private static final Logger logger = LoggerFactory.getLogger(ParameterizedLogging.class);

    public static void main(String[] args) {
        String username = "JohnDoe";
        int loginAttempts = 3;

        // Efficient parameterized logging without manual string concatenation
        logger.info("User '{}' attempted to log in. Total attempts: {}", username, loginAttempts);
        
        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            logger.error("An error occurred during math operations for user {}: ", username, e);
        }
    }
}
