package model;
public class RangedWeapon extends Weapon {
    private final int ammo;

    public RangedWeapon(String name, int damage, float accuracy, int spawnRate, float attackRate, int ammo) {
        super(name, damage, accuracy, spawnRate, attackRate);
        this.ammo = ammo;
    }
}
