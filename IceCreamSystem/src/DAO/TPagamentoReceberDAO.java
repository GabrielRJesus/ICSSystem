package DAO;

import entidade.TPagamentoReceber;
import exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TPagamentoReceberDAO{
    
    private String insert = "insert into tppagamento_receber(tpp_codigo, conr_codigo, tpgc_valor) values(?,?,?)";

    public int insert(int chave, TPagamentoReceber obj, Connection con) throws DAOException {
        PreparedStatement ps = null;
        int cont = 0;
        try{
            ps = con.prepareCall(insert);
            ps.setInt(++cont, obj.getTpg().getCodigo());
            ps.setInt(++cont,chave);
            ps.setDouble(++cont, obj.getValor());
            return ps.executeUpdate();
        }catch(SQLException ex){
            throw new DAOException(ex.getMessage());
        }
    }
}
