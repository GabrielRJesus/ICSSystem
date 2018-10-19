package entidade;

import DAO.ContasReceberDAO;
import exception.DAOException;
import exception.EntidadeException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContasReceber {
    
    private Integer codigo;
    private double valor;
    private Date dtVencimento;
    private double valorPago;
    private Date dtPagto;
    private Venda venda;
    
    private List<TPagamentoReceber> listaRecebimentos;

    public ContasReceber() {
        venda = new Venda();
        listaRecebimentos = new ArrayList<>();
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getDtVencimento() {
        return dtVencimento;
    }

    public void setDtVencimento(Date dtVencimento) {
        this.dtVencimento = dtVencimento;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public Date getDtPagto() {
        return dtPagto;
    }

    public void setDtPagto(Date dtPagto) {
        this.dtPagto = dtPagto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public List<TPagamentoReceber> getListaRecebimentos() {
        return listaRecebimentos;
    }

    public void setListaRecebimentos(List<TPagamentoReceber> listaRecebimentos) {
        this.listaRecebimentos = listaRecebimentos;
    }
    
    public int insert(Connection con) throws EntidadeException{
        try{
            return new ContasReceberDAO().insert(this, con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public int update(Connection con) throws EntidadeException{
        try{
            return new ContasReceberDAO().update(this, con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public int delete(Connection con) throws EntidadeException{
        try{
            return new ContasReceberDAO().delete(this, con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public ContasReceber select(Connection con) throws EntidadeException{
        try{
            return new ContasReceberDAO().select(this, con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public List<ContasReceber> lista(Connection con) throws EntidadeException{
        try{
            return new ContasReceberDAO().lista(this, con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
}
