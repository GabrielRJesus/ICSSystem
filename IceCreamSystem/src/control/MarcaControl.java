package control;

import entidade.Marca;
import exception.ControlException;
import exception.EntidadeException;
import java.sql.Connection;
import java.util.List;
import sql.Banco;
import util.Erro;

public class MarcaControl {
    
    Banco conSing = Banco.getInstancia();
    Connection con = conSing.getConexao();
    
    public int gravaMarca(Integer codigo, String marca) throws ControlException{
        Marca m = new Marca();
        Erro e = new Erro();
        
        if(marca==null || marca.isEmpty())
            e.add("Preencha o campo marca");
        if(marca.length()>40)
            e.add("Campo marca possui muitos caracteres");
        
        if(!e.isTemErro()){
            if(codigo!=0 && codigo!=null)
                m.setCodigo(codigo);
            m.setNome(marca);
            try{
                if(m.getCodigo()!=null && m.getCodigo()!=0)
                    return m.update(con);
                else
                    return m.insert(con);
            }catch(EntidadeException ex){
                throw new ControlException(ex.getMessage());
            }
        }
        return 0;
    }
    
    public int excluiMarca(Integer codigo, String marca) throws ControlException{
        Marca m = new Marca();
        Erro e = new Erro();
        m.setCodigo(codigo);
        m.setNome(marca);
        try{
            return m.delete(con);
        }catch(EntidadeException ex){
            throw new ControlException(ex.getMessage());
        }
    }
    
    public Marca buscaMarca(Integer codigo, String marca) throws ControlException{
        Marca m = new Marca();
        Erro e = new Erro();
        
        if(marca==null || marca.isEmpty())
            e.add("Preencha o campo marca");
        if(marca.length()>40)
            e.add("Campo marca possui muitos caracteres");
        
        if(!e.isTemErro()){
            if(codigo!=0 && codigo!=null)
                m.setCodigo(codigo);
            m.setNome(marca);
            try{
                return m.select(con);
            }catch(EntidadeException ex){
                throw new ControlException(ex.getMessage());
            }
        }
        return null;
    }
    
    public List<Marca> listaMarca(Integer codigo, String marca) throws ControlException{
        Marca m = new Marca();
        Erro e = new Erro();
        if(codigo!=0 && codigo!=null)
            m.setCodigo(codigo);
        m.setNome(marca);
        try{
            return m.lista(con);
        }catch(EntidadeException ex){
            throw new ControlException(ex.getMessage());
        }
    }
}
