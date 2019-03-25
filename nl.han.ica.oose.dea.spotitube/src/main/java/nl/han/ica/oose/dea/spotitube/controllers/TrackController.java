package nl.han.ica.oose.dea.spotitube.controllers;

import nl.han.ica.oose.dea.spotitube.controllers.dto.TrackRequestDto;
import nl.han.ica.oose.dea.spotitube.controllers.dto.TrackResponseDto;
import nl.han.ica.oose.dea.spotitube.controllers.dto.TracksResponseDto;
import nl.han.ica.oose.dea.spotitube.datasources.DataAccesObject;
import nl.han.ica.oose.dea.spotitube.datasources.TrackDAO;
import nl.han.ica.oose.dea.spotitube.models.TrackModel;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/tracks")
public class TrackController extends DataAccesObject {
  TrackDAO trackDao;

  public void checkDao() {
    if (trackDao == null) {
      trackDao = new TrackDAO();
    }
  }

  @GET
  @Produces("application/json")
  public Response availableTracks(
      @QueryParam("forPlaylist") int playlistId, @QueryParam("token") String token) {
    checkDao();
    if (trackDao.checkUser(token)) {
      List<TrackModel> trackModels = trackDao.getAvailableTracks(playlistId);
      if (trackModels.size() == 0) {
        return Response.status(204).build();
      }
      return getTracksResponse(trackModels);
    } else {
      return Response.status(401, "invalid token").build();
    }
  }

  @Produces("application/json")
  Response playListTracks(String token, int playlistId) {
    checkDao();
    if (trackDao.checkUser(token)) {
      List<TrackModel> trackModels = trackDao.getPlaylistTracks(playlistId);
      if (trackModels.size() == 0) {
        return Response.status(204).build();
      }
      return getTracksResponse(trackModels);
    } else {
      return Response.status(401, "invalid token").build();
    }
  }

  @Produces("application/json")
  Response deletePlaylistTrack(int playlistId, int trackId, String token) {
    checkDao();
    if (trackDao.checkOwner(playlistId, token)) {
      trackDao.deletePlaylistTrack(playlistId, trackId);
      return playListTracks(token, playlistId);
    } else {
      return Response.status(401, "invalid token").build();
    }
  }

  public Response addPlaylistTrack(TrackRequestDto trackRequestDto, int playlistId, String token) {
    checkDao();
    if (trackDao.checkOwner(playlistId, token)) {
      TrackModel trackModel = new TrackModel();
      trackModel.setId(trackRequestDto.getId());
      trackModel.setOfflineAvailable(trackRequestDto.getOfflineAvailable());

      trackDao.addPlayListTrack(trackModel, playlistId, token);
      return playListTracks(token, playlistId);
    } else {
      return Response.status(401, "invalid token").build();
    }
  }

  // HELPER METHODS
  private Response getTracksResponse(List<TrackModel> trackModels) {
    TracksResponseDto response = new TracksResponseDto();
    response.setTracks(convertToDtoList(trackModels));
    return Response.ok().entity(response).build();
  }

  private List<TrackResponseDto> convertToDtoList(List<TrackModel> trackModels) {
    List<TrackResponseDto> trackResponseDtos = new ArrayList<>();
    for (TrackModel modelObject : trackModels) {
      trackResponseDtos.add(
          new TrackResponseDto(
              modelObject.getId(),
              modelObject.getTitle(),
              modelObject.getPerformer(),
              modelObject.getDuration(),
              modelObject.getAlbum(),
              modelObject.getPlaycount(),
              modelObject.getPublicationDate(),
              modelObject.getDescription(),
              modelObject.getOfflineAvailable()));
    }
    return trackResponseDtos;
  }
}
