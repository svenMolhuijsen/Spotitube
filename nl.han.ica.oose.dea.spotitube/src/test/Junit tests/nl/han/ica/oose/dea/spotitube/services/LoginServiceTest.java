package nl.han.ica.oose.dea.spotitube.services;

import nl.han.ica.oose.dea.spotitube.datasources.ILoginDAO;
import nl.han.ica.oose.dea.spotitube.dto.LoginRequestDto;
import nl.han.ica.oose.dea.spotitube.dto.LoginResponseDto;
import nl.han.ica.oose.dea.spotitube.datasources.LoginDAO;
import nl.han.ica.oose.dea.spotitube.models.UserModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class LoginServiceTest {

    public static final String USERNAME = "s.molhuijsen@gmail.com";
    public static final String PASSWORD = "password";
    public static final String REAL_NAME = "Sven Molhuijsen";
    public static final String TOKEN = "5674-2342-2343";

    private ILoginService loginService;
    private ILoginDAO loginDao;
    private TokenServiceImp tokenService;


    @BeforeEach
    void setup() {
        loginDao = mock(LoginDAO.class);
        tokenService = mock(TokenServiceImp.class);
        loginService = new LoginServiceImp(loginDao, tokenService);
    }


    @Test
    public void login() {
        // Setup
        var loginRequestDto = new LoginRequestDto();
        loginRequestDto.setUser(USERNAME);
        loginRequestDto.setPassword(PASSWORD);

        var loginResponseDto = new LoginResponseDto();
        loginResponseDto.setUser(REAL_NAME);
        loginResponseDto.setToken(TOKEN);

        var userModel = new UserModel();
        userModel.setFullName(REAL_NAME);
        userModel.setToken(TOKEN);

        when(loginDao.login(USERNAME, PASSWORD, TOKEN)).thenReturn(userModel);
        when(tokenService.createToken()).thenReturn(TOKEN);

        // Test
        LoginResponseDto response = loginService.login(loginRequestDto);

        // Verify
        verify(loginDao).login(USERNAME, PASSWORD, TOKEN);
        Assertions.assertEquals(loginResponseDto.getToken(), response.getToken());
        Assertions.assertEquals(loginResponseDto.getUser(), response.getUser());
    }
}
