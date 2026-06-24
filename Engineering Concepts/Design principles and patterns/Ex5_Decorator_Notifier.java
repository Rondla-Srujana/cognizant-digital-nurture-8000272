// ============================================================
// Exercise 5: Decorator Pattern
// File: Ex5_Decorator_Notifier.java
// ============================================================

interface Notifier {
    void send(String message);
}

class EmailNotifier implements Notifier {
    public void send(String message) {
        System.out.println("Email notification: " + message);
    }
}

abstract class NotifierDecorator implements Notifier {
    protected Notifier wrappedNotifier;
    NotifierDecorator(Notifier notifier) { this.wrappedNotifier = notifier; }
    public void send(String message)     { wrappedNotifier.send(message); }
}

class SMSNotifierDecorator extends NotifierDecorator {
    SMSNotifierDecorator(Notifier notifier) { super(notifier); }
    public void send(String message) {
        super.send(message);
        System.out.println("SMS notification: " + message);
    }
}

class SlackNotifierDecorator extends NotifierDecorator {
    SlackNotifierDecorator(Notifier notifier) { super(notifier); }
    public void send(String message) {
        super.send(message);
        System.out.println("Slack notification: " + message);
    }
}

public class Ex5_Decorator_Notifier {
    public static void main(String[] args) {
        Notifier notifier = new SlackNotifierDecorator(
                            new SMSNotifierDecorator(
                            new EmailNotifier()));

        notifier.send("Server is down!");
    }
}
