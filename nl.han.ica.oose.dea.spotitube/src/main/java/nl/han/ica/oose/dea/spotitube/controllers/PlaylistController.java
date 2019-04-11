package nl.han.ica.oose.dea.spotitube.controllers;

import nl.han.ica.oose.dea.spotitube.dto.PlaylistOverviewDto;
import nl.han.ica.oose.dea.spotitube.dto.PlaylistRequestDto;
import nl.han.ica.oose.dea.spotitube.dto.TrackOverviewDto;
import nl.han.ica.oose.dea.spotitube.dto.TrackRequestDto;
import nl.han.ica.oose.dea.spotitube.services.IPlaylistService;
import nl.han.ica.oose.dea.spotitube.services.ItrackService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistController {

  private IPlaylistService playlistService;
  private ItrackService trackService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getPlaylistOverview(@QueryParam("token") String token) {
    PlaylistOverviewDto overview = playlistService.getAllPlaylists(token);
    return Response.ok(overview).build();
  }

  @DELETE
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{id}")
  public Response deletePlaylist(
      @QueryParam("token") String token, @PathParam("id") int playListId) {
    PlaylistOverviewDto overview = playlistService.deletePlaylist(token, playListId);
    return Response.ok(overview).build();
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{id}")
  public Response changePlayList(@QueryParam("token") String token, PlaylistRequestDto request) {
    PlaylistOverviewDto overview = playlistService.changePlaylist(token, request);
    return Response.ok(overview).build();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response addPlaylist(@QueryParam("token") String token, PlaylistRequestDto request) {
    PlaylistOverviewDto overview = playlistService.addPlaylist(token, request);
    return Response.status(201).entity(overview).build();
  }

//TRACKS IN PLAYLIST

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{playlistId}/tracks")
  public Response playListTracks(@QueryParam("token") String token, @PathParam("playlistId") int playlistId) {
    TrackOverviewDto overview = trackService.getTracksForPlaylist(token, playlistId);
    return Response.ok(overview).build();
  }

  @DELETE
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{playlistId}/tracks/{trackId}")
  public Response deletePlaylistTrack(@QueryParam("token") String token, @PathParam("playlistId") int playlistId, @PathParam("trackId") int trackId) {
    TrackOverviewDto overview = trackService.deleteTrackInPlaylist(token, playlistId, trackId);
    return Response.ok(overview).build();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{playlistId}/tracks/")
  public Response addPlaylistTrack(@QueryParam("token") String token, TrackRequestDto trackRequestDto, @PathParam("playlistId") int playlistId) {
    TrackOverviewDto overview = trackService.addTrackToPlaylist(token, playlistId, trackRequestDto);
    return Response.status(201).entity(overview).build();
  }


  @Inject
  public void setTrackService(ItrackService trackService) {
    this.trackService = trackService;
  }
  @Inject
  public void setPlaylistService(IPlaylistService playlistService) {
    this.playlistService = playlistService;
  }

}
