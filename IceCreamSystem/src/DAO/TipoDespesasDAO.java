package DAO;

import entidade.TipoDespesas;
import exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoDespesasDAO implements GenericDAO<TipoDespesas>{
    
    private String insert = "insert into tipo_conta(tpc_descricao) values(?)";
    private String update = "update tipo_conta set tpc_descricao = ? where tpc_codigo = ?";
    private String delete = "delete from tipo_conta where tpc_codigo = ?";
    private String select = "select * from tipo_conta";

    @Override
    public int insert(TipoDespesas obj, Connection con) throws DAOException {
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
            throw new DAOException("Erro na conexão!");
        }     
    }

    @Override
    public int update(TipoDespesas obj, Connection con) throws DAOException {
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
            throw new DAOException("Erro na conexão!");
        }
    }

    @Override
    public int delete(TipoDespesas obj, Connection con) throws DAOException {
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
    public TipoDespesas select(TipoDespesas obj, Connection con) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean ultimo = false;
        int cont = 0;
        if(con!=null){
            
            if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0){
                select+=" where tpc_codigo = ?";
                ultimo = true;
            }
            if(obj!=null && obj.getDescricao()!=null && !obj.getDescricao().isEmpty()){
                if(ultimo)
                    select+=" and tpc_descricao like ?";
                else
                    select+=" where tpc_descricao like ?";
            }
            
            try{
                ps = con.prepareStatement(select);
                if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0)
                    ps.setInt(++cont, obj.getCodigo());
                if(obj!=null && obj.getDescricao()!=null && !obj.getDescricao().isEmpty())
                    ps.setString(++cont, obj.getDescricao());
                rs = ps.executeQuery();
                
                if(rs.next()){
                    TipoDespesas tp = new TipoDespesas();
                    tp.setCodigo(rs.getInt("tpc_codigo"));
                    tp.setDescricao(rs.getString("tpc_descricao"));
                    return tp;
                }
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
        return null;
    }
    
    public List<TipoDespesas> lista(TipoDespesas obj, Connection con) throws DAOException {
        List<TipoDespesas> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean ultimo = false;
        int cont = 0;
        if(con!=null){
            
            if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0){
                select+=" where tpc_codigo = ?";
                ultimo = true;
            }
            if(obj!=null && obj.getDescricao()!=null && !obj.getDescricao().isEmpty()){
                if(ultimo)
                    select+=" and tpc_descricao like ?";
                else
                    select+=" where tpc_descricao like ?";
            }
            
            try{
                ps = con.prepareStatement(select);
                if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0)
                    ps.setInt(++cont, obj.getCodigo());
                if(obj!=null && obj.getDescricao()!=null && !obj.getDescricao().isEmpty())
                    ps.setString(++cont, obj.getDescricao());
                rs = ps.executeQuery();
                
                while(rs.next()){
                    TipoDespesas tp = new TipoDespesas();
                    tp.setCodigo(rs.getInt("tpc_codigo"));
                    tp.setDescricao(rs.getString("tpc_descricao"));
                    lista.add(tp);
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
