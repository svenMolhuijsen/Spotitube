package nl.han.ica.oose.dea.spotitube.services;

import nl.han.ica.oose.dea.spotitube.datasources.LoginDAO;
import nl.han.ica.oose.dea.spotitube.datasources.PlaylistDAO;
import nl.han.ica.oose.dea.spotitube.datasources.TrackDAO;

import javax.inject.Inject;

public class TokenService {
  private LoginDAO loginDAO;

  public TokenService() {}

  @Inject
  public TokenService(LoginDAO loginDAO) {
    this.loginDAO = loginDAO;
  }

  public void checkToken(String token, int playlistId){
    loginDAO.checkOwner(token, playlistId);
  }

  public void checkToken(String token){
    loginDAO.checkUser(token);
  }

  public String createToken() {
    return getRandomInRange(1000, 9999)
        + "-"
        + getRandomInRange(1000, 9999)
        + "-"
        + getRandomInRange(1000, 9999);
  }

  private int getRandomInRange(double min, double max) {
    return (int) ((Math.random() * ((max - min) + 1)) + min);
  }
}
