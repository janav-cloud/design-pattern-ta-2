import java.sql.*;

public class DatabaseManager {
    private static final String DATABASE_URL = "jdbc:sqlite:share_market.db";

    public DatabaseManager() {
        createTables();
        populateAdminUser();
        populateUsers();
    }

    private void createTables() {
        String createUsersTable = "CREATE TABLE IF NOT EXISTS users ("
                + "username TEXT PRIMARY KEY,"
                + "password TEXT NOT NULL"
                + ");";

        String createStocksTable = "CREATE TABLE IF NOT EXISTS stocks ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name TEXT NOT NULL,"
                + "price REAL NOT NULL"
                + ");";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createUsersTable);
            stmt.execute(createStocksTable);
            System.out.println("Tables created successfully (if they didn't exist).");
        } catch (SQLException e) {
            System.out.println("Error creating tables: " + e.getMessage());
        }
    }

    private void populateAdminUser() {
        if (!userExists("admin")) {
            addUser("admin", "123");
            System.out.println("Admin user added successfully.");
        } else {
            System.out.println("Admin user already exists.");
        }
    }

    private void populateUsers() {
        addUser("Alice", "abc");   
        addUser("Bob", "def");     
        addUser("Charlie", "ghi"); 
        addUser("David", "jkl");   
    }

    // Check if a user exists
    private boolean userExists(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Error checking user existence: " + e.getMessage());
            return false;
        }
    }

    public void addUser(String username, String password) {
        String sql = "INSERT INTO users(username, password) VALUES(?, ?)";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            System.out.println("User added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding user: " + e.getMessage());
        }
    }

    public boolean authenticateUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Error authenticating user: " + e.getMessage());
            return false;
        }
    }

    public void addStock(String name, double price) {
        String sql = "INSERT INTO stocks(name, price) VALUES(?, ?)";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.executeUpdate();
            System.out.println("Stock added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding stock: " + e.getMessage());
        }
    }

    public void updateStockPrice(String name, double price) {
        String sql = "UPDATE stocks SET price = ? WHERE name = ?";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, price);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
            System.out.println("Stock price updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating stock price: " + e.getMessage());
        }
    }

    public void removeStock(String name) {
        String sql = "DELETE FROM stocks WHERE name = ?";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            System.out.println("Stock removed successfully.");
        } catch (SQLException e) {
            System.out.println("Error removing stock: " + e.getMessage());
        }
    }

    public void displayStocks() {
        String sql = "SELECT * FROM stocks";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("Stock ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") + ", Price: " + rs.getDouble("price"));
            }
        } catch (SQLException e) {
            System.out.println("Error displaying stocks: " + e.getMessage());
        }
    }

    public Stock getStockByName(String name) {
        String sql = "SELECT * FROM stocks WHERE name = ?";
        Stock stock = null;

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                stock = new Stock(rs.getString("name"), rs.getDouble("price"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving stock: " + e.getMessage());
        }

        return stock;
    }
}
