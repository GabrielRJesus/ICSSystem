package entidade;

import DAO.MarcaDAO;
import exception.DAOException;
import exception.EntidadeException;
import java.sql.Connection;
import java.util.List;

public class Marca {
    private Integer codigo;
    private String nome;

    public Marca() {
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

    @Override
    public String toString() {
        return nome;
    }
    
    
    
    public int insert(Connection con) throws EntidadeException{
        try{
            return new MarcaDAO().insert(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public int update(Connection con) throws EntidadeException{
        try{
            return new MarcaDAO().update(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public int delete(Connection con) throws EntidadeException{
        try{
            return new MarcaDAO().delete(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public Marca select(Connection con) throws EntidadeException{
        try{
            return new MarcaDAO().select(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public List<Marca> lista(Connection con) throws EntidadeException{
        try{
            return new MarcaDAO().lista(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public List<String> listaString(Connection con) throws EntidadeException{
        try{
            return new MarcaDAO().listaString(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
}
