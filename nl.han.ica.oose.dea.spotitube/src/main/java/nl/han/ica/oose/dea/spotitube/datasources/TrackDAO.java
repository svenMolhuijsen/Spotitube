package nl.han.ica.oose.dea.spotitube.datasources;

import nl.han.ica.oose.dea.spotitube.exceptionMapper.DatabaseException;
import nl.han.ica.oose.dea.spotitube.models.TrackModel;

import javax.enterprise.inject.Default;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Default
public class TrackDAO extends DataAccesObject implements ITrackDAO {
  @Override
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
      throw new DatabaseException(e, "error: cant get available tracks");
    }
  }

  @Override
  public List<TrackModel> getPlaylistTracks(int playlistId) {
    try {
      var cnTrack = getConnection();
      var st =
          cnTrack.prepareStatement(
              "SELECT trackid, title, performer, duration, album, playcount, publicationdate, description, offlineavailable from track where TRACKID in (select TRACKID from playlist_bevat_tracks where PLAYLISTID = ?)");
      st.setInt(1, playlistId);
      ResultSet rs = st.executeQuery();
      return getTrackModels(rs);
    } catch (SQLException e) {
      throw new DatabaseException(e, "error: cant get tracks");
    }
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

  @Override
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
      throw new DatabaseException(e, "error: deleting playlisttracks failed");
    }
  }

  @Override
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
      throw new DatabaseException(e, "error: cant add track to playlist");
    }
  }
}
