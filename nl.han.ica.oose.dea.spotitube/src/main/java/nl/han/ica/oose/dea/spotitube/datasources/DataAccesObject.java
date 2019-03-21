package nl.han.ica.oose.dea.spotitube.datasources;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataAccesObject {
    //IEDERE DAO KRIJGT EIGEN CONNECTIE
    public Connection getConnection() {
        var properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("database.properties"));
            Class.forName(properties.getProperty("driver"));
            Connection connection;
            connection = DriverManager.getConnection(properties.getProperty("connectionString"));
            return connection;
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.out.println("Error connecting to a database: " + e);
        }
        return null;
    }
}
