package nl.han.ica.oose.dea.spotitube.controllers;

import nl.han.ica.oose.dea.spotitube.controllers.dto.LoginRequestDto;
import nl.han.ica.oose.dea.spotitube.datasources.LoginDAO;
import nl.han.ica.oose.dea.spotitube.models.UserModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

public class LoginControllerTest {

  public static final String USERNAME = "s.molhuijsen@gmail.com";
  public static final String PASSWORD = "password";

  private LoginController loginController;
  private LoginDAO loginDaoMock;

  @BeforeEach
  void setup() {
    loginDaoMock = Mockito.mock(LoginDAO.class);
    loginController = new LoginController();
    loginController.setLoginDAO(loginDaoMock);
  }

  @Test
  public void hello() {}

  @Test
  public void login() {
    var dto = new LoginRequestDto();
    dto.setUser(USERNAME);
    dto.setPassword(PASSWORD);

    var userModel = new UserModel();
    userModel.setFullName("Sven Molhuijsen");
    userModel.setToken("123123-qweqwe-890809");
    Mockito.when(loginDaoMock.login(USERNAME, PASSWORD)).thenReturn(userModel);

    Response response = loginController.login(dto);

    Mockito.verify(loginDaoMock).login(USERNAME, PASSWORD);
    Assertions.assertEquals(200, response.getStatus());
  }
}
