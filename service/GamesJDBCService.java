package service;

import model.GameHistory;

import java.sql.*;
import java.util.ArrayList;

public class GamesJDBCService {
    public static ArrayList<GameHistory> readGames() {
        ArrayList<GameHistory> games = new ArrayList<>();
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Establish a connection to the SQLite database
            Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Bogdan\\Desktop\\sqlite3\\HungerGamesDB.db");

            // Create a statement
            Statement statement = connection.createStatement();

            // Execute a query
            ResultSet resultSet = statement.executeQuery("SELECT * FROM gameHistory");

            // Process the result set
            while (resultSet.next()) {
                // Access the data from the result set
                String winner = resultSet.getString("winner");

                int id = resultSet.getInt("id");
                int length = resultSet.getInt("length");
                games.add(new GameHistory(id, length, winner));
            }

        // Close the resources
        resultSet.close();
        statement.close();
        connection.close();
        }
         catch(Exception e)

        {
            e.printStackTrace();
        }
        return games;
    }

    public static void addGame(int id, int length, String winner){
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Establish a connection to the SQLite database
            Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Bogdan\\Desktop\\sqlite3\\HungerGamesDB.db");

            // Create a statement
            String sql = "INSERT into gameHistory values (" + id + "," + length + ",'" + winner +"');";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            statement.close();
            connection.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
