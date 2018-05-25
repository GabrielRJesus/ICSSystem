package exception;

/**
 *
 * @author gabri
 */
public class EntidadeException extends Exception{
    
    public EntidadeException(){
        super();
    }
    
    public EntidadeException(String msg){
        super(msg);
    }
    
    public EntidadeException(String msg, Throwable cause) {
        super(msg, cause);
    }
    
}
