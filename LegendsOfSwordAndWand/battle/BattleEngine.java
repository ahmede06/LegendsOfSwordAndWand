package battle;

import java.util.List;
import model.Enemy;
import model.Hero;

public class BattleEngine {

    public void startBattle(List<Hero> heroes, List<Enemy> enemies) {

        System.out.println("Battle started!");

        while(
            heroes.stream().anyMatch(Hero::isAlive) &&
            enemies.stream().anyMatch(Enemy::isAlive)
        ){

            // heroes attack
            for(Hero hero : heroes){

                if(!hero.isAlive()) continue;

                for(Enemy enemy : enemies){

                    if(enemy.isAlive()){

                        enemy.takeDamage(hero.getAttack());

                        System.out.println(
                            hero.getName() +
                            " attacks " +
                            enemy.getName()
                        );

                        break;
                    }
                }
            }

            // enemies attack
            for(Enemy enemy : enemies){

                if(!enemy.isAlive()) continue;

                for(Hero hero : heroes){

                    if(hero.isAlive()){

                        hero.takeDamage(enemy.getAttack());

                        System.out.println(
                            enemy.getName() +
                            " attacks " +
                            hero.getName()
                        );

                        break;
                    }
                }
            }
               }

        if (heroes.stream().anyMatch(Hero::isAlive)) {
            System.out.println("Heroes win!");
        } else {
            System.out.println("Enemies win!");
        }
    }
}