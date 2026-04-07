package ui;

import battle.BattleEngine;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import model.Enemy;
import model.Hero;

public class BattlePanel extends JPanel {

    private JTextArea battleLog;
    private BattleEngine engine;

    private List<Hero> heroes;
    private List<Enemy> enemies;

    private Hero selectedHero = null;
    private Object selectedTarget = null;

    private boolean isPvP;
    private Runnable onExit;

    public BattlePanel(List<Hero> heroes, List<Enemy> enemies, boolean isPvP, Runnable onExit) {
        this.heroes = heroes;
        this.enemies = enemies;
        this.isPvP = isPvP;
        this.onExit = onExit;

        setLayout(new BorderLayout());

        engine = new BattleEngine();

        // HERO PANEL (TOP)
        JPanel heroPanel = new JPanel(new GridLayout(1, heroes.size()));

        for (Hero h : heroes) {
            JButton btn = new JButton(h.getName());
            btn.addActionListener(e -> {
                selectedHero = h;
                battleLog.append("\nSelected Hero: " + h.getName() + "\n");
            });
            heroPanel.add(btn);
        }

        add(heroPanel, BorderLayout.NORTH);

        // BATTLE LOG (CENTER)
        battleLog = new JTextArea();
        battleLog.setEditable(false);
        JScrollPane scroll = new JScrollPane(battleLog);
        add(scroll, BorderLayout.CENTER);

        // TARGET PANEL (BOTTOM)
        JPanel targetPanel = new JPanel();

        if (!isPvP) {
            targetPanel.setLayout(new GridLayout(1, enemies.size()));

            for (Enemy enemy : enemies) {
                JButton btn = new JButton(enemy.getName());
                btn.addActionListener(e -> {
                    selectedTarget = enemy;
                    battleLog.append("\nTarget Enemy: " + enemy.getName() + "\n");
                });
                targetPanel.add(btn);
            }
        } else {
            targetPanel.setLayout(new GridLayout(1, heroes.size()));

            for (Hero h : heroes) {
                JButton btn = new JButton(h.getName());
                btn.addActionListener(e -> {
                    selectedTarget = h;
                    battleLog.append("\nTarget Hero: " + h.getName() + "\n");
                });
                targetPanel.add(btn);
            }
        }

        add(targetPanel, BorderLayout.SOUTH);

        // ACTION PANEL (RIGHT) 
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(2, 1, 10, 10));

        JButton attackBtn = new JButton("Attack");
        JButton exitBtn = new JButton("Exit");

        actionPanel.add(attackBtn);
        actionPanel.add(exitBtn);

        add(actionPanel, BorderLayout.EAST);

        // BUTTON LOGIC 
        attackBtn.addActionListener(e -> performAttack());
        exitBtn.addActionListener(e -> onExit.run());
    }

    private void performAttack() {
        if (selectedHero == null || selectedTarget == null) {
            battleLog.append("\nSelect a hero and a target first!");
            return;
        }

        if (selectedTarget instanceof Enemy enemy) {
            engine.heroAttack(selectedHero, enemy, battleLog);
        } else if (selectedTarget instanceof Hero hero) {
            engine.heroVsHero(selectedHero, hero, battleLog);
        }

        selectedHero = null;
        selectedTarget = null;
    }
}