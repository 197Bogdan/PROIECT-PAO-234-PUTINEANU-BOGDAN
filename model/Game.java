package model;

import java.util.List;
import java.util.ArrayList;

public class Game {

    int id;
    private Map map;
    private List<Player> playersAlive;
    private List<Player> playersDead;

    public Game(Map map, List<Player> players, int id) {
        this.id = id;
        this.map = map;
        this.playersAlive = players;
        this.playersDead = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public List<Player> getPlayersAlive() {
        return playersAlive;
    }

    public void setPlayersAlive(List<Player> playersAlive) {
        this.playersAlive = playersAlive;
    }

    public List<Player> getPlayersDead() {
        return playersDead;
    }

    public void setPlayersDead(List<Player> playersDead) {
        this.playersDead = playersDead;
    }
}


