package nl.han.ica.oose.dea.spotitube.controllers;

import nl.han.ica.oose.dea.spotitube.controllers.dto.TrackRequestDto;
import nl.han.ica.oose.dea.spotitube.controllers.dto.TrackResponseDto;
import nl.han.ica.oose.dea.spotitube.controllers.dto.TrackOverviewDto;
import nl.han.ica.oose.dea.spotitube.datasources.DataAccesObject;
import nl.han.ica.oose.dea.spotitube.datasources.TrackDAO;
import nl.han.ica.oose.dea.spotitube.models.TrackModel;
import nl.han.ica.oose.dea.spotitube.services.IPlaylistService;
import nl.han.ica.oose.dea.spotitube.services.ItrackService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/tracks")
public class TrackController {

  private ItrackService trackService;

  public TrackController() {}

  @Inject
  public TrackController(ItrackService service) {
    this.trackService = service;
  }

  public ItrackService getTrackService() {
    return trackService;
  }
  public void setTrackService(ItrackService playlistService) {
    this.trackService = playlistService;
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response availableTracks(@QueryParam("token") String token, @QueryParam("forPlaylist") int playlistId){
          TrackOverviewDto overview = trackService.getAvailableTracksForPlaylist(token, playlistId);
          return Response.ok(overview).build();
  }

}
