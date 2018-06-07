package entidade;

import DAO.LogradouroDAO;
import exception.DAOException;
import exception.EntidadeException;
import java.sql.Connection;

/**
 *
 * @author gabri
 */
public class Logradouro {
    private Integer codigo;
    private String endereco;
    private String numero;
    private String bairro;
    private String cep;
    private Cidade cidade;

    public Logradouro() {
        this.cidade = new Cidade(); 
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
    
    public int insert(Connection con) throws EntidadeException{
        try{
            return new LogradouroDAO().insert(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public Logradouro select(Connection con) throws EntidadeException{
        try{
            return new LogradouroDAO().select(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
}
