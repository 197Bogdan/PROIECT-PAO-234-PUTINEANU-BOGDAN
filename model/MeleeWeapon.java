package model;
public class MeleeWeapon extends Weapon {
    private final int durability;

    public MeleeWeapon(String name, int damage, float accuracy, int spawnRate, float attackRate, int durability) {
        super(name, damage, accuracy, spawnRate, attackRate);
        this.durability = durability;
    }
}
