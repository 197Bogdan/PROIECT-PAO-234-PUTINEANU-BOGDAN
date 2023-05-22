package service;

import java.sql.*;
import java.util.ArrayList;

public class PlayersJDBCService {
    public static ArrayList<String> readPlayers() {
        ArrayList<String> players = new ArrayList<>();
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Establish a connection to the SQLite database
            Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Bogdan\\Desktop\\sqlite3\\HungerGamesDB.db");

            // Create a statement
            Statement statement = connection.createStatement();

            // Execute a query
            ResultSet resultSet = statement.executeQuery("SELECT * FROM players");

            // Process the result set
            while (resultSet.next()) {
                // Access the data from the result set
                String name = resultSet.getString("name");
                players.add(name);
            }


            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return players;
    }

    public static void addPlayer(String name){
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Establish a connection to the SQLite database
            Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Bogdan\\Desktop\\sqlite3\\HungerGamesDB.db");

            // Create a statement
            String sql = "INSERT into players values ('" + name + "');";
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
