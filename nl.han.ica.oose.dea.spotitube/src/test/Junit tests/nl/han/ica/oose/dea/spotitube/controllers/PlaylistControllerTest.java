package nl.han.ica.oose.dea.spotitube.controllers;

import nl.han.ica.oose.dea.spotitube.dto.*;
import nl.han.ica.oose.dea.spotitube.models.PlaylistModel;
import nl.han.ica.oose.dea.spotitube.services.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.ws.rs.core.Response;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PlaylistControllerTest {
  public static final String TOKEN = "5674-2342-2343";

  //needed objects
  @Mock
  PlaylistController playlistController;
  private IPlaylistService playlistServiceMock;
  private ItrackService trackServiceMock;

  //tracks
  private TrackRequestDto trackRequestDto;
  private TrackOverviewDto trackOverviewDto = new TrackOverviewDto();
  private TrackResponseDto trackResponseDto;
  //playlists
  private PlaylistRequestDto playlistRequestDto;
  private PlaylistOverviewDto playlistOverviewDto = new PlaylistOverviewDto();
  private PlaylistModel playlistModel;

  @BeforeEach
  void setup(){
    //SET MOCKS
    playlistServiceMock = mock(IPlaylistService.class);
    trackServiceMock = mock(ItrackService.class);

    playlistController = new PlaylistController();
playlistController.setPlaylistService(playlistServiceMock);
playlistController.setTrackService(trackServiceMock);
    //SET TRACKS
    var tracks = new ArrayList<TrackResponseDto>();
    tracks.add(new TrackResponseDto(1, "Sweet child o' mine", "Guns N'Roses", 418, "Appetite for Destruction", 10000, "2017-12-2", "342gfsghiufdhgsfg", true));
    trackOverviewDto.setTracks(tracks);

    //SET PLAYLISTOVERVIEW
    var playlists = new ArrayList<PlaylistModel>();
    playlists.add(new PlaylistModel(1, "name", true, tracks));
    playlistOverviewDto.setPlaylists(playlists);
    playlistOverviewDto.setLength(418);
  }


  @Test
  public void getPlayListOverview() {
    //SETUP
    when(playlistServiceMock.getAllPlaylists(TOKEN)).thenReturn(playlistOverviewDto);
    //TEST
      //check if method runs
    Response response = playlistController.getPlaylistOverview(TOKEN);
    //VERIFY
    //check correct status code
    assertEquals(Response.Status.OK.getStatusCode() , response.getStatus());
      //check if returned overview is same as expected overview
    assertEquals(playlistOverviewDto, response.getEntity());
  }

  @Test
  public void deletePlaylist1() {
    //SETUP
    when(playlistServiceMock.deletePlaylist(TOKEN, 3)).thenReturn(playlistOverviewDto);
    //TEST
    //check if method runs
    Response response = playlistController.deletePlaylist(TOKEN, 3);
    //VERIFY
    //verify if mockobject is reached
    verify(playlistServiceMock).deletePlaylist(TOKEN, 3);
    //check correct status code
    assertEquals(Response.Status.OK.getStatusCode() , response.getStatus());
    //check if returned overview is same as expected overview
    assertEquals(playlistOverviewDto, response.getEntity());
  }

    @Test
  public void changePlayList() {
      //SETUP
      when(playlistServiceMock.changePlaylist(TOKEN, playlistRequestDto)).thenReturn(playlistOverviewDto);
      //TEST
      //check if method runs
      Response response = playlistController.changePlayList(TOKEN, playlistRequestDto);
      //VERIFY
      //verify if mockobject is reached
      verify(playlistServiceMock).changePlaylist(TOKEN, playlistRequestDto);
      //check correct status code
      assertEquals(Response.Status.OK.getStatusCode() , response.getStatus());
      //check if returned overview is same as expected overview
      assertEquals(playlistOverviewDto, response.getEntity());
    }

    @Test
  public void addPlaylist() {
      //SETUP
      when(playlistServiceMock.addPlaylist(TOKEN, playlistRequestDto)).thenReturn(playlistOverviewDto);
      //TEST
      //check if method runs
      Response response = playlistController.addPlaylist(TOKEN, playlistRequestDto);
      //VERIFY
      //verify if mockobject is reached
      verify(playlistServiceMock).addPlaylist(TOKEN, playlistRequestDto);
      //check correct status code
      assertEquals(Response.Status.CREATED.getStatusCode() , response.getStatus());
      //check if returned overview is same as expected overview
      assertEquals(playlistOverviewDto, response.getEntity());
    }

    @Test
  public void playListTracks() {
      //SETUP
      when(trackServiceMock.getTracksForPlaylist(TOKEN, 1)).thenReturn(trackOverviewDto);
      //TEST
      //check if method runs
      Response response = playlistController.playListTracks(TOKEN, 1);
      //VERIFY
      //verify if mockobject is reached
      verify(trackServiceMock).getTracksForPlaylist(TOKEN, 1);
      //check correct status code
      assertEquals(Response.Status.OK.getStatusCode() , response.getStatus());
      //check if returned overview is same as expected overview
      assertEquals(trackOverviewDto, response.getEntity());
    }

    @Test
  public void deletePlaylistTrack() {

        when(trackServiceMock.deleteTrackInPlaylist(TOKEN, 1, 1)).thenReturn(trackOverviewDto);
        //TEST
        //check if method runs
        Response response = playlistController.deletePlaylistTrack(TOKEN, 1, 1);
        //VERIFY
        //verify if mockobject is reached
        verify(trackServiceMock).deleteTrackInPlaylist(TOKEN, 1, 1);
        //check correct status code
        assertEquals(Response.Status.OK.getStatusCode() , response.getStatus());
        //check if returned overview is same as expected overview
        assertEquals(trackOverviewDto, response.getEntity());

    }

    @Test
  public void addPlaylistTrack() {
            when(trackServiceMock.addTrackToPlaylist(TOKEN, 1, trackRequestDto)).thenReturn(trackOverviewDto);
            //TEST
            //check if method runs
            Response response = playlistController.addPlaylistTrack(TOKEN, trackRequestDto, 1);
            //VERIFY
            //verify if mockobject is reached
            verify(trackServiceMock).addTrackToPlaylist(TOKEN, 1, trackRequestDto);
            //check correct status code
            assertEquals(Response.Status.CREATED.getStatusCode() , response.getStatus());
            //check if returned overview is same as expected overview
            assertEquals(trackOverviewDto, response.getEntity());
    }
}
