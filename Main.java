import model.*;
import service.GameService;
import service.WeaponLoadService;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        // load weapon data
        WeaponLoadService weaponsReader = WeaponLoadService.getInstance();
        globals.Weapons.setAllWeapons(weaponsReader.readWeapons());

        // create players
        Player player1 = new Player("Player 1", 100);
        Player player2 = new Player("Player 2", 100);
        List<Player> players = new LinkedList<>(Arrays.asList(player1, player2));

        // create map service
        Map map = new Map(20, 20);
        Game game = new Game(map, players);
        GameService gameService = new GameService(game);

        // initialize players
        gameService.initializePlayers(players);

        // simulate game
        gameService.simulateGame();
    }
}
