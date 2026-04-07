package battle;

import javax.swing.JTextArea;
import model.Enemy;
import model.Hero;

public class BattleEngine {

    // === HERO attacks ENEMY (PvE) ===
    public void heroAttack(Hero hero, Enemy enemy, JTextArea log) {

        if (!hero.isAlive()) {
            log.append(hero.getName() + " is dead and cannot attack.\n");
            return;
        }

        if (!enemy.isAlive()) {
            log.append(enemy.getName() + " is already dead.\n");
            return;
        }

        // Hero attacks
        enemy.takeDamage(hero.getAttack());
        log.append(hero.getName() + " attacks " + enemy.getName() +
                " (Enemy HP: " + enemy.getHp() + ")\n");

        // Check if enemy died
        if (!enemy.isAlive()) {
            log.append(enemy.getName() + " has been defeated!\n");
            return;
        }

        // Enemy retaliates
        hero.takeDamage(enemy.getAttack());
        log.append(enemy.getName() + " counterattacks " + hero.getName() +
                " (Hero HP: " + hero.getHp() + ")\n");

        if (!hero.isAlive()) {
            log.append(hero.getName() + " has been defeated!\n");
        }

        log.append("\n");
    }

    // === HERO vs HERO (PvP) ===
    public void heroVsHero(Hero attacker, Hero defender, JTextArea log) {

        if (!attacker.isAlive()) {
            log.append(attacker.getName() + " is dead and cannot attack.\n");
            return;
        }

        if (!defender.isAlive()) {
            log.append(defender.getName() + " is already dead.\n");
            return;
        }

        // Attack
        defender.takeDamage(attacker.getAttack());
        log.append(attacker.getName() + " attacks " + defender.getName() +
                " (HP: " + defender.getHp() + ")\n");

        if (!defender.isAlive()) {
            log.append(defender.getName() + " has been defeated!\n");
            log.append(attacker.getName() + " wins!\n\n");
            return;
        }

        // Counterattack
        attacker.takeDamage(defender.getAttack());
        log.append(defender.getName() + " counterattacks " + attacker.getName() +
                " (HP: " + attacker.getHp() + ")\n");

        if (!attacker.isAlive()) {
            log.append(attacker.getName() + " has been defeated!\n");
            log.append(defender.getName() + " wins!\n");
        }

        log.append("\n");
    }
}