package com.example.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppenderLoggingExample {
    private static final Logger logger = LoggerFactory.getLogger(AppenderLoggingExample.class);

    public static void main(String[] args) {
        logger.info("Application started successfully.");
        logger.debug("Debugging application state with appenders configuration...");
        logger.warn("Simulating a non-critical component warning.");
        logger.error("Simulating a high-priority system error log exception.");
    }
}
