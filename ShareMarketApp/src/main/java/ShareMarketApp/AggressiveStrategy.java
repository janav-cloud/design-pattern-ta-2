class AggressiveStrategy implements BuyingStrategy {
    @Override
    public void buy(Stock stock, double amount) {
        System.out.println("Aggressively buying " + amount + " of " + stock.getName());
    }
}
