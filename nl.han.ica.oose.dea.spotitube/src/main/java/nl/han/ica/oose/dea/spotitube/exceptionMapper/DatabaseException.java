package nl.han.ica.oose.dea.spotitube.exceptionMapper;

import java.sql.SQLException;

public class DatabaseException extends RuntimeException{
    public DatabaseException(Throwable e, String message) {
        super(message, e);
    }
}
