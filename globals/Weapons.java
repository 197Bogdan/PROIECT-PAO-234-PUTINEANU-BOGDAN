package globals;

import model.Weapon;

import java.util.ArrayList;

public class Weapons {
    static ArrayList<Weapon> allWeapons;

    public Weapons() {
        allWeapons = new ArrayList<>();
    }

    static public ArrayList<Weapon> getAllWeapons() {
        return allWeapons;
    }

    static public void setAllWeapons(ArrayList<Weapon> allWeapons) {
        Weapons.allWeapons = allWeapons;
    }
}
