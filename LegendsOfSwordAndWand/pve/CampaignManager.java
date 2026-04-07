package pve;

import java.util.ArrayList;
import java.util.List;
import model.Enemy;
import model.Hero;
import model.Party;

public class CampaignManager {

    public List<Enemy> startCampaign(Party party) {
        System.out.println("Starting PvE campaign with party: " + party.getName());

        // Get heroes from the party
        List<Hero> heroes = party.getHeroes();

        // Create enemies
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(new Enemy("Goblin", 1, 40, 7, 2));
        enemies.add(new Enemy("Orc", 2, 90, 15, 3));



        return enemies;
    }
}