package service;

import model.Map;

import java.sql.*;
import java.util.ArrayList;

public class MapsJDBCService {
    public static ArrayList<Map> readMaps() {
        ArrayList<Map> maps = new ArrayList<>();
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Establish a connection to the SQLite database
            Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Bogdan\\Desktop\\sqlite3\\HungerGamesDB.db");

            // Create a statement
            Statement statement = connection.createStatement();

            // Execute a query
            ResultSet resultSet = statement.executeQuery("SELECT * FROM maps");

            // Process the result set
            while (resultSet.next()) {
                // Access the data from the result set
                int id = resultSet.getInt("id");
                int width = resultSet.getInt("width");
                int height = resultSet.getInt("height");
                maps.add(new Map(width, height));
            }


            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return maps;
    }

    public static void addMap(Map map, int id){
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Establish a connection to the SQLite database
            Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Bogdan\\Desktop\\sqlite3\\HungerGamesDB.db");

            // Create a statement
            String sql = "INSERT into maps values (" + id + "," + map.getWidth() + ",'" + map.getHeight() +"');";
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
