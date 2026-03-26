

import auth.AuthManager;
import ui.MainUI;

public class Main {
    public static void main(String[] args) {
        AuthManager auth = new AuthManager();
        MainUI ui = new MainUI(auth);
        ui.showMainMenu();
    }
}