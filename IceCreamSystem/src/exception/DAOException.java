package exception;

/**
 *
 * @author gabri
 * 
 */
public class DAOException extends Exception {

    public DAOException(String msg) {
        super(msg);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }

    public DAOException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
