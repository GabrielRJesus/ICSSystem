package DAO;

import entidade.Logradouro;
import exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author gabri
 */
public class LogradouroDAO {
    
    private String insert = "insert into logradouro(log_endereco, log_numero, log_cep, log_bairro, cid_codigo) values(?,?,?,?,?)";
    
    public int insert(Logradouro obj, Connection con) throws DAOException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        int chave = -1;
        int cont = 0;
        if(con!=null){
            try{
                ps = con.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(++cont, obj.getEndereco());
                ps.setString(++cont, obj.getNumero());
                ps.setString(++cont, obj.getCep());
                ps.setString(++cont, obj.getBairro());
                ps.setInt(++cont, obj.getCidade().getCodigo());
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                if (rs != null && rs.next()) {
                     chave = rs.getInt(1);
                }
                return chave;
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
    }
    
}
