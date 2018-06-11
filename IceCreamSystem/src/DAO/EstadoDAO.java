package DAO;

import entidade.Estado;
import exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabri
 */
public class EstadoDAO implements GenericDAO<Estado>{
    
    private String select = "select * from estado";

    @Override
    public int insert(Estado obj, Connection con) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Estado obj, Connection con) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Estado obj, Connection con) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Estado select(Estado obj, Connection con) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int cont=0;
        boolean ultimo = false;
        
        if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0){
            select+=" where est_codigo = ?";
            ultimo = true;
        }
        
        if(obj!=null && obj.getNome()!=null && !obj.getNome().isEmpty()){
            if(ultimo)
                select+=" and est_nome like ?";
            else{
                select+=" where est_nome like ?";
                ultimo = true;
            }
        }
        
        if(obj!=null && obj.getSigla()!=null && !obj.getSigla().isEmpty()){
            if(ultimo)
                select+=" and est_sigla = ?";
            else
                select+=" where est_sigla = ?";
        }
        
        if(con!=null){
            try{
                ps = con.prepareStatement(select);
                if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0)
                    ps.setInt(++cont, obj.getCodigo());
                if(obj!=null && obj.getNome()!=null && !obj.getNome().isEmpty())
                    ps.setString(++cont, "%"+obj.getNome()+"%");
                if(obj!=null && obj.getSigla()!=null && !obj.getSigla().isEmpty())
                    ps.setString(++cont, obj.getSigla());
                rs = ps.executeQuery();
                
                if(rs.next()){
                    Estado e = new Estado();
                    e.setCodigo(rs.getInt("est_codigo"));
                    e.setNome(rs.getString("est_nome"));
                    e.setSigla(rs.getString("est_sigla"));
                    return e;
                }
                
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
        return null;
    }
    
    public List<Estado> listaTodos(Estado obj, Connection con) throws DAOException {
        List<Estado> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        if(con!=null){
            try{
                ps = con.prepareStatement(select);
                rs = ps.executeQuery();
                
                while(rs.next()){
                    Estado e = new Estado();
                    e.setCodigo(rs.getInt("est_codigo"));
                    e.setNome(rs.getString("est_nome"));
                    e.setSigla(rs.getString("est_sigla"));
                    lista.add(e);
                }
                return lista;
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
    }
    
    public List<String> listaString(Estado obj, Connection con) throws DAOException {
        List<String> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        if(con!=null){
            try{
                ps = con.prepareStatement(select);
                rs = ps.executeQuery();
                
                while(rs.next()){
                    Estado e = new Estado();
                    e.setCodigo(rs.getInt("est_codigo"));
                    e.setNome(rs.getString("est_nome"));
                    e.setSigla(rs.getString("est_sigla"));
                    lista.add(e.toString());
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
