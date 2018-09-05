package control;

import entidade.ContasPagar;
import entidade.TipoDespesas;
import entidade.TipoPagamento;
import exception.ControlException;
import exception.EntidadeException;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import sql.Banco;

public class ContaPagarControl {
    
    Banco conSing = Banco.getInstancia();
    Connection con = conSing.getConexao();
    
    public int lancaContapagar(Integer codigo, Date data, double valor, TipoDespesas td, TipoPagamento tp)throws ControlException{
        ContasPagar cp = new ContasPagar();
        if(codigo!=null && codigo!=0)
            cp.setCodigo(codigo);
        cp.setData(data);
        cp.setValor(valor);
        cp.setParcela(0);
        cp.setTpd(td);
        cp.setTpp(tp);
        try{
            if(codigo!=null && codigo!=0)
                return cp.update(con);
            else
                return cp.insert(con);
        }catch(EntidadeException ex){
            throw new ControlException(ex.getMessage());
        }
    }
    
    public List<ContasPagar> listaContas(Integer codigo, Date data, TipoDespesas td, TipoPagamento tp) throws ControlException{
        ContasPagar cp = new ContasPagar();
        if(codigo!=null && codigo!=0)
            cp.setCodigo(codigo);
        if(data!=null)
            cp.setData(data);
        if(td!=null && td.getCodigo()!=null && td.getCodigo()!=0)
            cp.setTpd(td);
        if(tp!=null && tp.getCodigo()!=null && tp.getCodigo()!=0)
            cp.setTpp(tp);
        try{
            return cp.lista(con);
        }catch(EntidadeException ex){
            throw new ControlException(ex.getMessage());
        }
    }
    
    public void guardaSelecionado(ContasPagar cp){
        ContasPagar.setCpSelecionada(cp);
    }
}
