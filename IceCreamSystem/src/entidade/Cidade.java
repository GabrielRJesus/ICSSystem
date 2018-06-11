package entidade;

import DAO.CidadeDAO;
import exception.DAOException;
import exception.EntidadeException;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author gabri
 */
public class Cidade {
    private Integer codigo;
    private String nome;
    private Estado estado;

    public Cidade() {
        this.estado = new Estado();
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    public List<String> listaString(Connection con) throws EntidadeException{
        try{
            return new CidadeDAO().listaString(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }

    @Override
    public String toString() {
        return nome;
    }
    
    public Cidade select(Connection con)throws EntidadeException{
        try{
            return new CidadeDAO().select(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public List<Cidade> lista(Connection con)throws EntidadeException{
        try{
            return new CidadeDAO().listaTodas(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
}
