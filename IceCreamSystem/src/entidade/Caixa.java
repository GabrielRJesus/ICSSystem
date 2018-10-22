package entidade;

import DAO.CaixaDAO;
import exception.DAOException;
import exception.EntidadeException;
import java.sql.Connection;
import java.util.Date;

public class Caixa {
    private Integer codigo;
    private Date abertura;
    private Date fechamento;
    private String periodoA;
    private String periodoB;
    private double troco;
    private double trocoFinal;
    private Funcionario func;
    private String motEntrada;
    private String motSaida;

    public Caixa() {
        func = new Funcionario();
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getAbertura() {
        return abertura;
    }

    public void setAbertura(Date abertura) {
        this.abertura = abertura;
    }

    public Date getFechamento() {
        return fechamento;
    }

    public void setFechamento(Date fechamento) {
        this.fechamento = fechamento;
    }

    public String getPeriodoA() {
        return periodoA;
    }

    public void setPeriodoA(String periodoA) {
        this.periodoA = periodoA;
    }

    public String getPeriodoB() {
        return periodoB;
    }

    public void setPeriodoB(String periodoB) {
        this.periodoB = periodoB;
    }

    public double getTroco() {
        return troco;
    }

    public void setTroco(double troco) {
        this.troco = troco;
    }

    public Funcionario getFunc() {
        return func;
    }

    public void setFunc(Funcionario func) {
        this.func = func;
    }

    public double getTrocoFinal() {
        return trocoFinal;
    }

    public void setTrocoFinal(double trocoFinal) {
        this.trocoFinal = trocoFinal;
    }

    public String getMotEntrada() {
        return motEntrada;
    }

    public void setMotEntrada(String motEntrada) {
        this.motEntrada = motEntrada;
    }

    public String getMotSaida() {
        return motSaida;
    }

    public void setMotSaida(String motSaida) {
        this.motSaida = motSaida;
    }
    
    public int abrirCaixa(Connection con) throws EntidadeException{
        try{
            return new CaixaDAO().abrirCaixa(this, con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public int fecharCaixa(Connection con) throws EntidadeException{
        try{
            return new CaixaDAO().fecharCaixa(this, con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public Caixa retornaAberto(Connection con) throws EntidadeException{
        try{
            return new CaixaDAO().retornaCaixa(this, con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public double valorReceber(Connection con) throws EntidadeException{
         try{
            return new CaixaDAO().valorReceber(this, con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public double valorPagar(Connection con) throws EntidadeException{
         try{
            return new CaixaDAO().valorPagar(this, con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public double ultimoCaixa(Connection con) throws EntidadeException{
        try{
            return new CaixaDAO().ultimoCaixa(this, con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
}
