package service;

import constants.FilePaths;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PlayersCSVService {

    private static PlayersCSVService instance;

    private PlayersCSVService() {
        // Private constructor to prevent instantiation from outside the class
    }

    public static PlayersCSVService getInstance() {
        if (instance == null) {
            instance = new PlayersCSVService();
        }
        return instance;
    }

    public ArrayList<String> readPlayers() {
        ArrayList<String> players = new ArrayList<>();

        try {
            File file = new File(FilePaths.PLAYERS_CSV_PATH);
            Scanner scanner = new Scanner(file);

            // Read the header line (if present)
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(",");

                String name = fields[0];
                players.add(name);

            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return players;
    }

    public void addPlayer(String Name){
        try (FileWriter fileWriter = new FileWriter(FilePaths.PLAYERS_CSV_PATH, true)) {
            fileWriter.append(Name);
            fileWriter.append("\n"); // Add a new line after appending the data

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}