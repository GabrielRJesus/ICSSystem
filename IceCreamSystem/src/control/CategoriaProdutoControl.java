package control;

import entidade.CategoriaProduto;
import exception.ControlException;
import exception.EntidadeException;
import java.sql.Connection;
import java.util.List;
import sql.Banco;
import util.Erro;

public class CategoriaProdutoControl {
    Banco conSing = Banco.getInstancia();
    Connection con = conSing.getConexao();
    
    public int gravaCategoria(Integer codigo, String descricao) throws ControlException{
        CategoriaProduto cp = new CategoriaProduto();
        Erro e = new Erro();
        
        if(descricao==null || descricao.isEmpty())
            e.add("Preencha o campo descricao");
        if(descricao.length()>45)
            e.add("Campo descricao possui muitos caracteres");
        
        if(!e.isTemErro()){
            if(codigo!=0 && codigo!=null)
                cp.setCodigo(codigo);
            cp.setDescricao(descricao);
            try{
                if(cp.getCodigo()!=null && cp.getCodigo()!=0)
                    return cp.update(con);
                else
                    return cp.insert(con);
            }catch(EntidadeException ex){
                throw new ControlException(ex.getMessage());
            }
        }
        return 0;
    }
    
    public int excluiCategoria(Integer codigo, String descricao) throws ControlException{
        CategoriaProduto cp = new CategoriaProduto();
        Erro e = new Erro();
        
        if(codigo==null && codigo==0)
            e.add("Selecione um item para Excluir!");
        
        if(!e.isTemErro()){
            cp.setCodigo(codigo);
            cp.setDescricao(descricao);
            try{
                return cp.delete(con);
            }catch(EntidadeException ex){
                throw new ControlException(ex.getMessage());
            }
        }
        return 0;
    }
    
    public CategoriaProduto buscaCategoria(Integer codigo, String descricao) throws ControlException{
        CategoriaProduto cp = new CategoriaProduto();
        Erro e = new Erro();
        
        if(descricao==null || descricao.isEmpty())
            e.add("Preencha o campo Descricao");
        if(descricao.length()>45)
            e.add("Campo descricao possui muitos caracteres");
        
        if(!e.isTemErro()){
            if(codigo!=0 && codigo!=null)
                cp.setCodigo(codigo);
            cp.setDescricao(descricao);
            try{
                return cp.select(con);
            }catch(EntidadeException ex){
                throw new ControlException(ex.getMessage());
            }
        }
        return null;
    }
    
    public List<CategoriaProduto> listaCategoria(Integer codigo, String descricao) throws ControlException{
        CategoriaProduto cp = new CategoriaProduto();
        Erro e = new Erro();
        if(codigo!=0 && codigo!=null)
            cp.setCodigo(codigo);
        cp.setDescricao(descricao);
        try{
            return cp.lista(con);
        }catch(EntidadeException ex){
            throw new ControlException(ex.getMessage());
        }
    }
    
}
