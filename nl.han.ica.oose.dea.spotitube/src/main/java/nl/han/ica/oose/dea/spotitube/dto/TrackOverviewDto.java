package nl.han.ica.oose.dea.spotitube.dto;

import java.util.List;
import java.util.Objects;

public class TrackOverviewDto {
  List<TrackResponseDto> tracks;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof TrackOverviewDto)) return false;
    TrackOverviewDto that = (TrackOverviewDto) o;

    return (getTracks().size() == that.getTracks().size());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getTracks());
  }

  public List<TrackResponseDto> getTracks() {
    return tracks;
  }

  public void setTracks(List<TrackResponseDto> tracks) {
    this.tracks = tracks;
  }
}
