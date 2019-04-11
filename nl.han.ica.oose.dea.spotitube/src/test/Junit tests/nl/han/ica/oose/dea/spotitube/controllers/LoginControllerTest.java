package nl.han.ica.oose.dea.spotitube.controllers;

import nl.han.ica.oose.dea.spotitube.dto.LoginRequestDto;
import nl.han.ica.oose.dea.spotitube.dto.LoginResponseDto;
import nl.han.ica.oose.dea.spotitube.services.ILoginService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.mockito.Mockito.*;

public class LoginControllerTest {

    public static final String USERNAME = "s.molhuijsen@gmail.com";
    public static final String PASSWORD = "password";
    public static final String REAL_NAME = "Sven Molhuijsen";
    public static final String TOKEN = "5674-2342-2343";

    private LoginController loginController;
    private ILoginService loginServiceMock;


    @BeforeEach
    void setup() {
        loginServiceMock = mock(ILoginService.class);
        loginController = new LoginController();

        loginController.setLoginService(loginServiceMock);
    }

    @Test
    public void hello() {}

    @Test
    public void login() {
        // Setup
        var loginRequestDto = new LoginRequestDto();
        loginRequestDto.setUser(USERNAME);
        loginRequestDto.setPassword(PASSWORD);

        var loginResponseDto = new LoginResponseDto();
        loginResponseDto.setUser(REAL_NAME);
        loginResponseDto.setToken(TOKEN);

        when(loginServiceMock.login(loginRequestDto)).thenReturn(loginResponseDto);

        // Test
        Response response = loginController.login(loginRequestDto);

        // Verify
        verify(loginServiceMock).login(loginRequestDto);
        Assertions.assertEquals(200, response.getStatus());

    }
}
