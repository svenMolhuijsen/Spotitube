package nl.han.ica.oose.dea.spotitube.services;

import nl.han.ica.oose.dea.spotitube.dto.LoginRequestDto;
import nl.han.ica.oose.dea.spotitube.dto.LoginResponseDto;

public interface ILoginService {
    LoginResponseDto login(LoginRequestDto request);

}
