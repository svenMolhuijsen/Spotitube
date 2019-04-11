package nl.han.ica.oose.dea.spotitube.services;

import nl.han.ica.oose.dea.spotitube.datasources.ILoginDAO;
import nl.han.ica.oose.dea.spotitube.datasources.LoginDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class TokenServiceTest {
public static final String TOKEN = "5674-2342-2343";
public static final int PLAYLISTID = 1;


TokenServiceImp tokenService;
ILoginDAO loginDAOMock;

@BeforeEach
public void setup(){
    loginDAOMock = mock(LoginDAO.class);
    tokenService = new TokenServiceImp(loginDAOMock);
}

@Test
public void checkToken() {
    //SETUP
    when(loginDAOMock.checkUser(TOKEN)).thenReturn(true);
    //TEST
    //check if method runs
    tokenService.checkToken(TOKEN);
    //VERIFY
    verify(loginDAOMock).checkUser(TOKEN);
    //check if equals via overwritten equals method in objects
}

  @Test
  public void checkToken_owner() {
      //SETUP
      when(loginDAOMock.checkOwner(TOKEN, PLAYLISTID)).thenReturn(true);
      //TEST
      //check if method runs
      tokenService.checkToken(TOKEN, PLAYLISTID);
      //VERIFY
      verify(loginDAOMock).checkOwner(TOKEN, PLAYLISTID);
      //check if equals via overwritten equals method in objects
  }

  @Test
  public void createToken() {
      //check if meethod runs
      String token = tokenService.createToken();

      //check if equals via overwritten equals method in objects
      assertEquals( "-", token.substring(4, 5));
      assertEquals( "-", token.substring(9, 10));

      assertTrue(token.substring(0,4).chars().allMatch( Character::isDigit ));
      assertTrue(token.substring(5,9).chars().allMatch( Character::isDigit ));
      assertTrue(token.substring(11,14).chars().allMatch( Character::isDigit ));
  }
}
