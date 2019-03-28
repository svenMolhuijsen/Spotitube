package nl.han.ica.oose.dea.spotitube.services;

import nl.han.ica.oose.dea.spotitube.controllers.dto.PlaylistOverviewDto;
import nl.han.ica.oose.dea.spotitube.controllers.dto.PlaylistRequestDto;
import nl.han.ica.oose.dea.spotitube.datasources.PlaylistDAO;
import nl.han.ica.oose.dea.spotitube.datasources.TrackDAO;
import nl.han.ica.oose.dea.spotitube.models.PlaylistModel;
import nl.han.ica.oose.dea.spotitube.models.PlaylistsModel;

import javax.inject.Inject;
import java.util.ArrayList;

public class PlaylistServiceImp implements IPlaylistService {
  private PlaylistDAO playlistDAO;
  private TrackDAO trackDAO;
  private TokenService tokenService;

  public PlaylistServiceImp() {}

  @Inject
  public PlaylistServiceImp(PlaylistDAO playlistDAO, TrackDAO trackDAO, TokenService tokenService) {
    this.playlistDAO = playlistDAO;
    this.trackDAO = trackDAO;
    this.tokenService = tokenService;
  }

  public PlaylistOverviewDto getAllPlaylists(String token) {
    tokenService.checkToken(token);
    // GET MODEL FROM DAO
    PlaylistsModel playListModel = playlistDAO.getPlayLists(token);
    // CREATE AND FILL DTO
    PlaylistOverviewDto playlistOverviewDto = new PlaylistOverviewDto();
    playlistOverviewDto.setLength(playListModel.getLength());
    playlistOverviewDto.setPlaylists(playListModel.getPlaylists());
    return playlistOverviewDto;
  }

    @Override
    public PlaylistOverviewDto deletePlaylist(String token, int id) {
      tokenService.checkToken(token, id);
      playlistDAO.deletePlayList(id);
      return getAllPlaylists(token);
    }

    @Override
    public PlaylistOverviewDto addPlaylist(String token, PlaylistRequestDto playlist) {
    tokenService.checkToken(token);
    PlaylistModel playListModel = new PlaylistModel(playlist.getId(),
            playlist.getName(),
            false,
            new ArrayList<>());

    playlistDAO.addPlayList(token, playListModel);
    return getAllPlaylists(token);
    }

    @Override
    public PlaylistOverviewDto changePlaylist(String token, PlaylistRequestDto playlist) {
    tokenService.checkToken(token, playlist.getId());
      PlaylistModel playListModel = new PlaylistModel();
        playListModel.setId(playlist.getId());
        playListModel.setName(playlist.getName());
      playlistDAO.changePlayList(playListModel);
      return getAllPlaylists(token);
    }

}
