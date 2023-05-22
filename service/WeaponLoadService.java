package service;

import constants.FilePaths;

import model.MeleeWeapon;
import model.RangedWeapon;
import model.Weapon;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class WeaponLoadService {
    private static WeaponLoadService instance;

    private WeaponLoadService() {
        // Private constructor to prevent instantiation from outside the class
    }

    public static WeaponLoadService getInstance() {
        if (instance == null) {
            instance = new WeaponLoadService();
        }
        return instance;
    }

    public ArrayList<Weapon> readWeapons() {
        ArrayList<Weapon> weapons = new ArrayList<>();

        try {
            File file = new File(FilePaths.WEAPONS_CSV_PATH);
            Scanner scanner = new Scanner(file);

            // Read the header line (if present)
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(",");

                String name = fields[0];
                String type = fields[1];
                int damage = Integer.parseInt(fields[2]);
                int accuracy = Integer.parseInt(fields[3]);
                int spawnRate = Integer.parseInt(fields[4]);
                float attackRate = Float.parseFloat(fields[5]);
                int priority = Integer.parseInt(fields[6]);

                // Assuming the last field represents either durability or ammo
                int value = Integer.parseInt(fields[7]);

                if(type.equals("melee"))
                {
                    Weapon weapon = new MeleeWeapon(name, damage, accuracy, spawnRate, attackRate, priority, value);
                    weapons.add(weapon);
                }
                else if(type.equals("ranged"))
                {
                    Weapon weapon = new RangedWeapon(name, damage, accuracy, spawnRate, attackRate, priority, value);
                    weapons.add(weapon);
                }

            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return weapons;
    }
}

