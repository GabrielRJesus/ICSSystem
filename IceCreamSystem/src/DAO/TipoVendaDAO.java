package DAO;

import entidade.TipoVenda;
import exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoVendaDAO implements GenericDAO<TipoVenda>{
    
    private String insert = "insert into tipo_venda(tpv_descricao) values(?)";
    private String update = "update tipo_venda set tpv_descricao = ? where tpv_codigo = ?";
    private String delete = "delete from tipo_venda where tpv_codigo = ?";
    private String select = "select * from tipo_venda";

    @Override
    public int insert(TipoVenda obj, Connection con) throws DAOException {
        PreparedStatement ps = null;
        int cont = 0;
        if(con!=null){
            try{
                ps = con.prepareStatement(insert);
                ps.setString(++cont, obj.getDescricao());
                return ps.executeUpdate();
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!!");
        }
    }

    @Override
    public int update(TipoVenda obj, Connection con) throws DAOException {
        PreparedStatement ps = null;
        int cont = 0;
        if(con!=null){
            try{
                ps = con.prepareStatement(update);
                ps.setString(++cont, obj.getDescricao());
                ps.setInt(++cont, obj.getCodigo());
                return ps.executeUpdate();
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!!");
        }
    }

    @Override
    public int delete(TipoVenda obj, Connection con) throws DAOException {
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
            throw new DAOException("Erro na conexão!!");
        }
    }

    @Override
    public TipoVenda select(TipoVenda obj, Connection con) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean ultimo = true;
        int cont = 0;
        if(con!=null){
            if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0){
                select+= " where tpv_codigo = ?";
                ultimo = true;
            }
            
            if(obj!=null && obj.getDescricao()!=null && !obj.getDescricao().isEmpty()){
                if(ultimo)
                    select+=" and tpv_descricao like ?";
                else
                    select+=" where tpv_descricao like ?";
            }
            
            try{
                ps = con.prepareStatement(select);
                if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0)
                    ps.setInt(++cont, obj.getCodigo());
                if(obj!=null && obj.getDescricao()!=null && !obj.getDescricao().isEmpty())
                    ps.setString(++cont, "%"+obj.getDescricao()+"%");
                rs = ps.executeQuery();
                
                if(rs.next()){
                    TipoVenda tv = new TipoVenda();
                    tv.setCodigo(rs.getInt("tpv_codigo"));
                    tv.setDescricao(rs.getString("tpv_descricao"));
                    return tv;
                }
                
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!!");
        }
        return null;
    }
    
    public List<TipoVenda> lista(TipoVenda obj, Connection con) throws DAOException {
        List<TipoVenda> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean ultimo = true;
        int cont = 0;
        if(con!=null){
            if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0){
                select+= " where tpv_codigo = ?";
                ultimo = true;
            }
            
            if(obj!=null && obj.getDescricao()!=null && !obj.getDescricao().isEmpty()){
                if(ultimo)
                    select+=" and tpv_descricao like ?";
                else
                    select+=" where tpv_descricao like ?";
            }
            
            try{
                ps = con.prepareStatement(select);
                if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0)
                    ps.setInt(++cont, obj.getCodigo());
                if(obj!=null && obj.getDescricao()!=null && !obj.getDescricao().isEmpty())
                    ps.setString(++cont, "%"+obj.getDescricao()+"%");
                rs = ps.executeQuery();
                
                while(rs.next()){
                    TipoVenda tv = new TipoVenda();
                    tv.setCodigo(rs.getInt("tpv_codigo"));
                    tv.setDescricao(rs.getString("tpv_descricao"));
                    lista.add(tv);
                }
                return lista;
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!!");
        }
    }
    
}
