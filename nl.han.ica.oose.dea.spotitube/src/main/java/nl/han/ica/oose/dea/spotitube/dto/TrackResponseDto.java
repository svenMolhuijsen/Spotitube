package nl.han.ica.oose.dea.spotitube.dto;

import java.util.Objects;

public class TrackResponseDto {
  private int id;
  private String title;
  private String performer;
  private int duration;
  private String album;
  private int playcount;
  private String publicationDate;
  private String description;
  private Boolean offlineAvailable;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof TrackResponseDto)) return false;
    TrackResponseDto that = (TrackResponseDto) o;
    return getId() == that.getId() &&
            getDuration() == that.getDuration() &&
            getPlaycount() == that.getPlaycount() &&
            getTitle().equals(that.getTitle()) &&
            getPerformer().equals(that.getPerformer()) &&
            getAlbum().equals(that.getAlbum()) &&
            getPublicationDate().equals(that.getPublicationDate()) &&
            getDescription().equals(that.getDescription()) &&
            getOfflineAvailable().equals(that.getOfflineAvailable());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getTitle(), getPerformer(), getDuration(), getAlbum(), getPlaycount(), getPublicationDate(), getDescription(), getOfflineAvailable());
  }

  public TrackResponseDto() {}

  public TrackResponseDto(
      int id,
      String title,
      String performer,
      int duration,
      String album,
      int playcount,
      String publicationDate,
      String description,
      Boolean offlineAvailable) {
    this.id = id;
    this.title = title;
    this.performer = performer;
    this.duration = duration;
    this.album = album;
    this.playcount = playcount;
    this.publicationDate = publicationDate;
    this.description = description;
    this.offlineAvailable = offlineAvailable;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getPerformer() {
    return performer;
  }

  public void setPerformer(String performer) {
    this.performer = performer;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public String getAlbum() {
    return album;
  }

  public void setAlbum(String album) {
    this.album = album;
  }

  public int getPlaycount() {
    return playcount;
  }

  public void setPlaycount(int playcount) {
    this.playcount = playcount;
  }

  public String getPublicationDate() {
    return publicationDate;
  }

  public void setPublicationDate(String publicationDate) {
    this.publicationDate = publicationDate;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Boolean getOfflineAvailable() {
    return offlineAvailable;
  }

  public void setOfflineAvailable(Boolean offlineAvailable) {
    this.offlineAvailable = offlineAvailable;
  }
}
