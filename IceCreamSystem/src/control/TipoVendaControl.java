package control;

import entidade.TipoVenda;
import exception.ControlException;
import exception.EntidadeException;
import java.sql.Connection;
import java.util.List;
import sql.Banco;
import util.Erro;

public class TipoVendaControl {
    
    Banco conSing = Banco.getInstancia();
    Connection con = conSing.getConexao();
    
    public int gravaTVenda(Integer codigo, String descricao) throws ControlException{
        TipoVenda tv = new TipoVenda();
        Erro e = new Erro();
        
        if(descricao==null || descricao.isEmpty())
            e.add("Preencha o campo descricao");
        if(descricao.length()>20)
            e.add("Campo descricao possui muitos caracteres");
        
        if(!e.isTemErro()){
            if(codigo!=0 && codigo!=null)
                tv.setCodigo(codigo);
            tv.setDescricao(descricao);
            try{
                if(tv.getCodigo()!=null && tv.getCodigo()!=0)
                    return tv.update(con);
                else
                    return tv.insert(con);
            }catch(EntidadeException ex){
                throw new ControlException(ex.getMessage());
            }
        }
        return 0;
    }
    
    public int excluiTVenda(Integer codigo, String descricao) throws ControlException{
        TipoVenda tv = new TipoVenda();
        Erro e = new Erro();
        
        if(codigo==null && codigo==0)
            e.add("Selecione um item para Excluir!");
        
        if(!e.isTemErro()){
            tv.setCodigo(codigo);
            tv.setDescricao(descricao);
            try{
                return tv.delete(con);
            }catch(EntidadeException ex){
                throw new ControlException(ex.getMessage());
            }
        }
        return 0;
    }
    
    public TipoVenda buscaTVenda(Integer codigo, String descricao) throws ControlException{
        TipoVenda tv = new TipoVenda();
        Erro e = new Erro();
        
        if(descricao==null || descricao.isEmpty())
            e.add("Preencha o campo Descricao");
        if(descricao.length()>20)
            e.add("Campo descricao possui muitos caracteres");
        
        if(!e.isTemErro()){
            if(codigo!=0 && codigo!=null)
                tv.setCodigo(codigo);
            tv.setDescricao(descricao);
            try{
                return tv.select(con);
            }catch(EntidadeException ex){
                throw new ControlException(ex.getMessage());
            }
        }
        return null;
    }
    
    public List<TipoVenda> listaTVenda(Integer codigo, String descricao) throws ControlException{
        TipoVenda tv = new TipoVenda();
        Erro e = new Erro();
        if(codigo!=0 && codigo!=null)
            tv.setCodigo(codigo);
        tv.setDescricao(descricao);
        try{
            return tv.listaTipos(con);
        }catch(EntidadeException ex){
            throw new ControlException(ex.getMessage());
        }
    }
}
