package DAO;

import entidade.UnidadeMedida;
import exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnidadeMedidaDAO implements GenericDAO<UnidadeMedida>{
    
    private String insert = "insert into unidade_medida(um_descricao, um_sigla) values(?,?)";
    private String update = "update unidade_medida set um_descricao = ?, um_sigla = ? where um_codigo = ?";
    private String delete = "delete from unidade_medida where um_codigo = ?";
    private String select = "select * from unidade_medida";

    @Override
    public int insert(UnidadeMedida obj, Connection con) throws DAOException {
        PreparedStatement ps = null;
        int cont = 0;
        if(con!=null){
            try{
                ps = con.prepareStatement(insert);
                ps.setString(++cont, obj.getDescricao());
                ps.setString(++cont, obj.getAbreviacao());
                return ps.executeUpdate();
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
    }

    @Override
    public int update(UnidadeMedida obj, Connection con) throws DAOException {
        PreparedStatement ps = null;
        int cont = 0;
        if(con!=null){
            try{
                ps = con.prepareStatement(update);
                ps.setString(++cont, obj.getDescricao());
                ps.setString(++cont, obj.getAbreviacao());
                ps.setInt(++cont, obj.getCodigo());
                return ps.executeUpdate();
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
    }

    @Override
    public int delete(UnidadeMedida obj, Connection con) throws DAOException {
        PreparedStatement ps = null;
        int cont = 0;
        if(con!=null){
            try{
                ps = con.prepareStatement(delete);
                ps.setInt(++cont, obj.getCodigo());
                return ps.executeUpdate();
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
    }

    @Override
    public UnidadeMedida select(UnidadeMedida obj, Connection con) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean ultimo = false;
        int cont = 0;
        if(con!=null){
            
            if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0){
                select+=" where um_codigo = ?";
                ultimo = true;
            }
            if(obj!=null && obj.getDescricao()!=null && !obj.getDescricao().isEmpty()){
                if(ultimo)
                    select+=" and um_descricao like ?";
                else{
                    select+=" where um_descricao like ?";
                    ultimo = true;
                }
            }
            if(obj!=null && obj.getAbreviacao()!=null && !obj.getAbreviacao().isEmpty()){
                if(ultimo)
                    select+=" and um_sigla = ?";
                else
                    select+=" where um_sigla = ?";
            }
            
            try{
                ps = con.prepareStatement(select);
                if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0)
                    ps.setInt(++cont, obj.getCodigo());
                if(obj!=null && obj.getDescricao()!=null && !obj.getDescricao().isEmpty())
                    ps.setString(++cont, "%"+obj.getDescricao()+"%");
                if(obj!=null && obj.getAbreviacao()!=null && !obj.getAbreviacao().isEmpty())
                    ps.setString(++cont, obj.getAbreviacao());
                rs = ps.executeQuery();
                if(rs.next()){
                    UnidadeMedida u = new UnidadeMedida();
                    u.setCodigo(rs.getInt("um_codigo"));
                    u.setDescricao(rs.getString("um_descricao"));
                    u.setAbreviacao(rs.getString("um_sigla"));
                    return u;
                }
                
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
        return null;
    }
    
    public List<UnidadeMedida> lista(UnidadeMedida obj, Connection con) throws DAOException {
        List<UnidadeMedida> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean ultimo = false;
        int cont = 0;
        if(con!=null){
            
            if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0){
                select+=" where um_codigo = ?";
                ultimo = true;
            }
            if(obj!=null && obj.getDescricao()!=null && !obj.getDescricao().isEmpty()){
                if(ultimo)
                    select+=" and um_descricao like ?";
                else{
                    select+=" where um_descricao like ?";
                    ultimo = true;
                }
            }
            if(obj!=null && obj.getAbreviacao()!=null && !obj.getAbreviacao().isEmpty()){
                if(ultimo)
                    select+=" and um_sigla = ?";
                else
                    select+=" where um_sigla = ?";
            }
            
            try{
                ps = con.prepareStatement(select);
                if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0)
                    ps.setInt(++cont, obj.getCodigo());
                if(obj!=null && obj.getDescricao()!=null && !obj.getDescricao().isEmpty())
                    ps.setString(++cont, "%"+obj.getDescricao()+"%");
                if(obj!=null && obj.getAbreviacao()!=null && !obj.getAbreviacao().isEmpty())
                    ps.setString(++cont, obj.getAbreviacao());
                rs = ps.executeQuery();
                while(rs.next()){
                    UnidadeMedida u = new UnidadeMedida();
                    u.setCodigo(rs.getInt("um_codigo"));
                    u.setDescricao(rs.getString("um_descricao"));
                    u.setAbreviacao(rs.getString("um_sigla"));
                    lista.add(u);
                }
                return lista;
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
    }
    
}
