package DAO;

import entidade.Empresa;
import exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpresaDAO implements GenericDAO<Empresa>{
    
    private String insert = "insert into empresa(emp_nomefantasia, emp_razaosocial, emp_ie, emp_cnpj, emp_telefone, emp_logo, emp_dtfundacao) values(?,?,?,?,?,?,?)";
    private String update = "update empresa set emp_nomefantasia = ?, emp_razaosocial = ?, emp_ie = ?, emp_cnpj = ?, emp_telefone = ?, emp_logo = ?, emp_dtfundacao = ? where emp_codigo = ?";
    private String select = "select * from empresa";

    @Override
    public int insert(Empresa obj, Connection con) throws DAOException {
        PreparedStatement ps = null;
        int cont = 0;
        if(con!=null){
            try{
                ps = con.prepareStatement(insert);
                ps.setString(++cont, obj.getNome());
                ps.setString(++cont, obj.getRazaoSocial());
                ps.setString(++cont, obj.getIe());
                ps.setString(++cont, obj.getCnpj());
                ps.setString(++cont, obj.getTelefone());
                ps.setNull(++cont, java.sql.Types.BLOB);
                ps.setDate(++cont, new java.sql.Date(obj.getDataFundação().getTime()));
                return ps.executeUpdate();
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }  
        }else{
            throw new DAOException("Erro na conexão!");
        }
    }

    @Override
    public int update(Empresa obj, Connection con) throws DAOException {
        PreparedStatement ps = null;
        int cont = 0;
        if(con!=null){
            try{
                ps = con.prepareStatement(update);
                ps.setString(++cont, obj.getNome());
                ps.setString(++cont, obj.getRazaoSocial());
                ps.setString(++cont, obj.getIe());
                ps.setString(++cont, obj.getCnpj());
                ps.setString(++cont, obj.getTelefone());
                ps.setNull(++cont, java.sql.Types.BLOB);
                ps.setDate(++cont, new java.sql.Date(obj.getDataFundação().getTime()));
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
    public int delete(Empresa obj, Connection con) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Empresa select(Empresa obj, Connection con) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int cont = 0;
        if(con!=null){
            try{
                ps = con.prepareStatement(select);
                rs = ps.executeQuery();
                
                if(rs.next()){
                    Empresa e = new Empresa();
                    e.setCodigo(rs.getInt("emp_codigo"));
                    e.setNome(rs.getString("emp_nomefantasia"));
                    e.setRazaoSocial(rs.getString("emp_razaosocial"));
                    e.setIe(rs.getString("emp_ie"));
                    e.setCnpj(rs.getString("emp_cnpj"));
                    e.setTelefone(rs.getString("emp_telefone"));
                    e.setLogo(rs.getBlob("emp_logo"));
                    e.setDataFundação(rs.getDate("emp_dtfundacao"));
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
    
}
