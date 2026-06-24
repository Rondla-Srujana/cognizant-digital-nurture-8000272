// ============================================================
// Exercise 1: Singleton Pattern
// File: Ex1_Singleton_Logger.java
// ============================================================

class Logger {
    private static Logger instance;

    private Logger() {
        System.out.println("Logger instance created.");
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("[LOG]: " + message);
    }
}

public class Ex1_Singleton_Logger {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        logger1.log("Application started.");
        logger2.log("User logged in.");

        System.out.println("Same instance? " + (logger1 == logger2)); // true
    }
}
