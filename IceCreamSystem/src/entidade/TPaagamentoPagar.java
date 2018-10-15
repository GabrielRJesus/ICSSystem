package entidade;

import DAO.TPaagamentoPagarDAO;
import exception.DAOException;
import exception.EntidadeException;
import java.sql.Connection;

public class TPaagamentoPagar {
    
    private TipoPagamento tpg;
    private double valor;

    public TPaagamentoPagar() {
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
            return new TPaagamentoPagarDAO().insert(chave, this, con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
}
