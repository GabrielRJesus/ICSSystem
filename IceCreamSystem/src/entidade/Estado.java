package entidade;

import DAO.EstadoDAO;
import exception.DAOException;
import exception.EntidadeException;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author gabri
 */
public class Estado {
    private Integer codigo;
    private String nome;
    private String sigla;

    public Estado() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    
    public Estado select(Connection con) throws EntidadeException{
        try{
            return new EstadoDAO().select(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public List<Estado> lista(Connection con) throws EntidadeException{
        try{
            return new EstadoDAO().listaTodos(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public List<String> listaString(Connection con) throws EntidadeException{
        try{
            return new EstadoDAO().listaString(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }

    @Override
    public String toString() {
        return nome;
    }
    
    
    
}
