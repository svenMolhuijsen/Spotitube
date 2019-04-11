package nl.han.ica.oose.dea.spotitube.models;

import nl.han.ica.oose.dea.spotitube.dto.TrackResponseDto;

import java.util.List;
import java.util.Objects;

public class PlaylistModel {
  private int id;
  private String name;
  private boolean owner;
  private List<TrackResponseDto> tracks;

  public PlaylistModel() {}

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof PlaylistModel)) return false;
    PlaylistModel that = (PlaylistModel) o;
    return getId() == that.getId() &&
            isOwner() == that.isOwner() &&
            getName().equals(that.getName()) &&
            getTracks().equals(that.getTracks());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName(), isOwner(), getTracks());
  }

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
