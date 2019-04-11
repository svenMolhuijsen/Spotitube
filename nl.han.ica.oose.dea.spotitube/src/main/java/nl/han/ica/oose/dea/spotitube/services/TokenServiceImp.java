package nl.han.ica.oose.dea.spotitube.services;

import nl.han.ica.oose.dea.spotitube.datasources.ILoginDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
public class TokenServiceImp implements ITokenService {
  private ILoginDAO loginDAO;

  @Inject
  public TokenServiceImp(ILoginDAO loginDAO) {
    this.loginDAO = loginDAO;
  }

  @Override
  public void checkToken(String token, int playlistId){
    loginDAO.checkOwner(token, playlistId);
  }

  @Override
  public void checkToken(String token){
    loginDAO.checkUser(token);
  }

  @Override
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
