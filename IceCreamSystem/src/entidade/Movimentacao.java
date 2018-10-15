package entidade;

import DAO.MovimentacaoDAO;
import exception.DAOException;
import exception.EntidadeException;
import java.sql.Connection;
import java.util.Date;

public class Movimentacao {
    private int codigo;
    private String descricao;
    private Date data;
    private double valor;
    private ContasPagar cp;
    private ContasReceber cr;
    private Caixa caixa;

    public Movimentacao() {
        cp = new ContasPagar();
        caixa = new Caixa();
        cr = new ContasReceber();
    }
    
    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public ContasPagar getCp() {
        return cp;
    }

    public void setCp(ContasPagar cp) {
        this.cp = cp;
    }

    public Caixa getCaixa() {
        return caixa;
    }

    public void setCaixa(Caixa caixa) {
        this.caixa = caixa;
    }

    public ContasReceber getCr() {
        return cr;
    }

    public void setCr(ContasReceber cr) {
        this.cr = cr;
    }
    
    public int insert(Connection con) throws EntidadeException{
        try{
            return new MovimentacaoDAO().insert(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
}
