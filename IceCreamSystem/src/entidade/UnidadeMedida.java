package entidade;

import DAO.UnidadeMedidaDAO;
import exception.DAOException;
import exception.EntidadeException;
import java.sql.Connection;
import java.util.List;

public class UnidadeMedida {
    private Integer codigo;
    private String descricao;
    private String abreviacao;

    public UnidadeMedida() {
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

    public String getAbreviacao() {
        return abreviacao;
    }

    @Override
    public String toString() {
        return abreviacao;
    }
    
    

    public void setAbreviacao(String abreviacao) {
        this.abreviacao = abreviacao;
    }
    
    public int insert(Connection con) throws EntidadeException{
        try{
            return new UnidadeMedidaDAO().insert(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public int update(Connection con) throws EntidadeException{
        try{
            return new UnidadeMedidaDAO().update(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public int delete(Connection con) throws EntidadeException{
        try{
            return new UnidadeMedidaDAO().delete(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public UnidadeMedida select(Connection con) throws EntidadeException{
        try{
            return new UnidadeMedidaDAO().select(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public List<UnidadeMedida> listaUM(Connection con) throws EntidadeException{
        try{
            return new UnidadeMedidaDAO().lista(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public List<String> listaUMString(Connection con) throws EntidadeException{
        try{
            return new UnidadeMedidaDAO().listaString(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
}
