package nl.han.ica.oose.dea.spotitube.models;



import java.util.List;

public class PlaylistsModel {
    private  List<PlaylistModel> playlists;
    private int length;

    public List<PlaylistModel> getPlaylists() {
        return playlists;
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
