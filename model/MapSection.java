package model;

import java.util.ArrayList;
import java.util.List;

public class MapSection {
    private final int x;
    private final int y;
    private final List<Player> players;
    private final List<Item> items;
    private boolean isAccesible;

    public MapSection(int x, int y) {
        this.x = x;
        this.y = y;
        this.isAccesible = true;
        this.players = new ArrayList<>();
        this.items = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
