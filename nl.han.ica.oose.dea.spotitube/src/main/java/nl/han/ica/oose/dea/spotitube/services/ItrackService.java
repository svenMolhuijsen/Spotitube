package nl.han.ica.oose.dea.spotitube.services;

import nl.han.ica.oose.dea.spotitube.dto.TrackRequestDto;
import nl.han.ica.oose.dea.spotitube.dto.TrackOverviewDto;

public interface ItrackService {

    TrackOverviewDto getTracksForPlaylist(String token, int id);

    TrackOverviewDto deleteTrackInPlaylist(String token, int playlisyId, int trackId);

    TrackOverviewDto addTrackToPlaylist(String token, int id, TrackRequestDto track);

    TrackOverviewDto getAvailableTracksForPlaylist(String token, int id);

}
