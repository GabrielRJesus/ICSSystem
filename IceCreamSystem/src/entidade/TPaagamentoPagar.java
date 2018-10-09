package entidade;

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
    
    
}
