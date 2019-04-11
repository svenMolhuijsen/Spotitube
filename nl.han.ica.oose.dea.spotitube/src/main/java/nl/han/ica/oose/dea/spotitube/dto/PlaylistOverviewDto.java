package nl.han.ica.oose.dea.spotitube.dto;

import nl.han.ica.oose.dea.spotitube.models.PlaylistModel;

import java.util.List;
import java.util.Objects;

public class PlaylistOverviewDto {
  private List<PlaylistModel> playlists;
  private int length;

  public List<PlaylistModel> getPlaylists() {
    return playlists;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof PlaylistOverviewDto)) return false;
    PlaylistOverviewDto that = (PlaylistOverviewDto) o;
    return getLength() == that.getLength() &&
            getPlaylists().equals(that.getPlaylists());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getPlaylists(), getLength());
  }

  public void setPlaylists(List<PlaylistModel> playlists) {
    this.playlists = playlists;
  }

  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.length = length;
  }
}
