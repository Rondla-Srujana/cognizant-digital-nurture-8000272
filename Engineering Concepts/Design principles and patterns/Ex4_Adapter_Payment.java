// ============================================================
// Exercise 4: Adapter Pattern
// File: Ex4_Adapter_Payment.java
// ============================================================

// Target interface
interface PaymentProcessor {
    void processPayment(double amount);
}

// Adaptee 1 – PayPal
class PayPalGateway {
    public void makePayPalPayment(double amount) {
        System.out.println("PayPal payment of $" + amount + " processed.");
    }
}

// Adaptee 2 – Stripe
class StripeGateway {
    public void chargeStripe(double amount) {
        System.out.println("Stripe charge of $" + amount + " processed.");
    }
}

// Adapter for PayPal
class PayPalAdapter implements PaymentProcessor {
    private PayPalGateway payPal;
    PayPalAdapter(PayPalGateway payPal) { this.payPal = payPal; }
    public void processPayment(double amount) { payPal.makePayPalPayment(amount); }
}

// Adapter for Stripe
class StripeAdapter implements PaymentProcessor {
    private StripeGateway stripe;
    StripeAdapter(StripeGateway stripe) { this.stripe = stripe; }
    public void processPayment(double amount) { stripe.chargeStripe(amount); }
}

public class Ex4_Adapter_Payment {
    public static void main(String[] args) {
        PaymentProcessor paypal = new PayPalAdapter(new PayPalGateway());
        PaymentProcessor stripe = new StripeAdapter(new StripeGateway());

        paypal.processPayment(150.00);
        stripe.processPayment(200.00);
    }
}
