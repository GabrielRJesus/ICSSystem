package DAO;

import entidade.Cidade;
import entidade.Logradouro;
import exception.DAOException;
import exception.EntidadeException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;

/**
 *
 * @author gabri
 */
public class LogradouroDAO implements GenericDAO<Logradouro>{
    
    private String insert = "insert into logradouro(log_endereco, log_numero, log_cep, log_bairro, cid_codigo) values(?,?,?,?,?)";
    private String select = "select * from logradouro";
    
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

    @Override
    public int update(Logradouro obj, Connection con) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Logradouro obj, Connection con) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Logradouro select(Logradouro obj, Connection con) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int cont = 0;
        boolean ultimo = false;
        if(con!=null){
            if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0){
                select+=" where log_codigo = ?";
                ultimo = true;        
            }
            
            if(obj!=null && obj.getEndereco()!=null && !obj.getEndereco().isEmpty()){
                   if(ultimo)
                       select+=" and log_endereco like ?";
                   else{
                       select+=" where log_endereco like ?";
                       ultimo = true;
                   }
            }
            
            if(obj!=null && obj.getNumero()!=null && !obj.getNumero().isEmpty()){
                   if(ultimo)
                       select+=" and log_numero = ?";
                   else
                       select+=" where log_numero = ?";
            }
            try{
                ps = con.prepareStatement(select);
                if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0)
                    ps.setInt(++cont, obj.getCodigo());
                if(obj!=null && obj.getEndereco()!=null && !obj.getEndereco().isEmpty())
                    ps.setString(++cont, "%"+obj.getEndereco()+"%");
                if(obj!=null && obj.getNumero()!=null && !obj.getNumero().isEmpty())
                    ps.setString(++cont, obj.getNumero());
                rs = ps.executeQuery();
                if(rs.next()){
                    Logradouro log = new Logradouro();
                    Cidade c = new Cidade();
                    log.setCodigo(rs.getInt("log_codigo"));
                    log.setEndereco(rs.getString("log_endereco"));
                    log.setNumero(rs.getString("log_numero"));
                    log.setCep(rs.getString("log_cep"));
                    log.setBairro(rs.getString("log_bairro"));
                    c.setCodigo(rs.getInt("cid_codigo"));
                    log.setCidade(c.select(con));
                    return log;
                }
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            } catch (EntidadeException ex) {
                Logger.getLogger(LogradouroDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
        return null;
    }
    
}
