package nl.han.ica.oose.dea.spotitube.datasources;

import nl.han.ica.oose.dea.spotitube.models.PlaylistModel;
import nl.han.ica.oose.dea.spotitube.models.PlaylistOverviewModel;

public interface IPlaylistDAO {
    PlaylistOverviewModel getPlayLists(String token);

    void deletePlayList(int id);

    void addPlayList(String token, PlaylistModel playlistModel);

    void changePlayList(PlaylistModel playlistModel);
}
