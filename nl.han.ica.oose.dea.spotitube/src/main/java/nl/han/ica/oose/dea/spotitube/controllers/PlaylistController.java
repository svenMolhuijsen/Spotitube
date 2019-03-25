package nl.han.ica.oose.dea.spotitube.controllers;

import nl.han.ica.oose.dea.spotitube.controllers.dto.PlaylistRequestDto;
import nl.han.ica.oose.dea.spotitube.controllers.dto.PlaylistsresponseDto;
import nl.han.ica.oose.dea.spotitube.controllers.dto.TrackRequestDto;
import nl.han.ica.oose.dea.spotitube.datasources.PlaylistDAO;
import nl.han.ica.oose.dea.spotitube.models.PlaylistModel;
import nl.han.ica.oose.dea.spotitube.models.PlaylistsModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistController {
  PlaylistDAO playlistDao;

  public void checkDao() {
    if (playlistDao == null) {
      playlistDao = new PlaylistDAO();
    }
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response playlists(@QueryParam("token") String token) {
    checkDao();
    if (playlistDao.checkUser(token)) {
      PlaylistsModel playlists = playlistDao.getPlayLists(token);
      if (playlists.getPlaylists().size() == 0) {
        return Response.status(403).build();
      }

      PlaylistsresponseDto response = new PlaylistsresponseDto();
      response.setLength(playlists.getLength());
      response.setPlaylists(playlists.getPlaylists());
      return Response.ok().entity(response).build();
    } else {
      return Response.status(401, "invalid token").build();
    }
}
  @DELETE
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{id}")
  public Response playlists(@QueryParam("token") String token, @PathParam("id") int playListId) {
    checkDao();
    if (playlistDao.checkOwner(playListId, token)) {
      playlistDao.deletePlayLists(playListId);
      return playlists(token);
    } else {
      return Response.status(401, "invalid token").build();
    }
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{id}")
  public Response changePlayList(@QueryParam("token") String token, PlaylistRequestDto request) {
    checkDao();
    if (playlistDao.checkOwner(request.getId(), token)) {
      PlaylistModel playlistModel = new PlaylistModel();
      playlistModel.setId(request.getId());
      playlistModel.setName(request.getName());
      playlistDao.changePlayLists(playlistModel);
      return playlists(token);
    } else {
      return Response.status(401, "invalid token").build();
    }
  }


  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response playlists(@QueryParam("token") String token, PlaylistRequestDto request) {
    checkDao();
      if (playlistDao.checkOwner(request.getId(), token)) {
          PlaylistModel playlistModel =
        new PlaylistModel(request.getId(), request.getName(), false, request.getTracks());
    playlistDao.addPlayList(token, playlistModel);
    return playlists(token);
      } else {
          return Response.status(401, "invalid token").build();
      }
  }


  //REROUTES TO TRACKCONTROLLER, FOR LOGIC SEE TRACKCONTROLLER.JAVA
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{id}/tracks")
  public Response playlistTracks(@QueryParam("token") String token, @PathParam("id") int id) {
    // REROUTING TO track controller to prevent duplication of code
    TrackController trackController = new TrackController();
    return trackController.playListTracks(token, id);
  }

  @DELETE
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{playlistId}/tracks/{trackId}")
  public Response deletePlaylistTrack(
      @QueryParam("token") String token,
      @PathParam("playlistId") int playListId,
      @PathParam("trackId") int trackId) {

    // REROUTING TO track controller to prevent duplication of code
    TrackController trackController = new TrackController();
    return trackController.deletePlaylistTrack(playListId, trackId, token);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{playlistId}/tracks/")
  public Response addPlaylistTrack(
      @QueryParam("token") String token,
      @PathParam("playlistId") int playListId,
      TrackRequestDto request) {
    // REROUTING TO track controller to prevent duplication of code
    TrackController trackController = new TrackController();
    return trackController.addPlaylistTrack(request, playListId, token);
  }
}
