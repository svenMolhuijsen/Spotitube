package nl.han.ica.oose.dea.spotitube.datasources;

import nl.han.ica.oose.dea.spotitube.models.UserModel;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class LoginDAO extends DataAccesObject {
  public UserModel login(String user, String password) {
    var model = new UserModel();
    try {
      var cnLogin = getConnection();
      var st =cnLogin.prepareStatement(
              "SELECT USER, TOKEN FROM spotitube.user WHERE USERNAME = ? AND PASSWORD=?");
      st.setString(1, user);
      st.setString(2, password);
      st.executeQuery();
      ResultSet rs = st.executeQuery();

      if(rs.next()) {
        model.setFullName(rs.getString("USER"));
        model.setToken(rs.getString("TOKEN"));
        return model;
      }
    } catch (SQLException e) {
      System.out.println("Error getting results " + e);
    }
    return model;
  }
}
