package DAO;

import entidade.TipoPagamento;
import exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TipoPagamentoDAO implements GenericDAO<TipoPagamento>{
    
    private String insert = "insert into tipo_pagamento(tpp_descricao) values(?)";
    private String update = "update tipo_pagamento set tpp_descricao = ? where tpp_codigo = ?";
    private String delete = "delete from tipo_pagamento where tpp_codigo = ?";
    private String select = "select * from tipo_pagamento";

    @Override
    public int insert(TipoPagamento obj, Connection con) throws DAOException {
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
    public int update(TipoPagamento obj, Connection con) throws DAOException {
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
    public int delete(TipoPagamento obj, Connection con) throws DAOException {
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
    public TipoPagamento select(TipoPagamento obj, Connection con) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean ultimo = false;
        int cont = 0;
        if(con!=null){
            
            if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0){
                select+=" where tpp_codigo = ?";
                ultimo = true;
            }
            if(obj!=null && obj.getDescricao()!=null && !obj.getDescricao().isEmpty()){
                if(ultimo)
                    select+=" and tpp_descricao like ?";
                else
                    select+=" where tpp_descricao like ?";
            }
            
            try{
                ps = con.prepareStatement(select);
                if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0)
                    ps.setInt(++cont, obj.getCodigo());
                if(obj!=null && obj.getDescricao()!=null && !obj.getDescricao().isEmpty())
                    ps.setString(++cont, obj.getDescricao());
                rs = ps.executeQuery();
                
                if(rs.next()){
                    TipoPagamento tp = new TipoPagamento();
                    tp.setCodigo(rs.getInt("tpp_codigo"));
                    tp.setDescricao(rs.getString("tpp_descricao"));
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
    
    public List<TipoPagamento> lista(TipoPagamento obj, Connection con) throws DAOException {
        List<TipoPagamento> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean ultimo = false;
        int cont = 0;
        if(con!=null){
            
            if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0){
                select+=" where tpp_codigo = ?";
                ultimo = true;
            }
            if(obj!=null && obj.getDescricao()!=null && !obj.getDescricao().isEmpty()){
                if(ultimo)
                    select+=" and tpp_descricao like ?";
                else
                    select+=" where tpp_descricao like ?";
            }
            
            try{
                ps = con.prepareStatement(select);
                if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0)
                    ps.setInt(++cont, obj.getCodigo());
                if(obj!=null && obj.getDescricao()!=null && !obj.getDescricao().isEmpty())
                    ps.setString(++cont, obj.getDescricao());
                rs = ps.executeQuery();
                
                while(rs.next()){
                    TipoPagamento tp = new TipoPagamento();
                    tp.setCodigo(rs.getInt("tpp_codigo"));
                    tp.setDescricao(rs.getString("tpp_descricao"));
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
