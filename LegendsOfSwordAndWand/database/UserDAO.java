package database;

import auth.User;

public class UserDAO {

    private DatabaseManager db = DatabaseManager.getInstance();

    public void save(User user){
        db.saveUser(user);
    }

    public User find(String username){
        return db.loadUser(username);
    }

}