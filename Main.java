import model.*;
import service.GameService;
import service.WeaponLoadService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        // load weapon data
        WeaponLoadService weaponsReader = WeaponLoadService.getInstance();
        globals.Weapons.setAllWeapons(weaponsReader.readWeapons());

        // create players
        int player_count = 320;
        List<Player> players = new ArrayList<>();
        for(int i=0; i<player_count; i++)
            players.add(new Player("Player " + i, 100));

        // create map service
        Map map = new Map(25, 25);
        Game game = new Game(map, players);
        GameService gameService = new GameService(game);

        // initialize players
        gameService.initializePlayers(players);

        // simulate game
        gameService.simulateGame();
    }
}
