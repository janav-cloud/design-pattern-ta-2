import java.util.ArrayList;
import java.util.List;

class Market {
    private static Market instance;
    private List<Stock> stocks;

    private Market() {
        stocks = new ArrayList<>();
    }

    public static Market getInstance() {
        if (instance == null) {
            instance = new Market();
        }
        return instance;
    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void displayStocks() {
        System.out.println("--- Current Stock Listings ---");
        for (Stock stock : stocks) {
            System.out.println(stock.getName() + ": $" + stock.getPrice());
        }
    }
}
