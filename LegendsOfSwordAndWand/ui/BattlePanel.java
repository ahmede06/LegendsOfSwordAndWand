package ui;

import battle.BattleEngine;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import model.Enemy;
import model.Hero;

public class BattlePanel extends JPanel {

    private JTextArea battleLog;
    private JButton nextTurnBtn;
    private BattleEngine engine;
    private List<Hero> heroes;
    private List<Enemy> enemies;

    private int turn = 1;

    public BattlePanel(List<Hero> heroes, List<Enemy> enemies) {
        this.heroes = heroes;
        this.enemies = enemies;

        setLayout(new BorderLayout());

        battleLog = new JTextArea();
        battleLog.setEditable(false);
        JScrollPane scroll = new JScrollPane(battleLog);
        add(scroll, BorderLayout.CENTER);

        nextTurnBtn = new JButton("Next Turn");
        add(nextTurnBtn, BorderLayout.SOUTH);

        nextTurnBtn.addActionListener(e -> playTurn());

        engine = new BattleEngine(); 
    }

    private void playTurn() {
        if(!heroes.stream().anyMatch(Hero::isAlive) || !enemies.stream().anyMatch(Enemy::isAlive)) {
            battleLog.append("\nBattle finished!\n");
            nextTurnBtn.setEnabled(false);
            return;
        }

        battleLog.append("\n--- Turn " + turn + " ---\n");

        
        engine.runTurn(heroes, enemies, battleLog);

        turn++;
    }
}