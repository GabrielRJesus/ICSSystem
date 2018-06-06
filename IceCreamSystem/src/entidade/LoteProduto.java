package entidade;

import DAO.LoteProdutoDAO;
import exception.DAOException;
import exception.EntidadeException;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

public class LoteProduto {
    private Integer codigo;
    private String descricao;
    private String numeroLote;
    private Date validade;
    private int qtdeCompra;
    private int qtdRemanescente;
    
    private static LoteProduto loteSelecionado;

    public LoteProduto() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNumeroLote() {
        return numeroLote;
    }

    public void setNumeroLote(String numeroLote) {
        this.numeroLote = numeroLote;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public int getQtdeCompra() {
        return qtdeCompra;
    }

    public void setQtdeCompra(int qtdeCompra) {
        this.qtdeCompra = qtdeCompra;
    }

    public int getQtdRemanescente() {
        return qtdRemanescente;
    }

    public void setQtdRemanescente(int qtdRemanescente) {
        this.qtdRemanescente = qtdRemanescente;
    }

    public static LoteProduto getLoteSelecionado() {
        return loteSelecionado;
    }

    public static void setLoteSelecionado(LoteProduto loteSelecionado) {
        LoteProduto.loteSelecionado = loteSelecionado;
    }
    
    public int insert(Connection con) throws EntidadeException{
        try{
            return new LoteProdutoDAO().insert(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public int update(Connection con) throws EntidadeException{
        try{
            return new LoteProdutoDAO().update(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public int delete(Connection con) throws EntidadeException{
        try{
            return new LoteProdutoDAO().delete(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public LoteProduto select(Connection con) throws EntidadeException{
        try{
            return new LoteProdutoDAO().select(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public List<LoteProduto> lista(Date inicio, Date fim, Connection con) throws EntidadeException{
        try{
            return new LoteProdutoDAO().lista(this,inicio,fim,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
}
