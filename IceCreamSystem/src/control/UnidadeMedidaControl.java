package control;

import entidade.UnidadeMedida;
import exception.ControlException;
import exception.EntidadeException;
import java.sql.Connection;
import java.util.List;
import sql.Banco;
import util.Erro;

public class UnidadeMedidaControl {
    
    Banco conSing = Banco.getInstancia();
    Connection con = conSing.getConexao();
    
    public int gravaUM(Integer codigo, String descricao, String sigla) throws ControlException{
        UnidadeMedida u = new UnidadeMedida();
        Erro e = new Erro();
        
        if(descricao==null || descricao.isEmpty())
            e.add("Preencha o campo descrição");
        if(descricao.length()>30)
            e.add("Campo descrição possui muitos caracteres");
        if(sigla==null || sigla.isEmpty())
            e.add("Preencha o campo abreviação");
        if(sigla.length()>5)
            e.add("Campo abreviação possui muitos caracteres");
        
        if(!e.isTemErro()){
            if(codigo!=0 && codigo!=null)
                u.setCodigo(codigo);
            u.setDescricao(descricao);
            u.setAbreviacao(sigla);
            try{
                if(u.getCodigo()!=null && u.getCodigo()!=0)
                    return u.update(con);
                else
                    return u.insert(con);
            }catch(EntidadeException ex){
                throw new ControlException(ex.getMessage());
            }
        }
        return 0;
    }
    
    public int excluiUM(Integer codigo, String descricao, String sigla) throws ControlException{
        UnidadeMedida u = new UnidadeMedida();
        Erro e = new Erro();
        u.setCodigo(codigo);
        u.setDescricao(descricao);
        u.setAbreviacao(sigla);
        try{
            return u.delete(con);
        }catch(EntidadeException ex){
            throw new ControlException(ex.getMessage());
        }
    }
    
    public UnidadeMedida buscaUM(Integer codigo, String descricao, String sigla) throws ControlException{
        UnidadeMedida u = new UnidadeMedida();
        Erro e = new Erro();
        
        if(descricao==null || descricao.isEmpty())
            e.add("Preencha o campo descrição");
        if(descricao.length()>30)
            e.add("Campo descrição possui muitos caracteres");
        if(sigla==null || sigla.isEmpty())
            e.add("Preencha o campo abreviação");
        if(sigla.length()>5)
            e.add("Campo abreviação possui muitos caracteres");
        
        if(!e.isTemErro()){
            if(codigo!=0 && codigo!=null)
                u.setCodigo(codigo);
            u.setDescricao(descricao);
            u.setAbreviacao(sigla);
            try{
                return u.select(con);
            }catch(EntidadeException ex){
                throw new ControlException(ex.getMessage());
            }
        }
        return null;
    }
    
    public List<UnidadeMedida> listaUM(Integer codigo, String descricao, String sigla) throws ControlException{
        UnidadeMedida u = new UnidadeMedida();
        Erro e = new Erro();
        if(codigo!=0 && codigo!=null)
            u.setCodigo(codigo);
        u.setDescricao(descricao);
        u.setAbreviacao(sigla);
        try{
            return u.listaUM(con);
        }catch(EntidadeException ex){
            throw new ControlException(ex.getMessage());
        }
    }
    
}
