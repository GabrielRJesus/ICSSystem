package entidade;

import DAO.CategoriaProdutoDAO;
import exception.DAOException;
import exception.EntidadeException;
import java.sql.Connection;
import java.util.List;

public class CategoriaProduto {
    private Integer codigo;
    private String descricao;

    public CategoriaProduto() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public int insert(Connection con) throws EntidadeException{
        try{
            return new CategoriaProdutoDAO().insert(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public int update(Connection con) throws EntidadeException{
        try{
            return new CategoriaProdutoDAO().update(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public int delete(Connection con) throws EntidadeException{
        try{
            return new CategoriaProdutoDAO().delete(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public CategoriaProduto select(Connection con) throws EntidadeException{
        try{
            return new CategoriaProdutoDAO().select(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public List<CategoriaProduto> lista(Connection con) throws EntidadeException{
        try{
            return new CategoriaProdutoDAO().lista(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
}
