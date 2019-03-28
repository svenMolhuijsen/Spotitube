package nl.han.ica.oose.dea.spotitube.datasources;

import nl.han.ica.oose.dea.spotitube.models.TrackModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackDAO extends DataAccesObject {
  public List<TrackModel> getAvailableTracks(int playlistId) {
    try {
      var cnTrack = getConnection();
      var st =
          cnTrack.prepareStatement(
              "SELECT trackid, title, performer, duration, album, playcount, publicationdate, description, offlineavailable from track where TRACKID not in (select TRACKID from playlist_bevat_tracks where PLAYLISTID = ?)");
      st.setInt(1, playlistId);
      ResultSet rs = st.executeQuery();
      return getTrackModels(rs);
    } catch (SQLException e) {
      System.out.println("Error getting results " + e);
    }
    return null;
  }

  public List<TrackModel> getPlaylistTracks(int playlistId) {
    try {
      var cnTrack = getConnection();
      var st =
          cnTrack.prepareStatement(
              "SELECT trackid, title, performer, duration, album, playcount, publicationdate, description, offlineavailable from track where TRACKID in (select TRACKID from playlist_bevat_tracks where PLAYLISTID = ?)");
      st.setInt(1, playlistId);
      ResultSet rs = st.executeQuery();
      System.out.println(playlistId);
      return getTrackModels(rs);
    } catch (SQLException e) {
      System.out.println("Error getting results " + e);
    }
    return null;
  }

  private List<TrackModel> getTrackModels(ResultSet rs) throws SQLException {
    List<TrackModel> tracks = new ArrayList<>();
    while (rs.next()) {
      tracks.add(fillTrackModel(rs));
    }
    return tracks;
  }

  private TrackModel fillTrackModel(ResultSet rs) throws SQLException {
    return new TrackModel(
        rs.getInt("trackid"),
        rs.getString("title"),
        rs.getString("performer"),
        rs.getInt("duration"),
        rs.getString("album"),
        rs.getInt("playcount"),
        rs.getString("publicationdate"),
        rs.getString("description"),
        rs.getBoolean("offlineavailable"));
  }

  public void deletePlaylistTrack(int playlistId, int trackId) {
    try {
      var connection = getConnection();
      var statement =
          connection.prepareStatement(
              "DELETE FROM playlist_bevat_tracks where PLAYLISTID = ? AND TRACKID = ?");
      statement.setInt(1, playlistId);
      statement.setInt(2, trackId);
      statement.executeUpdate();
    } catch (SQLException e) {
      System.out.println("Error deleting record" + e);
    }
  }

  public void addPlayListTrack(TrackModel trackModel, int playlistId, String token) {
    var connection = getConnection();
    try {

      var statement =
          connection.prepareStatement(
              "insert into playlist_bevat_tracks (playlistid, trackid) value (?,?)");
      statement.setInt(1, playlistId);
      statement.setInt(2, trackModel.getId());
      statement.executeUpdate();

      var statement2 =
          connection.prepareStatement("UPDATE track SET OFFLINEAVAILABLE = ? WHERE TRACKID = ?");
      statement2.setBoolean(1, trackModel.getOfflineAvailable());
      statement2.setInt(2, trackModel.getId());
      statement2.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
