package nl.han.ica.oose.dea.spotitube.services;

import nl.han.ica.oose.dea.spotitube.datasources.IPlaylistDAO;
import nl.han.ica.oose.dea.spotitube.datasources.ITrackDAO;
import nl.han.ica.oose.dea.spotitube.dto.*;
import nl.han.ica.oose.dea.spotitube.datasources.PlaylistDAO;
import nl.han.ica.oose.dea.spotitube.datasources.TrackDAO;
import nl.han.ica.oose.dea.spotitube.models.PlaylistModel;
import nl.han.ica.oose.dea.spotitube.models.PlaylistOverviewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PlaylistServiceTest {
    public static final String USERNAME = "s.molhuijsen@gmail.com";
    public static final String PASSWORD = "password";
    public static final String REAL_NAME = "Sven Molhuijsen";
    public static final String TOKEN = "5674-2342-2343";
    public static final int TOTALDURATION = 418;


//REQUIRED SERVICES
    private IPlaylistService playlistService;
    private IPlaylistDAO playlistDAO;
    private ITrackDAO trackDAO;
    private TokenServiceImp tokenService;

    //tracks
    private TrackRequestDto trackRequestDto;
    private TrackOverviewDto trackOverviewDto;
    private TrackResponseDto trackResponseDto;


    //playlists
    private PlaylistRequestDto playlistRequestDto;
    private PlaylistOverviewDto playlistOverviewDto;
    private PlaylistModel genericPlaylistModel;
    private PlaylistOverviewModel playlistOverviewModel;



    @BeforeEach
    void setup() {
        playlistDAO = mock(PlaylistDAO.class);
        trackDAO = mock(TrackDAO.class);
        tokenService = mock(TokenServiceImp.class);
        playlistService = new PlaylistServiceImp(playlistDAO, trackDAO, tokenService);


        //SET TRACKS
        trackOverviewDto = new TrackOverviewDto();
        var tracks = new ArrayList<TrackResponseDto>();
        tracks.add(new TrackResponseDto(1, "Sweet child o' mine", "Guns N'Roses", 418, "Appetite for Destruction", 10000, "2017-12-2", "342gfsghiufdhgsfg", true));
        trackOverviewDto.setTracks(tracks);

        //SET PLAYLISTREQUESTDTO
        playlistRequestDto = new PlaylistRequestDto();
        playlistRequestDto.setId(1);
        playlistRequestDto.setName("trashy pop");
        playlistRequestDto.setOwner(true);
        //SET GENERIC PLAYLISTMODEL
        genericPlaylistModel = new PlaylistModel(1, "Trashy pop", true, tracks);

        //SET PLAYLISTOVERVIEW
        playlistOverviewDto = new PlaylistOverviewDto();
        var playlists = new ArrayList<PlaylistModel>();
        playlists.add(genericPlaylistModel);
        playlistOverviewDto.setPlaylists(playlists);
        playlistOverviewDto.setLength(418);

        //SET PLAYLISTMODEL
        var playlistModelArrayList = new ArrayList<PlaylistModel>();
        playlistModelArrayList.add(genericPlaylistModel);
        playlistOverviewModel= new PlaylistOverviewModel();
        playlistOverviewModel.setPlaylists(playlistModelArrayList);
        playlistOverviewModel.setLength(418);
    }

    @Test
    public void getAllPlaylists() {
        //SETUP
        when(playlistDAO.getPlayLists(TOKEN)).thenReturn(playlistOverviewModel);
        when(tokenService.createToken()).thenReturn(TOKEN);
        //TEST
        //check if method runs
        PlaylistOverviewDto result = playlistService.getAllPlaylists(TOKEN);
        //VERIFY
        //check if equals via overwritten equals method in objects
        assertEquals( playlistOverviewDto, result);
    }

    @Test
    public void deletePlaylist() {
        //SETUP
        when(playlistDAO.getPlayLists(TOKEN)).thenReturn(playlistOverviewModel);
        when(tokenService.createToken()).thenReturn(TOKEN);
        //TEST
        //check if method runs
        PlaylistOverviewDto result = playlistService.deletePlaylist(TOKEN, 1);
        //VERIFY
        verify(playlistDAO).deletePlayList(1);
        //check if equals via overwritten equals method in objects
        assertEquals( playlistOverviewDto, result);
    }

    @Test
    public void addPlaylist() {
        //SETUP
        when(playlistDAO.getPlayLists(TOKEN)).thenReturn(playlistOverviewModel);
        when(tokenService.createToken()).thenReturn(TOKEN);
        //TEST
        //check if method runs
        PlaylistOverviewDto result = playlistService.addPlaylist(TOKEN, playlistRequestDto);
        //VERIFY
        verify(playlistDAO).addPlayList(eq(TOKEN), any(PlaylistModel.class));
        //check if equals via overwritten equals method in objects
        assertEquals( playlistOverviewDto, result);
    }

    @Test
    public void changePlaylist() {
        //SETUP
        when(playlistDAO.getPlayLists(TOKEN)).thenReturn(playlistOverviewModel);
        when(tokenService.createToken()).thenReturn(TOKEN);
        //TEST
        //check if method runs
        PlaylistOverviewDto result = playlistService.changePlaylist(TOKEN, playlistRequestDto);
        //VERIFY
        verify(playlistDAO).changePlayList(any(PlaylistModel.class));
        //check if equals via overwritten equals method in objects
        assertEquals( playlistOverviewDto, result);
    }
}
