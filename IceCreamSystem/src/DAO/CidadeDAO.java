package DAO;

import entidade.Cidade;
import entidade.Estado;
import exception.DAOException;
import exception.EntidadeException;
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
public class CidadeDAO implements GenericDAO<Cidade>{
    
    private String select = "select * from cidade";

    @Override
    public int insert(Cidade obj, Connection con) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Cidade obj, Connection con) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Cidade obj, Connection con) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cidade select(Cidade obj, Connection con) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int cont = 0;
        boolean ultimo = false;
        
        if(con!=null){
            
            if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0){
                select+=" where cid_codigo = ?";
                ultimo = true;
            }
            
            if(obj!=null && obj.getNome()!=null && !obj.getNome().isEmpty()){
                if(ultimo)
                    select+=" and cid_nome like ?";
                else{
                    select+=" where cid_nome like ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getEstado().getCodigo()!=null && obj.getEstado().getCodigo()!=0){
                if(ultimo)
                    select+=" and est_codig = ?";
                else{
                    select+=" where est_codig = ?";
                }
            }
            
            try{
                ps = con.prepareStatement(select);
                if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0)
                    ps.setInt(++cont, obj.getCodigo());
                if(obj!=null && obj.getNome()!=null && !obj.getNome().isEmpty())
                    ps.setString(++cont, "%"+obj.getNome()+"%");
                if(obj!=null && obj.getEstado().getCodigo()!=null && obj.getEstado().getCodigo()!=0)
                    ps.setInt(++cont, obj.getEstado().getCodigo());
                rs = ps.executeQuery();
                
                if(rs.next()){
                    Cidade c = new Cidade();
                    Estado e = new Estado();
                    c.setCodigo(rs.getInt("cid_codigo"));
                    c.setNome(rs.getString("cid_nome"));
                    e.setCodigo(rs.getInt("est_codig"));
                    c.setEstado(e.select(con));
                    return c;
                }
                
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            } catch (EntidadeException ex) {
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
        return null;
    }
    
    public List<Cidade> listaTodas(Cidade obj, Connection con) throws DAOException {
        List<Cidade> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        if(obj!=null && obj.getEstado().getCodigo()!=null && obj.getEstado().getCodigo()!=0)
            select+=" where est_codig = ?";
        
        if(con!=null){
            try{
                ps = con.prepareStatement(select);
                if(obj!=null && obj.getEstado().getCodigo()!=null && obj.getEstado().getCodigo()!=0)
                    ps.setInt(1, obj.getEstado().getCodigo());
                rs = ps.executeQuery();
                
                while(rs.next()){
                    Cidade c = new Cidade();
                    Estado e = new Estado();
                    c.setCodigo(rs.getInt("cid_codigo"));
                    c.setNome(rs.getString("cid_nome"));
                    e.setCodigo(rs.getInt("est_codig"));
                    c.setEstado(e.select(con));
                    lista.add(c);
                }
                return lista;
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            } catch (EntidadeException ex) {
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
    }
    
    public List<String> listaString(Cidade obj, Connection con) throws DAOException {
        List<String> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        if(obj!=null && obj.getEstado().getCodigo()!=null && obj.getEstado().getCodigo()!=0)
            select+=" where est_codig = ?";
        
        if(con!=null){
            try{
                ps = con.prepareStatement(select);
                if(obj!=null && obj.getEstado().getCodigo()!=null && obj.getEstado().getCodigo()!=0)
                    ps.setInt(1, obj.getEstado().getCodigo());
                rs = ps.executeQuery();
                
                while(rs.next()){
                    Cidade c = new Cidade();
                    Estado e = new Estado();
                    c.setCodigo(rs.getInt("cid_codigo"));
                    c.setNome(rs.getString("cid_nome"));
                    e.setCodigo(rs.getInt("est_codig"));
                    c.setEstado(e.select(con));
                    lista.add(c.toString());
                }
                return lista;
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            } catch (EntidadeException ex) {
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
    }
    
}
