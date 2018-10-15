package entidade;

import java.util.Date;

public class Venda {
    private Integer codigo;
    private int comanda;
    private double total;
    private Date data;
    private String status;
    private Date entrega;
    private TipoVenda tpv;
    private Cliente cli;
    private Funcionario func;

    public Venda() {
        tpv = new TipoVenda();
        cli = new Cliente();
        func = new Funcionario();
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public int getComanda() {
        return comanda;
    }

    public void setComanda(int comanda) {
        this.comanda = comanda;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getEntrega() {
        return entrega;
    }

    public void setEntrega(Date entrega) {
        this.entrega = entrega;
    }

    public TipoVenda getTpv() {
        return tpv;
    }

    public void setTpv(TipoVenda tpv) {
        this.tpv = tpv;
    }

    public Cliente getCli() {
        return cli;
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
    }

    public Funcionario getFunc() {
        return func;
    }

    public void setFunc(Funcionario func) {
        this.func = func;
    }
}
