package nl.han.ica.oose.dea.spotitube.controllers;

import nl.han.ica.oose.dea.spotitube.controllers.dto.LoginRequestDto;
import nl.han.ica.oose.dea.spotitube.controllers.dto.LoginResponseDto;
import nl.han.ica.oose.dea.spotitube.datasources.LoginDAO;
import nl.han.ica.oose.dea.spotitube.models.UserModel;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Console;

@Path("/login")
public class LoginController {
    LoginDAO loginDao;

    public void checkDao(){
        if (loginDao == null) {
            loginDao = new LoginDAO();
        }
    }
    @GET
    public String hello(){
        return "hello world";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(LoginRequestDto request) {
        System.out.println(request.getUser());
        checkDao();
        UserModel login = loginDao.login(request.getUser(), request.getPassword() );
        System.out.println(request.getUser() +request.getPassword());
        if(login.getToken() == null){
            return Response.status(403).build();
        }

        LoginResponseDto response =  new LoginResponseDto();
        response.setToken(login.getToken());
        response.setUser(login.getFullName());

        return Response.ok().entity(response).build();
    }


    public void setLoginDAO(LoginDAO loginDao) {
this.loginDao = loginDao;
    }
}
