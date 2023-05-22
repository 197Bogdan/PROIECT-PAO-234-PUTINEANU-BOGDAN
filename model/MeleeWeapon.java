package model;
public class MeleeWeapon extends Weapon {
    private final int durability;

    public MeleeWeapon(String name, int damage, float accuracy, int spawnRate, float attackRate, int durability, int priority) {
        super(name, damage, accuracy, spawnRate, attackRate, priority);
        this.durability = durability;
    }
}
