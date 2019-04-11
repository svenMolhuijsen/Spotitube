package nl.han.ica.oose.dea.spotitube.controllers;

import nl.han.ica.oose.dea.spotitube.dto.*;
import nl.han.ica.oose.dea.spotitube.services.ItrackService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.ws.rs.core.Response;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TrackControllerTest {
        public static final String TOKEN = "5674-2342-2343";

        //needed objects
        @Mock
        TrackController trackController;
        private ItrackService trackServiceMock;

        //tracks
        private TrackRequestDto trackRequestDto;
        private TrackOverviewDto trackOverviewDto = new TrackOverviewDto();
        private TrackResponseDto trackResponseDto;


        @BeforeEach
        void setup(){
            //SET MOCKS
            trackServiceMock = mock(ItrackService.class);
            trackController = new TrackController();
            trackController.setTrackService(trackServiceMock);

            //SET TRACKS
            var tracks = new ArrayList<TrackResponseDto>();
            tracks.add(new TrackResponseDto(1, "Sweet child o' mine", "Guns N'Roses", 418, "Appetite for Destruction", 10000, "2017-12-2", "342gfsghiufdhgsfg", true));
            trackOverviewDto.setTracks(tracks);
            }


        @Test
        public void availableTracksTest() {
            //SETUP
            when(trackServiceMock.getAvailableTracksForPlaylist(TOKEN, 1)).thenReturn(trackOverviewDto);
            //TEST
            //check if method runs
            Response response = trackController.availableTracks(TOKEN, 1);
            //VERIFY
            verify(trackServiceMock).getAvailableTracksForPlaylist(TOKEN, 1);

            //check correct status code
            assertEquals(Response.Status.OK.getStatusCode() , response.getStatus());
            //check if returned overview is same as expected overview
            assertEquals(trackOverviewDto, response.getEntity());
        }



  public void availableTracks() {}
}
