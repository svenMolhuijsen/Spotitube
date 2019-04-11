package nl.han.ica.oose.dea.spotitube.services;

import nl.han.ica.oose.dea.spotitube.dto.PlaylistOverviewDto;
import nl.han.ica.oose.dea.spotitube.dto.PlaylistRequestDto;

public interface IPlaylistService {
    PlaylistOverviewDto getAllPlaylists(String token);


    PlaylistOverviewDto deletePlaylist(String token, int id);


    PlaylistOverviewDto addPlaylist(String token, PlaylistRequestDto playlist);

    PlaylistOverviewDto changePlaylist(String token, PlaylistRequestDto playlist);


}
