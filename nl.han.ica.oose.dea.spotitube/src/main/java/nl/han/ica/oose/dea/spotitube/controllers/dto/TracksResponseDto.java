package nl.han.ica.oose.dea.spotitube.controllers.dto;

import java.util.ArrayList;
import java.util.List;

public class TracksResponseDto {
    List<TrackResponseDto> tracks;

    public List<TrackResponseDto> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackResponseDto> tracks) {
        this.tracks = tracks;
    }



}
