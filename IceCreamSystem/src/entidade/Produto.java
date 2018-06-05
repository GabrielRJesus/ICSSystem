package entidade;

import exception.DAOException;
import exception.EntidadeException;
import java.sql.Connection;
import java.util.List;

public class Produto {
    private Integer codigo;
    private CategoriaProduto cprod;
    private LoteProduto lprod;
    private UnidadeMedida unimed;
    private Marca marca;
    private double precoBase;
    private double margemLucro;
    private double preco;
    private int qtdeMin;
    private int qtdeEstoque;

    public Produto() {
        cprod = new CategoriaProduto();
        lprod = new LoteProduto();
        unimed = new UnidadeMedida();
        marca = new Marca();
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public CategoriaProduto getCprod() {
        return cprod;
    }

    public void setCprod(CategoriaProduto cprod) {
        this.cprod = cprod;
    }

    public LoteProduto getLprod() {
        return lprod;
    }

    public void setLprod(LoteProduto lprod) {
        this.lprod = lprod;
    }

    public UnidadeMedida getUnimed() {
        return unimed;
    }

    public void setUnimed(UnidadeMedida unimed) {
        this.unimed = unimed;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public double getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(double precoBase) {
        this.precoBase = precoBase;
    }

    public double getMargemLucro() {
        return margemLucro;
    }

    public void setMargemLucro(double margemLucro) {
        this.margemLucro = margemLucro;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQtdeMin() {
        return qtdeMin;
    }

    public void setQtdeMin(int qtdeMin) {
        this.qtdeMin = qtdeMin;
    }

    public int getQtdeEstoque() {
        return qtdeEstoque;
    }

    public void setQtdeEstoque(int qtdeEstoque) {
        this.qtdeEstoque = qtdeEstoque;
    }
    
    public int insert(Connection con) throws EntidadeException{
        try{
            return new ProdutoDAO().insert(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public int update(Connection con) throws EntidadeException{
        try{
            return new ProdutoDAO().update(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public int delete(Connection con) throws EntidadeException{
        try{
            return new ProdutoDAO().delete(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public TipoDespesas select(Connection con) throws EntidadeException{
        try{
            return new ProdutoDAO().select(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public List<TipoDespesas> lista(Connection con) throws EntidadeException{
        try{
            return new ProdutoDAO().lista(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }

    
}
