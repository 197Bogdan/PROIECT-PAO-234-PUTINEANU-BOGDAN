package service;

import constants.FilePaths;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;

public class AuditCSVService {

    private static AuditCSVService instance;

    private AuditCSVService() {
        // Private constructor to prevent instantiation from outside the class
    }

    public static AuditCSVService getInstance() {
        if (instance == null) {
            instance = new AuditCSVService();
        }
        return instance;
    }

    static public void addLog(String text){
        try (FileWriter fileWriter = new FileWriter(FilePaths.LOGS_CSV_PATH, true)) {
            Instant timestamp = Instant.now();
            fileWriter.append(String.valueOf(timestamp)).append(",").append(text);
            fileWriter.append("\n"); // Add a new line after appending the data

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}