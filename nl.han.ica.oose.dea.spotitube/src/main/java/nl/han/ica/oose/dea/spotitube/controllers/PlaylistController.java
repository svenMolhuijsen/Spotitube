package nl.han.ica.oose.dea.spotitube.controllers;

import nl.han.ica.oose.dea.spotitube.controllers.dto.LoginRequestDto;
import nl.han.ica.oose.dea.spotitube.controllers.dto.PlaylistRequestDto;
import nl.han.ica.oose.dea.spotitube.controllers.dto.PlaylistsresponseDto;
import nl.han.ica.oose.dea.spotitube.datasources.PlaylistDAO;
import nl.han.ica.oose.dea.spotitube.models.PlaylistModel;
import nl.han.ica.oose.dea.spotitube.models.PlaylistsModel;
import nl.han.ica.oose.dea.spotitube.models.UserModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistController {
    PlaylistDAO playlistDao;

    public void checkDao(){
        if (playlistDao == null) {
            playlistDao = new PlaylistDAO();
        }
    }
    @GET
    public Response playlists(@QueryParam("token") String token) {
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

    @DELETE
    @Path("{id}")
    public Response playlists(@QueryParam("token") String token, @PathParam("id") int id) {
        checkDao();
        playlistDao.deletePlayLists(token, id);

        return playlists(token);
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response playlists(@QueryParam("token") String token, PlaylistRequestDto request) {
        checkDao();
        PlaylistModel playlistModel = new PlaylistModel(request.getId(),request.getName(),false,  request.getTracks());
        playlistDao.addPlayList(token, playlistModel);
        return playlists(token);
    }


}



