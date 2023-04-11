import java.util.Arrays;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        // create players
        Player player1 = new Player("Player 1", 100);
        player1.setWeapon(new MeleeWeapon("Knife", 10));
        Player player2 = new Player("Player 2", 100);
        player2.setWeapon(new RangedWeapon("Bow", 20));
        List<Player> players = Arrays.asList(player1, player2);

        // create map service
        MapService mapService = new MapService(10, 10);

        // initialize players
        mapService.initializePlayers(players);

        // simulate game
        mapService.simulateGame();
    }
}
