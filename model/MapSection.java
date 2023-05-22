package model;

import java.util.ArrayList;

public class MapSection {
    private final int x;
    private final int y;
    private final ArrayList<Player> players;
    private final ArrayList<Weapon> items;

    public MapSection(int x, int y) {
        this.x = x;
        this.y = y;
        this.players = new ArrayList<>();

        this.items = new ArrayList<>();     // init random items
        for(Weapon weapon: globals.Weapons.getAllWeapons())
            if((int) (Math.random() * 100) < weapon.getSpawnRate())
                this.items.add(weapon);
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void addItem(Weapon item) {
        items.add(item);
    }

    public void removeItems() {
        items.clear();
    }

    public ArrayList<Weapon> getItems() {
        return items;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
