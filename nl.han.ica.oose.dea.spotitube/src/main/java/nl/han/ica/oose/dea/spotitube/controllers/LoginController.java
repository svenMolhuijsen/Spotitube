package nl.han.ica.oose.dea.spotitube.controllers;

import nl.han.ica.oose.dea.spotitube.controllers.dto.LoginRequestDto;
import nl.han.ica.oose.dea.spotitube.controllers.dto.LoginResponseDto;
import nl.han.ica.oose.dea.spotitube.datasources.LoginDAO;
import nl.han.ica.oose.dea.spotitube.models.UserModel;
import nl.han.ica.oose.dea.spotitube.services.ILoginService;
import nl.han.ica.oose.dea.spotitube.services.TokenService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {
  private ILoginService loginService;

  public LoginController(){};

  @Inject
  public LoginController(ILoginService loginService) {
    this.loginService = loginService;
  }


  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response login(LoginRequestDto request) {
    LoginResponseDto response = loginService.login(request);
    return Response.ok().entity(response).build();
  }
}
