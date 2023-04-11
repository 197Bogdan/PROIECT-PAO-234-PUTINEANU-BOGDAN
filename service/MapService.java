import java.util.Arrays;
import java.util.List;

public class MapService {

    private Map map;

    public MapService(int width, int height) {
        map = new Map(width, height);
    }

    public void initializePlayers(List<Player> players) {
        map.initialize(players);
    }

    public void simulateGame() {
        while (true) {
            // move players randomly
            for (Player player : map.getPlayers()) {
                int x = player.getSection().getX() + (int) (Math.random() * 3) - 1;
                int y = player.getSection().getY() + (int) (Math.random() * 3) - 1;
                MapSection section = map.getSection(x, y);
                player.setSection(section);
            }

            // check for encounters
            for (MapSection section : map.getSections()) {
                List<Player> players = section.getPlayers();
                if (players.size() > 1) {
                    // simulate encounter
                    Player attacker = players.get(0);
                    Player defender = players.get(1);
                    System.out.println(attacker.getName() + " attacks " + defender.getName() + "!");
                    defender.setHealth(defender.getHealth() - attacker.getWeapon().getDamage());
                    if (!defender.isAlive()) {
                        System.out.println(defender.getName() + " dies!");
                        section.removePlayer(defender);
                        break;
                    }
                }
            }

            // check for end of game
            if (map.getPlayers().size() <= 1) {
                break;
            }
        }

        // print result
        if (map.getPlayers().size() == 1) {
            System.out.println(map.getPlayers().get(0).getName() + " wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

}
