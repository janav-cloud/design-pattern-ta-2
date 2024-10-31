import java.util.HashMap;
import java.util.Map;

class Investor implements Observer {
    private String name;
    private Map<Stock, Double> portfolio = new HashMap<>();

    public Investor(String name) {
        this.name = name;
    }

    @Override
    public void update(String stockName, double price) {
        System.out.println("Investor " + name + " notified. " + stockName + " new price: " + price);
    }

    public String getName() {
        return name;
    }

    public void buyStock(Stock stock, double amount, BuyingStrategy strategy) {
        strategy.buy(stock, amount);
        portfolio.put(stock, portfolio.getOrDefault(stock, 0.0) + amount);
        System.out.println(name + " bought " + amount + " of " + stock.getName());
    }

    public void sellStock(Stock stock, double amount) {
        if (portfolio.containsKey(stock) && portfolio.get(stock) >= amount) {
            portfolio.put(stock, portfolio.get(stock) - amount);
            System.out.println(name + " sold " + amount + " of " + stock.getName());
        } else {
            System.out.println("Not enough stock to sell.");
        }
    }

    public Stock getStock(String stockName) {
        for (Stock stock : portfolio.keySet()) {
            if (stock.getName().equalsIgnoreCase(stockName)) {
                return stock;
            }
        }
        return null;
    }

    public void viewPortfolio() {
        System.out.println(name + "'s Portfolio:");
        for (Map.Entry<Stock, Double> entry : portfolio.entrySet()) {
            System.out.println(entry.getKey().getName() + ": " + entry.getValue());
        }
    }
}