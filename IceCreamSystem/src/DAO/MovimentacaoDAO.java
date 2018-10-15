package DAO;

import entidade.Movimentacao;
import exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class MovimentacaoDAO implements GenericDAO<Movimentacao>{
    
    private String insert = "insert into movimentacao_caixa(mov_descricao, mov_data, mov_valor, conr_codigo, con_codigo, cai_codigo) values(?,?,?,?,?,?)";

    @Override
    public int insert(Movimentacao obj, Connection con) throws DAOException {
        PreparedStatement ps = null;
        int cont = 0;
        if(con!=null){
            try{
                ps = con.prepareCall(insert);
                ps.setString(++cont, obj.getDescricao());
                ps.setDate(++cont, new java.sql.Date(obj.getData().getTime()));
                ps.setDouble(++cont, obj.getValor());
                if(obj.getCr().getCodigo()!=null && obj.getCr().getCodigo()!=0)
                    ps.setInt(++cont, obj.getCr().getCodigo());
                else
                    ps.setNull(++cont, java.sql.Types.INTEGER);
                if(obj.getCp().getCodigo()!=null && obj.getCp().getCodigo()!=0)
                    ps.setInt(++cont, obj.getCp().getCodigo());
                else{
                    ps.setNull(++cont, java.sql.Types.INTEGER);
                }
                ps.setInt(++cont, obj.getCaixa().getCodigo());
                return ps.executeUpdate();
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
    }

    @Override
    public int update(Movimentacao obj, Connection con) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Movimentacao obj, Connection con) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Movimentacao select(Movimentacao obj, Connection con) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
