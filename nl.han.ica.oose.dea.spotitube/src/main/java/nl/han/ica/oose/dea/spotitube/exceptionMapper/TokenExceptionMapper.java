package nl.han.ica.oose.dea.spotitube.exceptionMapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class TokenExceptionMapper implements ExceptionMapper<TokenException> {

    public TokenExceptionMapper() {
    }

    @Override
    public Response toResponse(TokenException message) {
        return Response.status(Response.Status.FORBIDDEN)
                .entity(message)
                .build();
    }
}
