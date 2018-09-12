package entidade;

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
}
