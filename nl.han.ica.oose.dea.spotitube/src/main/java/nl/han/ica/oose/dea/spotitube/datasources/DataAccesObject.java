package nl.han.ica.oose.dea.spotitube.datasources;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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

    //check if user OWNS certain playlist
    public boolean checkOwner(int playlistId, String token){
        try {
            var connection = getConnection();
            var st =
                    connection.prepareStatement(
                            "SELECT 1 from playlist P inner join user U on P.USERNAME = U.USERNAME WHERE P.PLAYLISTID = ? AND U.TOKEN = ?");
            st.setInt(1, playlistId);
            st.setString(2, token);
            ResultSet rs = st.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Error checking token: " + e);
        }
        return false;
    }
    //check if user exists with token

    public boolean checkUser(String token){
        //TODO check if token is OK
        try {
            var connection = getConnection();
            var st =
                    connection.prepareStatement(
                            "SELECT 1 from user U where U.TOKEN = ?");
            st.setString(1, token);
            ResultSet rs = st.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Error checking token: " + e);
        }
        return false;
    }
}
