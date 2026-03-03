package model;

public class Unit {
    protected String name;
    protected int level;
    protected int hp;
    protected int attack;
    protected int defense;

    public Unit(String name, int level, int hp, int attack, int defense) {
        this.name = name;
        this.level = level;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
    }

    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getAttack() { return attack; }
    public void takeDamage(int dmg) { hp -= dmg; }
    public boolean isAlive() { return hp > 0; }
}