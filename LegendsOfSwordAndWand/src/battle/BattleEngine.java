package battle;

import java.util.List;
import model.Enemy;
import model.Hero;

public class BattleEngine {
    public void startBattle(List<Hero> heroes, List<Enemy> enemies) {
        System.out.println("Battle started!");
        while(heroes.stream().anyMatch(Hero::isAlive) && enemies.stream().anyMatch(Enemy::isAlive)) {
            // simple turn-based attack: each hero attacks first enemy alive
            for(Hero h : heroes) {
                if(h.isAlive()) {
                    for(Enemy e : enemies) {
                        if(e.isAlive()) {
                            e.takeDamage(h.getAttack());
                            System.out.println(h.getName() + " attacks " + e.getName() + " for " + h.getAttack());
                            break;
                        }
                    }
                }
            }
            // enemies attack first hero alive
            for(Enemy e : enemies) {
                if(e.isAlive()) {
                    for(Hero h : heroes) {
                        if(h.isAlive()) {
                            h.takeDamage(h.getAttack());
                            System.out.println(e.getName() + " attacks " + h.getName() + " for " + h.getAttack());
                            break;
                        }
                    }
                }
            }
        }

        if(heroes.stream().anyMatch(Hero::isAlive)) System.out.println("Heroes win!");
        else System.out.println("Enemies win!");
    }
}