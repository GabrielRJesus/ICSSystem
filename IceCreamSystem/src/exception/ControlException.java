package exception;

import util.Erro;

/**
 *
 * @author gabri
 */
public class ControlException extends Exception{

    private Erro e;
    
    public ControlException(String message) {
        super(message);
    }

    public ControlException(Throwable cause) {
        super(cause);
    }

    public ControlException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public ControlException(Erro e){
        this.e = e;
    }

    public Erro getErro() {
        return e;
    }
    
    
}
