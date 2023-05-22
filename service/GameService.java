package service;

import model.Game;
import model.MapSection;
import model.Player;
import model.Weapon;

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

        for (Player player : players) {
            int x = (int) (Math.random() * game.getMap().getWidth());
            int y = (int) (Math.random() * game.getMap().getHeight());
            MapSection section = game.getMap().getSections().get(x * game.getMap().getHeight() + y);
            section.addPlayer(player);
            player.setSection(section);
        }

        System.out.println("Players spawned randomly!\n\n");
        AuditCSVService.addLog("Players spawned randomly!");
    }

    public void simulateGame() {
        // move players randomly
        int loopCount = 0;

        do {
            loopCount++;
            System.out.println("Stage " + loopCount + ":\nPlayers move randomly!\n");
            AuditCSVService.addLog("Players move randomly!");
            for (Player player : game.getPlayersAlive()) {
                int x = player.getSection().getX() + (int) ((Math.random() * 3) - 1);
                int y = player.getSection().getY() + (int) ((Math.random() * 3) - 1);
                x = Math.max(Math.min(x, game.getMap().getWidth() - 1), 0);     // verify x in bounds
                y = Math.max(Math.min(y, game.getMap().getHeight() - 1), 0);    // verify y in bounds
                MapSection newSection = game.getMap().getSections().get(x * game.getMap().getHeight() + y);

                player.getSection().removePlayer(player);
                player.setSection(newSection);
                newSection.addPlayer(player);
            }

            // check for encounters
            for (MapSection section : game.getMap().getSections()) {
                List<Player> players = section.getPlayers();
                if (players.size() > 1)
                {
                    System.out.println("Encounter started!");
                    AuditCSVService.addLog("Encounter started!");
                    while(players.size() > 1)
                    {
                        // simulate encounter
                        for (int idx = 0; idx < players.size(); idx++) {
                            Player attacker = players.get(idx);
                            int randomDefender;
                            do {
                                randomDefender = (int) (Math.random() * players.size());
                            } while (randomDefender == idx);

                            Player defender = players.get(randomDefender);
                            if (Math.random() * 100 < attacker.getWeapon().getAccuracy()){
                                defender.setHealth(defender.getHealth() - attacker.getWeapon().getDamage());
                                System.out.println(attacker.getName() + " attacks " + defender.getName() + " with " + attacker.getWeapon().getName() + "! (hit)");
                                AuditCSVService.addLog(attacker.getName() + " attacks " + defender.getName() + " with " + attacker.getWeapon().getName() + "! (hit)");
                            }
                            else
                                System.out.println(attacker.getName() + " attacks " + defender.getName() + " with " + attacker.getWeapon().getName() + "! (miss)");
                            AuditCSVService.addLog(attacker.getName() + " attacks " + defender.getName() + " with " + attacker.getWeapon().getName() + "! (miss)");


                            if (defender.isDead()) {
                                System.out.println(defender.getName() + " dies!");
                                AuditCSVService.addLog(defender.getName() + " dies!");
                                section.removePlayer(defender);
                                killPlayer(defender);
                            }
                        }
                    }
                    System.out.println("Encounter finished!\n");
                    AuditCSVService.addLog("Encounter finished!\n");
                }
            }

            // loot available items after eventual encounter
            for (MapSection section : game.getMap().getSections()) {
                List<Player> players = section.getPlayers();
                if (players.size() == 1 && section.getItems().size() > 0) {

                    players.get(0).addWeapons(section.getItems());
                    for(Weapon w: section.getItems()){
                        System.out.println(players.get(0).getName() + " finds a(n) " + w.getName());
                        AuditCSVService.addLog(players.get(0).getName() + " finds a(n) " + w.getName());
                    }
                    section.removeItems();
                    System.out.println();

                }
            }

            // update player weapons with best weapon they own
            for(Player player : game.getPlayersAlive())
                player.setWeapon(player.getWeapons().first());

        } while (game.getPlayersAlive().size() > 1);
        // print result
        if (game.getPlayersAlive().size() == 1) {
            System.out.println(game.getPlayersAlive().get(0).getName() + " wins!");
            AuditCSVService.addLog(game.getPlayersAlive().get(0).getName() + " wins!");
        } else {
            System.out.println("It's a tie!");
            AuditCSVService.addLog("It's a tie!");
        }

        // save game to csv
        GamesJDBCService.addGame(game.getId(), loopCount, game.getPlayersAlive().get(0).getName());
    }
}
