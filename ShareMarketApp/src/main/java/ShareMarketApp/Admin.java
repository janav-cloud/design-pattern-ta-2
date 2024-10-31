import java.util.List;

class Admin {
    public void addStock(Market market, Stock stock) {
        market.addStock(stock);
        System.out.println("Admin added stock: " + stock.getName());
    }

    public void updateStockPrice(Market market, String stockName, double newPrice) {
        Stock stockToUpdate = findStock(market, stockName);
        if (stockToUpdate != null) {
            stockToUpdate.setPrice(newPrice);
            System.out.println("Admin updated price of " + stockToUpdate.getName() + " to $" + newPrice);
        } else {
            System.out.println("Stock not found!");
        }
    }

    public void removeStock(Market market, String stockName) {
        Stock stockToRemove = findStock(market, stockName);
        if (stockToRemove != null) {
            market.getStocks().remove(stockToRemove);
            System.out.println("Admin removed stock: " + stockToRemove.getName());
        } else {
            System.out.println("Stock not found!");
        }
    }

    public void viewAllStocks(Market market) {
        List<Stock> stocks = market.getStocks();
        if (stocks.isEmpty()) {
            System.out.println("No stocks available.");
        } else {
            System.out.println("--- Current Stock Listings ---");
            for (Stock stock : stocks) {
                System.out.println(stock.getName() + ": $" + stock.getPrice());
            }
        }
    }

    private Stock findStock(Market market, String stockName) {
        for (Stock stock : market.getStocks()) {
            if (stock.getName().equalsIgnoreCase(stockName)) {
                return stock;
            }
        }
        return null;
    }
}