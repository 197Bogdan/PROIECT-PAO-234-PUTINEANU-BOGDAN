import model.*;
import service.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        // load weapon data
        globals.Weapons.setAllWeapons(WeaponsJDBCService.readWeapons());

        //load game history
        ArrayList<GameHistory> gameHistory = GamesJDBCService.readGames();

        //load past players
        PlayersCSVService playersReader = PlayersCSVService.getInstance();
        ArrayList<String> registeredPlayers = playersReader.readPlayers();

        //load map history
        ArrayList<Map> mapsHistory = MapsJDBCService.readMaps();

        int player_count = 25;
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

        AuditCSVService.addLog("Starting game... (game id: " + newId + ")");
        // initialize players
        gameService.initializePlayers(players);

        // simulate game
        gameService.simulateGame();

        //add map to history
        MapsJDBCService.addMap(map, newId);
    }
}
