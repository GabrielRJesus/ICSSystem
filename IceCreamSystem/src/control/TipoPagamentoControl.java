package control;

import entidade.TipoPagamento;
import exception.ControlException;
import exception.EntidadeException;
import java.sql.Connection;
import java.util.List;
import sql.Banco;
import util.Erro;

public class TipoPagamentoControl {
    Banco conSing = Banco.getInstancia();
    Connection con = conSing.getConexao();
    
    public int gravaTPagamento(Integer codigo, String descricao) throws ControlException{
        TipoPagamento tp = new TipoPagamento();
        Erro e = new Erro();
        
        if(descricao==null || descricao.isEmpty())
            e.add("Preencha o campo descricao");
        if(descricao.length()>45)
            e.add("Campo descricao possui muitos caracteres");
        
        if(!e.isTemErro()){
            if(codigo!=0 && codigo!=null)
                tp.setCodigo(codigo);
            tp.setDescricao(descricao);
            try{
                if(tp.getCodigo()!=null && tp.getCodigo()!=0)
                    return tp.update(con);
                else
                    return tp.insert(con);
            }catch(EntidadeException ex){
                throw new ControlException(ex.getMessage());
            }
        }
        return 0;
    }
    
    public int excluiTPagamento(Integer codigo, String descricao) throws ControlException{
        TipoPagamento tp = new TipoPagamento();
        Erro e = new Erro();
        
        if(codigo==null && codigo==0)
            e.add("Selecione um item para Excluir!");
        
        if(!e.isTemErro()){
            tp.setCodigo(codigo);
            tp.setDescricao(descricao);
            try{
                return tp.delete(con);
            }catch(EntidadeException ex){
                throw new ControlException(ex.getMessage());
            }
        }
        return 0;
    }
    
    public TipoPagamento buscaTPagamento(Integer codigo, String descricao) throws ControlException{
        TipoPagamento tp = new TipoPagamento();
        Erro e = new Erro();
        
        if(descricao==null || descricao.isEmpty())
            e.add("Preencha o campo Descricao");
        if(descricao.length()>45)
            e.add("Campo descricao possui muitos caracteres");
        
        if(!e.isTemErro()){
            if(codigo!=0 && codigo!=null)
                tp.setCodigo(codigo);
            tp.setDescricao(descricao);
            try{
                return tp.select(con);
            }catch(EntidadeException ex){
                throw new ControlException(ex.getMessage());
            }
        }
        return null;
    }
    
    public List<TipoPagamento> listaTPagamento(Integer codigo, String descricao) throws ControlException{
        TipoPagamento tp = new TipoPagamento();
        Erro e = new Erro();
        if(codigo!=0 && codigo!=null)
            tp.setCodigo(codigo);
        tp.setDescricao(descricao);
        try{
            return tp.lista(con);
        }catch(EntidadeException ex){
            throw new ControlException(ex.getMessage());
        }
    }
}
