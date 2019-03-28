package nl.han.ica.oose.dea.spotitube.datasources;

import nl.han.ica.oose.dea.spotitube.exceptionMapper.TokenException;
import nl.han.ica.oose.dea.spotitube.exceptionMapper.LoginException;
import nl.han.ica.oose.dea.spotitube.exceptionMapper.DatabaseException;
import nl.han.ica.oose.dea.spotitube.models.UserModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO extends DataAccesObject {
  public UserModel login(String user, String password, String newToken) {
    var model = new UserModel();
    try {
      var cnLogin = getConnection();
      var st =
          cnLogin.prepareStatement(
              "SELECT USER, TOKEN FROM spotitube.user WHERE USERNAME = ? AND PASSWORD=?");
      st.setString(1, user);
      st.setString(2, password);
      st.executeQuery();
      ResultSet rs = st.executeQuery();

      if (rs.next()) {
        st = cnLogin.prepareStatement("UPDATE user SET token = ? WHERE user.USERNAME = ?");
        st.setString(1, newToken);
        st.setString(2, user);
        st.executeUpdate();

        model.setFullName(rs.getString("USER"));
        model.setToken(newToken);
        return model;
      }else{
        throw new LoginException("Error logging in: no credentials found");
      }
    } catch (SQLException e) {
      throw new DatabaseException(e, "Error logging in ");
    }
  }


  // check if user OWNS certain playlist
  public boolean checkOwner( String token, int playlistId) {
    try {
      var connection = getConnection();
      var st =
              connection.prepareStatement(
                      "SELECT 1 from playlist P inner join user U on P.USERNAME = U.USERNAME WHERE P.PLAYLISTID = ? AND U.TOKEN = ?");
      st.setInt(1, playlistId);
      st.setString(2, token);
      ResultSet rs = st.executeQuery();
      if (!rs.next()) {
        throw new TokenException("User not owner playlist");
      }
    } catch (SQLException e) {
      throw new RuntimeException("Database error validating token");
    }
    return false;
  }
  // check if user exists with token

  public boolean checkUser(String token) {
    // TODO check if token is OK
    try {
      var connection = getConnection();
      var st = connection.prepareStatement("SELECT 1 from user U where U.TOKEN = ?");
      st.setString(1, token);
      ResultSet rs = st.executeQuery();
      if (!rs.next()) {
        throw new TokenException("User with token not found, please try to log in again");
      }
    } catch (SQLException e) {
      throw new RuntimeException("Database error validating token");
    }
    return false;
  }

}
