package service;

import model.Game;
import model.MapSection;
import model.Player;

import java.util.List;

public class GameService {

    Game game;

    public GameService(Game game) {
        this.game = game;
    }

    public void killPlayer(Player player) {
        game.getPlayersAlive().remove(player);
        if (!game.getPlayersDead().contains(player))
            game.getPlayersDead().add(player);
    }

    public void initializePlayers(List<Player> players) {          // spawn players randomly
        for (int x = 0; x < game.getMap().getWidth(); x++) {
            for (int y = 0; y < game.getMap().getHeight(); y++) {
                game.getMap().getSections().set(x * game.getMap().getHeight() + y, new MapSection(x, y));
            }
        }
        for (Player player : players) {
            int x = (int) (Math.random() * game.getMap().getWidth());
            int y = (int) (Math.random() * game.getMap().getHeight());
            MapSection section = game.getMap().getSections().get(x * game.getMap().getWidth() + y);
            section.addPlayer(player);
            player.setSection(section);
        }
    }

    public void simulateGame() {
        // move players randomly
        do {
            for (Player player : game.getPlayersAlive()) {
                int x = player.getSection().getX() + (int) ((Math.random() * 3) - 1);
                int y = player.getSection().getY() + (int) ((Math.random() * 3) - 1);
                x = Math.max(Math.min(x, game.getMap().getWidth() - 1), 0);     // verify x in bounds
                y = Math.max(Math.min(y, game.getMap().getHeight() - 1), 0);    // verify y in bounds
                MapSection newSection = game.getMap().getSections().get(x * game.getMap().getWidth() + y);

                player.getSection().removePlayer(player);
                player.setSection(newSection);
                newSection.addPlayer(player);
            }

            // check for encounters
            for (MapSection section : game.getMap().getSections()) {
                List<Player> players = section.getPlayers();
                if (players.size() > 1) {
                    // simulate encounter
                    Player attacker = players.get(0);
                    Player defender = players.get(1);
                    System.out.println(attacker.getName() + " attacks " + defender.getName() + " with " + attacker.getWeapon().getName() + "!");
                    defender.setHealth(defender.getHealth() - attacker.getWeapon().getDamage());
                    System.out.println(defender.getName() + " attacks " + attacker.getName() + " with " + defender.getWeapon().getName() + "!");
                    attacker.setHealth(attacker.getHealth() - defender.getWeapon().getDamage());
                    if (defender.isDead()) {
                        System.out.println(defender.getName() + " dies!");
                        section.removePlayer(defender);
                        killPlayer(defender);
                    }
                    if (attacker.isDead()) {
                        System.out.println(attacker.getName() + " dies!");
                        section.removePlayer(attacker);
                        killPlayer(attacker);
                        break;

                    }
                }
            }

        } while (game.getPlayersAlive().size() > 1);
        // print result
        if (game.getPlayersAlive().size() == 1) {
            System.out.println(game.getPlayersAlive().get(0).getName() + " wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }
}
