package auth;

import java.util.ArrayList;
import java.util.List;
import model.Party;

public class User {
    private String username;
    private String password;
    private List<Party> parties;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.parties = new ArrayList<>();
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public List<Party> getParties() { return parties; }

    public void addParty(Party p) { parties.add(p); }
}