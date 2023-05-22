import model.*;
import service.GameService;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        // create players
        Player player1 = new Player("Player 1", 100);
        player1.setWeapon(new MeleeWeapon("Knife", 10, 0,0,0,0));
        Player player2 = new Player("Player 2", 100);
        player2.setWeapon(new RangedWeapon("Bow", 20, 0,0,0,0));
        List<Player> players = new LinkedList<>(Arrays.asList(player1, player2));

        // create map service
        Map map = new Map(2, 2);
        Game game = new Game(map, players);
        GameService gameService = new GameService(game);

        // initialize players
        gameService.initializePlayers(players);

        // simulate game
        gameService.simulateGame();
    }
}
