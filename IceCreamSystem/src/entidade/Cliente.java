package entidade;

import DAO.ClienteDAO;
import exception.DAOException;
import exception.EntidadeException;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author gabri
 */
public class Cliente extends Pessoa{
   private String cpf;
   private String rg;
   private String celular;
   private Date dtNasc;
   private char sexo;
   
   private static Cliente cliSelecionado;

    public Cliente() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Date getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(Date dtNasc) {
        this.dtNasc = dtNasc;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public static Cliente getCliSelecionado() {
        return cliSelecionado;
    }

    public static void setCliSelecionado(Cliente cliSelecionado) {
        Cliente.cliSelecionado = cliSelecionado;
    }
    
   
    public int insert(Connection con) throws EntidadeException{
        try{
            return new ClienteDAO().insert(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public int update(Connection con) throws EntidadeException{
        try{
            return new ClienteDAO().update(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public int delete(Connection con) throws EntidadeException{
        try{
            return new ClienteDAO().delete(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public Cliente select(Connection con) throws EntidadeException{
        try{
            return new ClienteDAO().select(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public List<Cliente> lista(Connection con) throws EntidadeException{
        try{
            return new ClienteDAO().lista(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }

    
}
