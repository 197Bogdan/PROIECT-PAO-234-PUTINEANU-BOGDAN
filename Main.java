import model.*;
import service.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        // load weapon data
        WeaponsCSVService weaponsReader = WeaponsCSVService.getInstance();
        globals.Weapons.setAllWeapons(weaponsReader.readWeapons());

        //load game history
        GamesCSVService gamesReader = GamesCSVService.getInstance();
        ArrayList<GameHistory> gameHistory = gamesReader.readGames();

        //load past players
        PlayersCSVService playersReader = PlayersCSVService.getInstance();
        ArrayList<String> registeredPlayers = playersReader.readPlayers();

        //load map history
        MapsCSVService mapsReader = MapsCSVService.getInstance();
        ArrayList<Map> mapsHistory = mapsReader.readMaps();

        int player_count = 115;
        // register new players
        int lastPlayerId =  Integer.parseInt(registeredPlayers.get(registeredPlayers.size() - 1).split(" ")[1]);
        if(player_count > lastPlayerId)
            for(int i=lastPlayerId+1;i < player_count;i++)
                playersReader.addPlayer("Player " + i);
        // create players

        List<Player> players = new ArrayList<>();
        for(int i=0; i<player_count; i++)
            players.add(new Player("Player " + i, 100));

        // create new game
        int newId = gameHistory.get(gameHistory.size() - 1).getGameId() + 1;
        Map map = new Map(50, 50);
        Game game = new Game(map, players, newId);
        GameService gameService = new GameService(game);

        // initialize players
        gameService.initializePlayers(players);

        // simulate game
        gameService.simulateGame();

        //add map to history
        mapsReader.addMap(map);
    }
}
