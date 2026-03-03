package model;

import java.util.ArrayList;
import java.util.List;

public class Party {
    private String name;
    private List<Hero> heroes;

    public Party(String name) {
        this.name = name;
        heroes = new ArrayList<>();
    }

    public String getName() { return name; }
    public List<Hero> getHeroes() { return heroes; }
    public void addHero(Hero h) { heroes.add(h); }
}