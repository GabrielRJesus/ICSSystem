package entidade;

import DAO.TPagamentoReceberDAO;
import exception.DAOException;
import exception.EntidadeException;
import java.sql.Connection;

public class TPagamentoReceber {
    
    private TipoPagamento tpg;
    private double valor;

    public TPagamentoReceber() {
        tpg = new TipoPagamento();
    }
    
    public TipoPagamento getTpg() {
        return tpg;
    }

    public void setTpg(TipoPagamento tpg) {
        this.tpg = tpg;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public int insert(int chave, Connection con) throws EntidadeException{
        try{
            return new TPagamentoReceberDAO().insert(chave, this, con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
}
