package nl.han.ica.oose.dea.spotitube.dto;

public class LoginResponseDto {
  String user;
  String token;

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
