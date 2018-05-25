package util;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author gabri
 */
public final class Erro{
    
    private final ArrayList<String> mensagens;
    private final ArrayList<String> idCampos;
    
    public Erro() 
    {
        this.mensagens = new ArrayList<>();
        this.idCampos = new ArrayList<>();
    }

    public Erro(String mensagem) 
    {
        this();
        add(mensagem);
    }

    public ArrayList<String> getMensagens() 
    {
        return mensagens;
    }
    
    public ArrayList<String> getIdCampos(){
        return idCampos;
    }
    
    public void add(String mensagem) 
    {
        mensagens.add(mensagem);
    }

    public boolean possuiErro() 
    {
        return !mensagens.isEmpty();
    }

    public boolean isTemErro() 
    {
        return possuiErro();
    }
}
