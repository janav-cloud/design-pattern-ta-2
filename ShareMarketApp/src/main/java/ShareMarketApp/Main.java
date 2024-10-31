import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Market market = Market.getInstance();
        Scanner scanner = new Scanner(System.in);
        List<Investor> investors = new ArrayList<>();
        Admin admin = new Admin();
        LoginService loginService = new LoginService();
        DatabaseManager dbManager = new DatabaseManager(); // Initialize DB Manager

        while (true) {
            System.out.println("\n--- Share Market App ---");
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            // Authenticate user
            if (loginService.authenticate(username, password)) {
                if (username.equals("admin")) {
                    adminMenu(scanner, admin, market, dbManager);
                } else {
                    Investor investor = findInvestor(investors, username);
                    if (investor == null) {
                        investor = new Investor(username);
                        investors.add(investor);
                    }
                    investorMenu(scanner, investor, market);
                }
            } else {
                System.out.println("Invalid username or password. Please try again.");
            }
        }
    }

    private static void investorMenu(Scanner scanner, Investor investor, Market market) {
        while (true) {
            System.out.println("\n--- Investor Menu ---");
            System.out.println("1. View Portfolio");
            System.out.println("2. View Current Stock Listings");
            System.out.println("3. Buy Stock");
            System.out.println("4. Sell Stock");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    investor.viewPortfolio();
                    break;

                case 2:
                    market.displayStocks();
                    break;

                case 3:
                    buyStock(scanner, investor, market);
                    break;

                case 4:
                    sellStock(scanner, investor);
                    break;

                case 5:
                    System.out.println("Logging out...");
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void adminMenu(Scanner scanner, Admin admin, Market market, DatabaseManager dbManager) {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add Stock");
            System.out.println("2. Update Stock Price");
            System.out.println("3. Remove Stock");
            System.out.println("4. View All Stocks");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter stock name: ");
                    String stockName = scanner.nextLine();
                    System.out.print("Enter initial price: ");
                    double initialPrice = scanner.nextDouble();
                    dbManager.addStock(stockName, initialPrice); // Save to DB
                    break;

                case 2:
                    System.out.print("Enter stock name to update: ");
                    stockName = scanner.nextLine();
                    System.out.print("Enter new price: ");
                    double newPrice = scanner.nextDouble();
                    dbManager.updateStockPrice(stockName, newPrice); // Update in DB
                    break;

                case 3:
                    System.out.print("Enter stock name to remove: ");
                    stockName = scanner.nextLine();
                    dbManager.removeStock(stockName); // Remove from DB
                    break;

                case 4:
                    dbManager.displayStocks(); // Show stocks from DB
                    break;

                case 5:
                    System.out.println("Logging out...");
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void buyStock(Scanner scanner, Investor investor, Market market) {
        System.out.print("Enter stock name to buy: ");
        String stockName = scanner.nextLine();
        Stock stockToBuy = findStock(market, stockName);
        if (stockToBuy != null) {
            System.out.print("Enter amount to buy: ");
            double amount = scanner.nextDouble();
            BuyingStrategy strategy = selectStrategy(scanner);
            investor.buyStock(stockToBuy, amount, strategy);
        } else {
            System.out.println("Stock not found!");
        }
    }

    private static void sellStock(Scanner scanner, Investor investor) {
        System.out.print("Enter stock name to sell: ");
        String stockName = scanner.nextLine();
        Stock stockToSell = investor.getStock(stockName);
        if (stockToSell != null) {
            System.out.print("Enter amount to sell: ");
            double amount = scanner.nextDouble();
            investor.sellStock(stockToSell, amount);
        } else {
            System.out.println("You don't own this stock!");
        }
    }

    private static Stock findStock(Market market, String stockName) {
        for (Stock stock : market.getStocks()) {
            if (stock.getName().equalsIgnoreCase(stockName)) {
                return stock;
            }
        }
        return null;
    }

    private static Investor findInvestor(List<Investor> investors, String name) {
        for (Investor investor : investors) {
            if (investor.getName().equalsIgnoreCase(name)) {
                return investor;
            }
        }
        return null;
    }

    private static BuyingStrategy selectStrategy(Scanner scanner) {
        System.out.println("Select buying strategy:");
        System.out.println("1. Aggressive");
        System.out.println("2. Conservative");
        int strategyChoice = scanner.nextInt();
        switch (strategyChoice) {
            case 1:
                return new AggressiveStrategy();
            case 2:
                return new ConservativeStrategy();
            default:
                System.out.println("Invalid choice. Defaulting to Conservative strategy.");
                return new ConservativeStrategy();
        }
    }
}