package entidade;

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
    
    
}
