package nl.han.ica.oose.dea.spotitube.datasources;

import nl.han.ica.oose.dea.spotitube.models.UserModel;

public interface ILoginDAO {
    UserModel login(String user, String password, String newToken);

    // check if user OWNS certain playlist
    boolean checkOwner(String token, int playlistId);

    // check if user exists with token
    boolean checkUser(String token);
}
