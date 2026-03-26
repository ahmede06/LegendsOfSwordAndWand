package auth;

import database.DatabaseManager;

public class AuthManager {

    private User currentUser;

    // register new user
    public boolean register(String username, String password){

        DatabaseManager db = DatabaseManager.getInstance();

        if(db.userExists(username))
            return false;

        currentUser = new User(username,password);

        db.saveUser(currentUser);

        return true;
    }

    // login existing user
    public boolean login(String username, String password){

        DatabaseManager db = DatabaseManager.getInstance();

        User user = db.loadUser(username);

        if(user == null)
            return false;

        if(!user.getPassword().equals(password))
            return false;

        currentUser = user;

        return true;
    }

    public User getCurrentUser(){
        return currentUser;
    }
}