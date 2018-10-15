package DAO;

import entidade.TPaagamentoPagar;
import exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TPaagamentoPagarDAO implements GenericDAO<TPaagamentoPagar>{
    
    private String insert = "insert into tppagamento_cpagar(tpp_codigo, con_codigo, tpgc_valor) values(?,?,?)"; 

    
    public int insert(int chave, TPaagamentoPagar obj, Connection con) throws DAOException {
        PreparedStatement ps = null;
        int cont = 0;
        if(con!=null){
            try{
                ps = con.prepareCall(insert);
                ps.setInt(++cont, obj.getTpg().getCodigo());
                ps.setInt(++cont, chave);
                ps.setDouble(++cont, obj.getValor());
                return ps.executeUpdate();
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
    }

    @Override
    public int update(TPaagamentoPagar obj, Connection con) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(TPaagamentoPagar obj, Connection con) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TPaagamentoPagar select(TPaagamentoPagar obj, Connection con) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insert(TPaagamentoPagar obj, Connection con) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
