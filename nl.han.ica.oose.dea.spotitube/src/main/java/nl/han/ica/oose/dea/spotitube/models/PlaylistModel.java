package nl.han.ica.oose.dea.spotitube.models;

import nl.han.ica.oose.dea.spotitube.controllers.dto.TrackResponseDto;

import java.util.List;

public class PlaylistModel {
    private int id;
    private String name;
    private boolean owner;
    private List<TrackResponseDto> tracks;

    public PlaylistModel(){}
    public PlaylistModel(int id, String name, boolean owner, List<TrackResponseDto> tracks) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.tracks = tracks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public List<TrackResponseDto> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackResponseDto> tracks) {
        this.tracks = tracks;
    }



}
