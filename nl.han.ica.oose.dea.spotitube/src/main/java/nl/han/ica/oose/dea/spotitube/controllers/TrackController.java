package nl.han.ica.oose.dea.spotitube.controllers;

import nl.han.ica.oose.dea.spotitube.controllers.dto.PlaylistsresponseDto;
import nl.han.ica.oose.dea.spotitube.datasources.DataAccesObject;
import nl.han.ica.oose.dea.spotitube.models.PlaylistsModel;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackController extends DataAccesObject {

    @GET
    public Response playlists(@QueryParam("forPlaylist")int playlistId, @QueryParam("token") String token) {
        checkDao();
        PlaylistsModel playlists = playlistDao.getPlayLists(token);
        if(playlists.getPlaylists().size() == 0){
            return Response.status(403).build();
        }

        PlaylistsresponseDto response =  new PlaylistsresponseDto();
        response.setLength(playlists.getLength());
        response.setPlaylists(playlists.getPlaylists());
        return Response.ok().entity(response).build();
    }
}
