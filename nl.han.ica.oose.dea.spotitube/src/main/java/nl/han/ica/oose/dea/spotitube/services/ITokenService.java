package nl.han.ica.oose.dea.spotitube.services;

public interface ITokenService {
    void checkToken(String token, int playlistId);

    void checkToken(String token);

    String createToken();
}
