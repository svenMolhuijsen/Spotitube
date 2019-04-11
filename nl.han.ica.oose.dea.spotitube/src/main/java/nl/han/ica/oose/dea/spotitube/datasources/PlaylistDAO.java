package nl.han.ica.oose.dea.spotitube.datasources;

import nl.han.ica.oose.dea.spotitube.dto.TrackResponseDto;
import nl.han.ica.oose.dea.spotitube.exceptionMapper.DatabaseException;
import nl.han.ica.oose.dea.spotitube.models.PlaylistModel;
import nl.han.ica.oose.dea.spotitube.models.PlaylistOverviewModel;

import javax.enterprise.inject.Default;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Default
public class PlaylistDAO extends DataAccesObject implements IPlaylistDAO {

  @Override
  public PlaylistOverviewModel getPlayLists(String token) {
    var model = new PlaylistOverviewModel();
    try {
      var connection = getConnection();
      var st =
          connection.prepareStatement(
              "SELECT PLAYLISTID, NAME, (select 1 from USER U where P.USERNAME = U.USERNAME AND U.TOKEN = ?) AS OWNER,"
                  + " (select SUM(T1.duration) from track T1 inner join playlist_bevat_tracks PT on T1.trackID = PT.trackid where PT.playlistid = P.playlistid) AS DURATION"
                  + " FROM playlist P");
      st.setString(1, token);
      st.executeQuery();
      ResultSet rs = st.executeQuery();

      List<PlaylistModel> playlists = new ArrayList<>();
      var totalDuration = 0;
      while (rs.next()) {
        var playlistModel =
            new PlaylistModel(
                rs.getInt("PLAYLISTID"),
                rs.getString("NAME"),
                rs.getBoolean("OWNER"),
                new ArrayList<TrackResponseDto>());
        totalDuration += rs.getInt("DURATION");
        playlists.add(playlistModel);
      }
      model.setPlaylists(playlists);
      model.setLength(totalDuration);
    } catch (SQLException e) {
      throw new DatabaseException(e, "error getting playlists");
    }
    return model;
  }

  @Override
  public void deletePlayList(int id) {
    try {
      var connection = getConnection();

      var st =
          connection.prepareStatement("DELETE FROM playlist_bevat_tracks where PLAYLISTID = ?");
      st.setInt(1, id);
      st.executeUpdate();

      st = connection.prepareStatement("DELETE FROM playlist where PLAYLISTID = ?");
      st.setInt(1, id);
      st.executeUpdate();

    } catch (SQLException e) {
      throw new DatabaseException(e, "error deleting playlist");
    }
  }

  @Override
  public void addPlayList(String token, PlaylistModel playlistModel) {
    try {
      var connection = getConnection();
      var st =
          connection.prepareStatement(
              "INSERT INTO playlist (name, username) VALUES (?,(SELECT U.username FROM USER U WHERE U.token = ?))");
      st.setString(1, playlistModel.getName());
      st.setString(2, token);
      st.executeUpdate();
    } catch (SQLException e) {
      throw new DatabaseException(e, "error adding playlist");
    }
  }

  @Override
  public void changePlayList(PlaylistModel playlistModel) {
    try {
      var connection = getConnection();
      var statement =
          connection.prepareStatement("UPDATE playlist SET name = ? WHERE PLAYLISTID = ?");
      statement.setString(1, playlistModel.getName());
      statement.setInt(2, playlistModel.getId());
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new DatabaseException(e, "error changing playlist");
    }
  }
}
