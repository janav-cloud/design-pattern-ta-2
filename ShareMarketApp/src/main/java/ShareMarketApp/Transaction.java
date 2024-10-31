class Transaction {
    private String stockName;
    private double amount;
    private String type; // Buy or Sell

    public Transaction(String stockName, double amount, String type) {
        this.stockName = stockName;
        this.amount = amount;
        this.type = type;
    }

    public String getDetails() {
        return type + " " + amount + " of " + stockName;
    }
}