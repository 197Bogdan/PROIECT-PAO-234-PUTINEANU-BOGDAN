package model;
public abstract class Weapon extends Item {
    private final int damage;
    private final float accuracy;
    private final int spawnRate;
    private final float attackRate;

    public Weapon(String name, int damage, float accuracy, int spawnRate, float attackRate) {
        super(name);
        this.damage = damage;
        this.accuracy = accuracy;
        this.spawnRate = spawnRate;
        this.attackRate = attackRate;
    }

    public int getDamage() {
        return damage;
    }
}
