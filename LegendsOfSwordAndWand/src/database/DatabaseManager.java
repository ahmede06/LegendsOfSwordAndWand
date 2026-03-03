package database;

import auth.User;
import java.util.HashMap;
import java.util.Map;

public class DatabaseManager {
    private static DatabaseManager instance = null;
    private Map<String, User> users;

    private DatabaseManager() {
        users = new HashMap<>();
    }

    public static DatabaseManager getInstance() {
        if(instance == null) instance = new DatabaseManager();
        return instance;
    }

    public void saveUser(User u) { users.put(u.getUsername(), u); }
    public User loadUser(String username) { return users.get(username); }
    public boolean userExists(String username) { return users.containsKey(username); }
}