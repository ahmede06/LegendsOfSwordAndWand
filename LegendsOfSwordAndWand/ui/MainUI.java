package ui;

import auth.AuthManager;
import auth.User;
import java.util.Scanner;
import model.Hero;
import model.Party;
import pve.CampaignManager;

public class MainUI {
    private AuthManager auth;
    private Scanner sc;

    public MainUI(AuthManager auth) {
        this.auth = auth;
        sc = new Scanner(System.in);
    }

    public void showMainMenu() {
        while(true) {
            System.out.println("1. Register  2. Login  3. Exit");
            String choice = sc.nextLine();
            if(choice.equals("1")) register();
            else if(choice.equals("2")) login();
            else break;
        }
    }

    private void register() {
        System.out.print("Username: ");
        String u = sc.nextLine();
        System.out.print("Password: ");
        String p = sc.nextLine();
        if(auth.register(u,p)) System.out.println("Registered!");
        else System.out.println("Username taken.");
    }

    private void login() {
        System.out.print("Username: ");
        String u = sc.nextLine();
        System.out.print("Password: ");
        String p = sc.nextLine();
        if(auth.login(u,p)) {
            System.out.println("Logged in as " + u);
            showUserMenu(auth.getCurrentUser());
        } else System.out.println("Login failed.");
    }

    private void showUserMenu(User user) {
        while(true) {
            System.out.println("1. Start PvE  2. Logout");
            String choice = sc.nextLine();
            if(choice.equals("1")) startPvE(user);
            else break;
        }
    }

    private void startPvE(User user) {
        if(user.getParties().isEmpty()) {
            Party p = new Party("DefaultParty");
            p.addHero(new Hero("Hero1",1,50,10,5));
            user.addParty(p);
        }
        CampaignManager cm = new CampaignManager();
        cm.startCampaign(user.getParties().get(0));
    }
}