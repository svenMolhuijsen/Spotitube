package nl.han.ica.oose.dea.spotitube.services;

import nl.han.ica.oose.dea.spotitube.controllers.dto.LoginRequestDto;
import nl.han.ica.oose.dea.spotitube.controllers.dto.LoginResponseDto;
import nl.han.ica.oose.dea.spotitube.datasources.LoginDAO;

import nl.han.ica.oose.dea.spotitube.models.UserModel;

import javax.inject.Inject;

public class LoginServiceImp implements ILoginService{
    LoginDAO loginDao;
    TokenService tokenService;

    public LoginServiceImp() {
    }

    @Inject
    public LoginServiceImp(LoginDAO loginDao, TokenService tokenService) {
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
