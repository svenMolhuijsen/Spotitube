package nl.han.ica.oose.dea.spotitube.controllers;

import nl.han.ica.oose.dea.spotitube.dto.TrackOverviewDto;
import nl.han.ica.oose.dea.spotitube.services.ItrackService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/tracks")
public class TrackController {

  private ItrackService trackService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response availableTracks(@QueryParam("token") String token, @QueryParam("forPlaylist") int playlistId){
          TrackOverviewDto overview = trackService.getAvailableTracksForPlaylist(token, playlistId);
          return Response.ok(overview).build();
  }

  @Inject
  public void setTrackService(ItrackService trackService) {
    this.trackService = trackService;
  }

}
