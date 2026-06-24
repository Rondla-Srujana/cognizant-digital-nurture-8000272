// ============================================================
// Exercise 8: Strategy Pattern
// File: Ex8_Strategy_Payment.java
// ============================================================

interface PaymentStrategy {
    void pay(double amount);
}

class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    CreditCardPayment(String cardNumber) { this.cardNumber = cardNumber; }
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using Credit Card ending in " +
                cardNumber.substring(cardNumber.length() - 4));
    }
}

class PayPalPayment implements PaymentStrategy {
    private String email;
    PayPalPayment(String email) { this.email = email; }
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " via PayPal account: " + email);
    }
}

class PaymentContext {
    private PaymentStrategy strategy;

    public void setStrategy(PaymentStrategy strategy) { this.strategy = strategy; }
    public void executePayment(double amount)          { strategy.pay(amount); }
}

public class Ex8_Strategy_Payment {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        context.setStrategy(new CreditCardPayment("1234567890123456"));
        context.executePayment(500.00);

        context.setStrategy(new PayPalPayment("user@example.com"));
        context.executePayment(250.00);
    }
}
