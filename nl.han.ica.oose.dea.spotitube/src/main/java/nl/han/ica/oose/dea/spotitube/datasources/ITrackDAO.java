package nl.han.ica.oose.dea.spotitube.datasources;

import nl.han.ica.oose.dea.spotitube.models.TrackModel;

import java.util.List;

public interface ITrackDAO {
    List<TrackModel> getAvailableTracks(int playlistId);

    List<TrackModel> getPlaylistTracks(int playlistId);

    void deletePlaylistTrack(int playlistId, int trackId);

    void addPlayListTrack(TrackModel trackModel, int playlistId, String token);
}
