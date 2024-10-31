public class LoginService {
    private DatabaseManager dbManager;

    public LoginService() {
        dbManager = new DatabaseManager();
    }

    public boolean authenticate(String username, String password) {
        return dbManager.authenticateUser(username, password);
    }

    public void addUser(String username, String password) {
        dbManager.addUser(username, password);
    }
}
