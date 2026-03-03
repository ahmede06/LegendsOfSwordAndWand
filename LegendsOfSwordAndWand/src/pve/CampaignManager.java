package pve;

import model.Party;
import battle.BattleEngine;
import model.Hero;
import model.Enemy;
import java.util.ArrayList;
import java.util.List;

public class CampaignManager {
    public void startCampaign(Party party) {
        System.out.println("Starting PvE campaign with party: " + party.getName());
        // Simple demo: 1 battle with 2 enemies
        List<Hero> heroes = party.getHeroes();
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(new Enemy("Goblin", 1, 20, 5, 2));
        enemies.add(new Enemy("Orc", 2, 30, 6, 3));

        BattleEngine battle = new BattleEngine();
        battle.startBattle(heroes, enemies);
    }
}