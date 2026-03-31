package battle;

import java.util.List;
import javax.swing.JTextArea;
import model.Enemy;
import model.Hero;

public class BattleEngine {

    private void pause() {
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void startBattle(List<Hero> heroes, List<Enemy> enemies) {
        // existing console version
    }

    // NEW METHOD for GUI battles
    public void runTurn(List<Hero> heroes, List<Enemy> enemies, JTextArea log) {

        for (Hero hero : heroes) {
            if (!hero.isAlive()) continue;
            for (Enemy enemy : enemies) {
                if (enemy.isAlive()) {
                    enemy.takeDamage(hero.getAttack());
                    log.append(hero.getName() + " attacks " + enemy.getName() +
                            " (Enemy HP: " + enemy.getHp() + ")\n");
                    pause();
                    break;
                }
            }
        }

        for (Enemy enemy : enemies) {
            if (!enemy.isAlive()) continue;
            for (Hero hero : heroes) {
                if (hero.isAlive()) {
                    hero.takeDamage(enemy.getAttack());
                    log.append(enemy.getName() + " attacks " + hero.getName() +
                            " (Hero HP: " + hero.getHp() + ")\n");
                    pause();
                    break;
                }
            }
        }

        log.append("\nHeroes:\n");
        for (Hero h : heroes) log.append(h.getName() + " HP: " + h.getHp() + "\n");

        log.append("Enemies:\n");
        for (Enemy e : enemies) log.append(e.getName() + " HP: " + e.getHp() + "\n");

       boolean anyHeroAlive = false;
        for (Hero h : heroes) {
            if (h.isAlive()) {
              anyHeroAlive = true;
             break;
            }
        }

        boolean anyEnemyAlive = false;
        for (Enemy e : enemies) {
              if (e.isAlive()) {
                  anyEnemyAlive = true;
                  break;
             }
        }

        if (!anyHeroAlive) {
             log.append("\nEnemies win!\n");
        } else if (!anyEnemyAlive) {
             log.append("\nHeroes win!\n");
        }

        log.append("\n"); // extra spacing
    }
}