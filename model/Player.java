package model;

import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

public class Player {
    private final String name;
    private int health;
    private Weapon weapon;
    private final SortedSet<Weapon> weapons;
    private MapSection section;

    public Player(String name, int health) {
        this.name = name;
        this.health = health;
        this.weapons = new TreeSet<>();

        weapons.add(new MeleeWeapon("Fist", 5, 80, 0, 1, -1, 0));   // default weapon
        this.weapon = weapons.first();
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }


    public MapSection getSection() {
        return section;
    }

    public void setSection(MapSection section) {
        this.section = section;
    }

    public SortedSet<Weapon> getWeapons() {
        return weapons;
    }

    public boolean isDead() {
        return health <= 0;
    }

    public void addWeapons(ArrayList<Weapon> weapons)
    {
        this.weapons.addAll(weapons);
    }

    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof Player))
            return false;
        return ((Player) o).name.equals(this.name);
    }
}
