// ============================================================
// Exercise 7: Observer Pattern
// File: Ex7_Observer_StockMarket.java
// ============================================================

import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(String stockName, double price);
}

interface Stock {
    void registerObserver(Observer o);
    void deregisterObserver(Observer o);
    void notifyObservers();
}

class StockMarket implements Stock {
    private List<Observer> observers = new ArrayList<>();
    private String stockName;
    private double price;

    public void setPrice(String stockName, double price) {
        this.stockName = stockName;
        this.price = price;
        notifyObservers();
    }

    public void registerObserver(Observer o)   { observers.add(o); }
    public void deregisterObserver(Observer o) { observers.remove(o); }
    public void notifyObservers() {
        for (Observer o : observers) o.update(stockName, price);
    }
}

class MobileApp implements Observer {
    public void update(String stockName, double price) {
        System.out.println("[MobileApp] " + stockName + " -> $" + price);
    }
}

class WebApp implements Observer {
    public void update(String stockName, double price) {
        System.out.println("[WebApp]    " + stockName + " -> $" + price);
    }
}

public class Ex7_Observer_StockMarket {
    public static void main(String[] args) {
        StockMarket market = new StockMarket();

        Observer mobile = new MobileApp();
        Observer web    = new WebApp();

        market.registerObserver(mobile);
        market.registerObserver(web);

        market.setPrice("AAPL", 189.50);
        market.setPrice("GOOGL", 175.20);

        System.out.println("\nDeregistering WebApp...");
        market.deregisterObserver(web);
        market.setPrice("AAPL", 192.00);
    }
}
