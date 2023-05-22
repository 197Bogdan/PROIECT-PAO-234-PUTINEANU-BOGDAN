package service;

import model.MeleeWeapon;
import model.RangedWeapon;
import model.Weapon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class WeaponsJDBCService {
    public static ArrayList<Weapon> readWeapons() {
        ArrayList<Weapon> weapons = new ArrayList<>();
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Establish a connection to the SQLite database
            Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Bogdan\\Desktop\\sqlite3\\HungerGamesDB.db");

            // Create a statement
            Statement statement = connection.createStatement();

            // Execute a query
            ResultSet resultSet = statement.executeQuery("SELECT * FROM weapons");

            // Process the result set
            while (resultSet.next()) {
                // Access the data from the result set
                String name = resultSet.getString("name");
                String type = resultSet.getString("type");
                int damage = resultSet.getInt("damage");
                int accuracy = resultSet.getInt("accuracy");
                int spawnRate = resultSet.getInt("spawnRate");
                int priority = resultSet.getInt("priority");

                if(type.equals("melee"))
                {
                    Weapon weapon = new MeleeWeapon(name, damage, accuracy, spawnRate, -1, -1, priority);
                    weapons.add(weapon);
                }
                else if(type.equals("ranged"))
                {
                    Weapon weapon = new RangedWeapon(name, damage, accuracy, spawnRate, -1, -1, priority);
                    weapons.add(weapon);
                }
            }

            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return weapons;
    }
}
