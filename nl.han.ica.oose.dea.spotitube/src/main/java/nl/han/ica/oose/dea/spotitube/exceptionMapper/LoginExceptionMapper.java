package nl.han.ica.oose.dea.spotitube.exceptionMapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class LoginExceptionMapper implements ExceptionMapper<LoginException> {
    public LoginExceptionMapper() {
    }

    @Override
    public Response toResponse(LoginException message) {
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity("Login not allowed" + message.getMessage())
                .build();
    }
}
