package service;

import constants.FilePaths;
import model.Map;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MapsCSVService {

    private static MapsCSVService instance;

    private MapsCSVService() {
        // Private constructor to prevent instantiation from outside the class
    }

    public static MapsCSVService getInstance() {
        if (instance == null) {
            instance = new MapsCSVService();
        }
        return instance;
    }

    public ArrayList<Map> readMaps() {
        ArrayList<Map> maps = new ArrayList<>();

        try {
            File file = new File(FilePaths.MAPS_CSV_PATH);
            Scanner scanner = new Scanner(file);

            // Read the header line (if present)
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(",");

                int width = Integer.parseInt(fields[0]);
                int height = Integer.parseInt(fields[1]);
                maps.add(new Map(width, height));

            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return maps;
    }

    public void addMap(Map map){
        try (FileWriter fileWriter = new FileWriter(FilePaths.MAPS_CSV_PATH, true)) {
            fileWriter.append(String.valueOf(map.getWidth())).append(",").append(String.valueOf(map.getHeight()));
            fileWriter.append("\n"); // Add a new line after appending the data

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}