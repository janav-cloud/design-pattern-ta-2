class ConservativeStrategy implements BuyingStrategy {
    @Override
    public void buy(Stock stock, double amount) {
        System.out.println("Conservatively buying " + amount + " of " + stock.getName());
    }
}
