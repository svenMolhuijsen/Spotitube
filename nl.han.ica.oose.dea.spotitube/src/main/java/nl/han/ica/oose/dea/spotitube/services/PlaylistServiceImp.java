package nl.han.ica.oose.dea.spotitube.services;

import nl.han.ica.oose.dea.spotitube.datasources.IPlaylistDAO;
import nl.han.ica.oose.dea.spotitube.datasources.ITrackDAO;
import nl.han.ica.oose.dea.spotitube.dto.PlaylistOverviewDto;
import nl.han.ica.oose.dea.spotitube.dto.PlaylistRequestDto;
import nl.han.ica.oose.dea.spotitube.models.PlaylistModel;
import nl.han.ica.oose.dea.spotitube.models.PlaylistOverviewModel;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.ArrayList;

@Default
public class PlaylistServiceImp implements IPlaylistService {
  private IPlaylistDAO playlistDAO;
  private ITrackDAO trackDAO;
  private ITokenService tokenService;

  @Inject
  public PlaylistServiceImp(IPlaylistDAO playlistDAO, ITrackDAO trackDAO, ITokenService tokenService) {
    this.playlistDAO = playlistDAO;
    this.trackDAO = trackDAO;
    this.tokenService = tokenService;
  }

  public PlaylistOverviewDto getAllPlaylists(String token) {
    tokenService.checkToken(token);
    // GET MODEL FROM DAO
    PlaylistOverviewModel playListModel = playlistDAO.getPlayLists(token);

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
