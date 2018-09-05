package entidade;

import DAO.ContasPagarDAO;
import exception.DAOException;
import exception.EntidadeException;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

public class ContasPagar {
    private Integer codigo;
    private Date data;
    private double valor;
    private Date dtpgto;
    private double valorpago;
    private Integer parcela;
    private Compra compra;
    private TipoDespesas tpd;
    private TipoPagamento tpp;
    
    public static ContasPagar cpSelecionada;

    public ContasPagar() {
        compra = new Compra();
        tpd = new TipoDespesas();
        tpp = new TipoPagamento();
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

    public Date getDtpgto() {
        return dtpgto;
    }

    public void setDtpgto(Date dtpgto) {
        this.dtpgto = dtpgto;
    }

    public double getValorpago() {
        return valorpago;
    }

    public void setValorpago(double valorpago) {
        this.valorpago = valorpago;
    }

    public Integer getParcela() {
        return parcela;
    }

    public void setParcela(Integer parcela) {
        this.parcela = parcela;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public TipoDespesas getTpd() {
        return tpd;
    }

    public void setTpd(TipoDespesas tpd) {
        this.tpd = tpd;
    }

    public TipoPagamento getTpp() {
        return tpp;
    }

    public void setTpp(TipoPagamento tpp) {
        this.tpp = tpp;
    }

    public static ContasPagar getCpSelecionada() {
        return cpSelecionada;
    }

    public static void setCpSelecionada(ContasPagar cpSelecionada) {
        ContasPagar.cpSelecionada = cpSelecionada;
    }
    
    public int insert(Connection con) throws EntidadeException{
        try{
            return new ContasPagarDAO().insert(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public int update(Connection con) throws EntidadeException{
        try{
            return new ContasPagarDAO().update(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public List<ContasPagar> lista(Connection con) throws EntidadeException{
        try{
            return new ContasPagarDAO().lista(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
}
