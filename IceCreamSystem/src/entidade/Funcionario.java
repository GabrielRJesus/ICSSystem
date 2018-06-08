package entidade;

import DAO.FuncionarioDAO;
import exception.DAOException;
import exception.EntidadeException;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author gabri
 */
public class Funcionario extends Cliente{
    private String login;
    private String senha;
    private Date dtAdmiss;
    private Date dtDemiss;
    private int nivel;
    private String cargo;
    private double salario;
    
    private static Funcionario funcLogado;
    private static Funcionario funcSelecionado;

    public Funcionario() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDtAdmiss() {
        return dtAdmiss;
    }

    public void setDtAdmiss(Date dtAdmiss) {
        this.dtAdmiss = dtAdmiss;
    }

    public Date getDtDemiss() {
        return dtDemiss;
    }

    public void setDtDemiss(Date dtDemiss) {
        this.dtDemiss = dtDemiss;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public static Funcionario getFuncLogado() {
        return funcLogado;
    }

    public static void setFuncLogado(Funcionario funcLogado) {
        Funcionario.funcLogado = funcLogado;
    }

    public static Funcionario getFuncSelecionado() {
        return funcSelecionado;
    }

    public static void setFuncSelecionado(Funcionario funcSelecionado) {
        Funcionario.funcSelecionado = funcSelecionado;
    }
    
    public Funcionario select(Connection con) throws EntidadeException{
        try{
            return new FuncionarioDAO().select(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public int insert(Connection con) throws EntidadeException{
        try{
            return new FuncionarioDAO().insert(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public List<Funcionario> lista(Connection con) throws EntidadeException{
        try{
            return new FuncionarioDAO().lista(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
}
