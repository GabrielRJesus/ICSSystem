package entidade;

import DAO.ClienteDAO;
import exception.DAOException;
import exception.EntidadeException;
import java.sql.Connection;
import java.util.Date;

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
   
    public int insert(Connection con) throws EntidadeException{
        try{
            return new ClienteDAO().insert(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }

    
}
