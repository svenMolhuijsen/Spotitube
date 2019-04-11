package nl.han.ica.oose.dea.spotitube.services;

import nl.han.ica.oose.dea.spotitube.datasources.ITrackDAO;
import nl.han.ica.oose.dea.spotitube.dto.*;
import nl.han.ica.oose.dea.spotitube.datasources.TrackDAO;
import nl.han.ica.oose.dea.spotitube.models.PlaylistModel;
import nl.han.ica.oose.dea.spotitube.models.PlaylistOverviewModel;
import nl.han.ica.oose.dea.spotitube.models.TrackModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TrackServiceTest {
    public static final String USERNAME = "s.molhuijsen@gmail.com";
    public static final String PASSWORD = "password";
    public static final String REAL_NAME = "Sven Molhuijsen";
    public static final String TOKEN = "5674-2342-2343";
    public static final int TOTALDURATION = 418;

    //REAL OBJECTS
    private ItrackService itrackService;

    //MOCKED OBJECTS
    private ITrackDAO trackDAOMock;
    private TokenServiceImp tokenServiceMock;

    //tracks
    private TrackRequestDto trackRequestDto;
    private TrackOverviewDto trackOverviewDto;
    private TrackResponseDto trackResponseDto;
    private TrackModel trackModel;
    private ArrayList<TrackModel> trackModelArrayList;

    //playlists
    private PlaylistRequestDto playlistRequestDto;
    private PlaylistOverviewDto playlistOverviewDto;
    private PlaylistModel genericPlaylistModel;
    private PlaylistOverviewModel playlistOverviewModel;



    @BeforeEach
    void setup() {
        trackDAOMock = mock(TrackDAO.class);
        tokenServiceMock = mock(TokenServiceImp.class);
        itrackService = new TrackService(trackDAOMock, tokenServiceMock);

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

        //SET TRACKMODELARRAYLIST
        trackModelArrayList = new ArrayList<TrackModel>();
        trackModel = new TrackModel(1, "Sweet child o' mine", "Guns N'Roses", 418, "Appetite for Destruction", 10000, "2017-12-2", "342gfsghiufdhgsfg", true);
        trackModelArrayList.add(trackModel);

        //SET TRACKREQUESTDTO
        trackRequestDto = new TrackRequestDto(1, "Sweet child o' mine", "Guns N'Roses", 418, "Appetite for Destruction", 10000, "2017-12-2", "342gfsghiufdhgsfg", true);;

    }

    @Test
    void getTracksForPlaylist(){
        //SETUP
        when(trackDAOMock.getPlaylistTracks(1)).thenReturn(trackModelArrayList);
        when(tokenServiceMock.createToken()).thenReturn(TOKEN);
        //TEST
        //check if method runs
         TrackOverviewDto result = itrackService.getTracksForPlaylist(TOKEN, 1);
        //VERIFY
        verify(trackDAOMock).getPlaylistTracks(1);
        //check if equals via overwritten equals method in objects
        assertEquals( trackOverviewDto, result);
        
    }

    @Test
    void deleteTrackInPlaylist(){
        //SETUP
        when(trackDAOMock.getPlaylistTracks(1)).thenReturn(trackModelArrayList);
        when(tokenServiceMock.createToken()).thenReturn(TOKEN);
        //TEST
        //check if method runs
        TrackOverviewDto result = itrackService.deleteTrackInPlaylist(TOKEN, 1, 1);
        //VERIFY
        verify(trackDAOMock).deletePlaylistTrack(1, 1);
        verify(trackDAOMock).getPlaylistTracks(1);
        //check if equals via overwritten equals method in objects
        assertEquals( trackOverviewDto, result);
    }

    @Test
    void addTrackToPlaylist(){
        //SETUP
        when(trackDAOMock.getPlaylistTracks(1)).thenReturn(trackModelArrayList);
        when(tokenServiceMock.createToken()).thenReturn(TOKEN);
        //TEST
        //check if method runs
        trackOverviewDto = itrackService.addTrackToPlaylist(TOKEN, 1, trackRequestDto);
        //VERIFY
        verify(trackDAOMock).addPlayListTrack(any(TrackModel.class), eq(1), eq(TOKEN));
        verify(trackDAOMock).getPlaylistTracks(1);
        //check if equals via overwritten equals method in objects
        assertEquals( trackModelArrayList, trackModelArrayList);
    }
    @Test
    void getAvailableTracksForPlaylist(){
        //SETUP
        when(trackDAOMock.getAvailableTracks(1)).thenReturn(trackModelArrayList);
        when(tokenServiceMock.createToken()).thenReturn(TOKEN);
        //TEST
        //check if method runs
        trackOverviewDto = itrackService.getAvailableTracksForPlaylist(TOKEN, 1);
        //VERIFY
        verify(trackDAOMock).getAvailableTracks(1);
        //check if equals via overwritten equals method in objects
        assertEquals( trackModelArrayList, trackModelArrayList);
    }
}
