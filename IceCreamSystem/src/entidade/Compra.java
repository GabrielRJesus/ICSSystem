package entidade;

import DAO.CompraDAO;
import exception.DAOException;
import exception.EntidadeException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Compra {
    private Integer codigo;
    private Date data;
    private double valor;
    private Fornecedor forn;
    
    private List<ItensCompra> produtosCompra;

    public Compra() {
        forn = new Fornecedor();
        produtosCompra = new ArrayList<>();
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Fornecedor getForn() {
        return forn;
    }

    public void setForn(Fornecedor forn) {
        this.forn = forn;
    }

    public List<ItensCompra> getProdutosCompra() {
        return produtosCompra;
    }

    public void setProdutosCompra(List<ItensCompra> produtosCompra) {
        this.produtosCompra = produtosCompra;
    }
    
    public int insert(Connection con) throws EntidadeException{
        try{
            return new CompraDAO().insert(this, con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public int delete(Connection con) throws EntidadeException{
        try{
            return new CompraDAO().delete(this, con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public Compra select(Connection con) throws EntidadeException{
        try{
            return new CompraDAO().select(this, con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public List<Compra> lista(Connection con) throws EntidadeException{
        try{
            return new CompraDAO().lista(this, con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
}
