package auth;

import database.DatabaseManager;

public class AuthManager {
    private User currentUser;

    public boolean register(String username, String password) {
        DatabaseManager db = DatabaseManager.getInstance();
        if(db.userExists(username)) return false;
        currentUser = new User(username, password);
        db.saveUser(currentUser);
        return true;
    }

    public boolean login(String username, String password) {
        DatabaseManager db = DatabaseManager.getInstance();
        User u = db.loadUser(username);
        if(u != null && u.getPassword().equals(password)) {
            currentUser = u;
            return true;
        }
        return false;
    }

    public User getCurrentUser() { return currentUser; }
}