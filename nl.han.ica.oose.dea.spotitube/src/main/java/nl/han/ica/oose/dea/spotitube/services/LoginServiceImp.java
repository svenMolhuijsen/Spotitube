package nl.han.ica.oose.dea.spotitube.services;

import nl.han.ica.oose.dea.spotitube.datasources.ILoginDAO;
import nl.han.ica.oose.dea.spotitube.dto.LoginRequestDto;
import nl.han.ica.oose.dea.spotitube.dto.LoginResponseDto;

import nl.han.ica.oose.dea.spotitube.models.UserModel;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
public class LoginServiceImp implements ILoginService{
    private ILoginDAO loginDao;
    private TokenServiceImp tokenService;

    @Inject
    public LoginServiceImp(ILoginDAO loginDao, TokenServiceImp tokenService) {
        this.loginDao = loginDao;
        this.tokenService = tokenService;
    }

    @Override
    public LoginResponseDto login(LoginRequestDto request) {
        var token = tokenService.createToken();
        UserModel userModel = loginDao.login(request.getUser(), request.getPassword(), token);
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setToken(userModel.getToken());
        loginResponseDto.setUser(userModel.getFullName());
        return loginResponseDto;
    }



}
