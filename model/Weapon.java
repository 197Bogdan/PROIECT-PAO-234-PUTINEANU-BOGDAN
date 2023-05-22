package model;
public abstract class Weapon extends Item {
    private final int damage;
    private final float accuracy;
    private final int spawnRate;
    private final float attackRate;
    private final int priority;     // decides what weapon the player will use

    public Weapon(String name, int damage, float accuracy, int spawnRate, float attackRate, int priority) {
        super(name);
        this.damage = damage;
        this.accuracy = accuracy;
        this.spawnRate = spawnRate;
        this.attackRate = attackRate;
        this.priority = priority;
    }

    public int getDamage() {
        return damage;
    }
}
