package ui;

import auth.AuthManager;
import auth.User;
import factory.HeroFactory;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import model.Enemy;
import model.Hero;
import model.Party;

public class MainUI {

    private AuthManager auth;
    private JFrame frame;
    private User currentUser;

    public MainUI(AuthManager auth) {
        this.auth = auth;
        initUI();
    }

    private void initUI() {
        frame = new JFrame("PvE Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        showMainMenu();
        frame.setVisible(true);
    }

    private void showMainMenu() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,1,10,10));

        JLabel title = new JLabel("Welcome to the PvE Game!", SwingConstants.CENTER);
        panel.add(title);

        JButton registerBtn = new JButton("Register");
        JButton loginBtn = new JButton("Login");
        JButton exitBtn = new JButton("Exit");

        panel.add(registerBtn);
        panel.add(loginBtn);
        panel.add(exitBtn);

        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();

        registerBtn.addActionListener(e -> showRegisterDialog());
        loginBtn.addActionListener(e -> showLoginDialog());
        exitBtn.addActionListener(e -> System.exit(0));
    }

    private void showRegisterDialog() {
        JTextField usernameField = new JTextField();
        JTextField passwordField = new JPasswordField();
        Object[] message = {
                "Username:", usernameField,
                "Password:", passwordField
        };
        int option = JOptionPane.showConfirmDialog(frame, message, "Register", JOptionPane.OK_CANCEL_OPTION);
        if(option == JOptionPane.OK_OPTION) {
            String u = usernameField.getText();
            String p = passwordField.getText();
            if(auth.register(u,p)) JOptionPane.showMessageDialog(frame, "Registered!");
            else JOptionPane.showMessageDialog(frame, "Username taken.");
        }
    }

    private void showLoginDialog() {
        JTextField usernameField = new JTextField();
        JTextField passwordField = new JPasswordField();
        Object[] message = {
                "Username:", usernameField,
                "Password:", passwordField
        };
        int option = JOptionPane.showConfirmDialog(frame, message, "Login", JOptionPane.OK_CANCEL_OPTION);
        if(option == JOptionPane.OK_OPTION) {
            String u = usernameField.getText();
            String p = passwordField.getText();
            if(auth.login(u,p)) {
                currentUser = auth.getCurrentUser();
                JOptionPane.showMessageDialog(frame, "Logged in as " + u);
                showUserMenu();
            } else {
                JOptionPane.showMessageDialog(frame, "Login failed.");
            }
        }
    }

    private void showUserMenu() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,1,10,10));

        JLabel title = new JLabel("User Menu", SwingConstants.CENTER);
        panel.add(title);

        JButton startPvEBtn = new JButton("Start PvE");
        JButton logoutBtn = new JButton("Logout");

        panel.add(startPvEBtn);
        panel.add(logoutBtn);

        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();

        startPvEBtn.addActionListener(e -> startPvE());
        logoutBtn.addActionListener(e -> {
            currentUser = null;
            showMainMenu();
        });
    }

    private void startPvE() {
    if(currentUser.getParties().isEmpty()) {
        Party p = new Party("DefaultParty");
        p.addHero(HeroFactory.createHero("warrior"));
        p.addHero(HeroFactory.createHero("mage"));
        p.addHero(HeroFactory.createHero("adventurer"));
        currentUser.addParty(p);
    }

    // Get heroes
    List<Hero> heroes = List.of(
    HeroFactory.createHero("warrior"),
    HeroFactory.createHero("mage"),
    HeroFactory.createHero("adventurer")
);

    // Create some enemies for the demo
    List<Enemy> enemies = List.of(
        new Enemy("Goblin", 1, 50, 15, 3),
        new Enemy("Orc", 1, 80, 20, 5)
    );

    // Remove all current UI components and add BattlePanel
    frame.getContentPane().removeAll();
    frame.getContentPane().add(new BattlePanel(heroes, enemies));
    frame.revalidate();
    frame.repaint();
}
}