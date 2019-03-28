package nl.han.ica.oose.dea.spotitube.services;

import nl.han.ica.oose.dea.spotitube.controllers.LoginController;
import nl.han.ica.oose.dea.spotitube.controllers.dto.LoginRequestDto;
import nl.han.ica.oose.dea.spotitube.controllers.dto.LoginResponseDto;
import nl.han.ica.oose.dea.spotitube.controllers.dto.PlaylistOverviewDto;

public interface ILoginService {
    LoginResponseDto login(LoginRequestDto request);

}
