package control;

import entidade.ContasPagar;
import entidade.TipoDespesas;
import entidade.TipoPagamento;
import exception.ControlException;
import exception.EntidadeException;
import java.sql.Connection;
import java.util.Date;
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
}
