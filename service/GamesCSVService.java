package service;

import constants.FilePaths;
import model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GamesCSVService {

    private static GamesCSVService instance;

    private GamesCSVService() {
        // Private constructor to prevent instantiation from outside the class
    }

    public static GamesCSVService getInstance() {
        if (instance == null) {
            instance = new GamesCSVService();
        }
        return instance;
    }

    public ArrayList<GameHistory> readGames() {
        ArrayList<GameHistory> games = new ArrayList<>();

        try {
            File file = new File(FilePaths.GAMES_CSV_PATH);
            Scanner scanner = new Scanner(file);

            // Read the header line (if present)
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(",");

                int id = Integer.parseInt(fields[0]);
                int length = Integer.parseInt(fields[1]);
                String winner = fields[2];

                games.add(new GameHistory(id, length, winner));

            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return games;
    }

    void addGame(int id, int length, String winner){
        try (FileWriter fileWriter = new FileWriter(FilePaths.GAMES_CSV_PATH, true)) {
            String dataToAppend = Integer.toString(id) + "," + length + "," + winner;
            fileWriter.append(dataToAppend);
            fileWriter.append("\n"); // Add a new line after appending the data

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
