package DAO;

import entidade.Cliente;
import exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDAO {
    
    private String insert = "insert into cliente(cli_nome, cli_cpf, cli_rg, cli_celular, cli_dtnasc, cli_sexo, cli_telefone, cli_email, log_codigo) values(?,?,?,?,?,?,?,?,?)";
    private String select = "select * from cliente";
    
    public int insert(Cliente obj, Connection con) throws DAOException{
        PreparedStatement ps = null;
        int cont = 0;
        if(con!=null){
            try{
                ps = con.prepareStatement(insert);
                ps.setString(++cont, obj.getNome());
                ps.setString(++cont, obj.getCpf());
                ps.setString(++cont, obj.getRg());
                ps.setString(++cont, obj.getCelular());
                ps.setDate(++cont, new java.sql.Date(obj.getDtNasc().getTime()));
                ps.setString(++cont, obj.getSexo()+"");
                ps.setString(++cont, obj.getTelefone());
                ps.setString(++cont, obj.getEmail());
                ps.setInt(++cont, obj.getLogradouro().getCodigo());
                return ps.executeUpdate();
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
    }
    
}
