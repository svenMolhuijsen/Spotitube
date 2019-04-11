package nl.han.ica.oose.dea.spotitube.exceptionMapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DatabaseExceptionMapper implements ExceptionMapper<DatabaseException> {
    @Override
    public Response toResponse(DatabaseException e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("database error occured: "+ e.getMessage()).build();
    }
}
