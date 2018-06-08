package DAO;

import entidade.Cliente;
import entidade.Logradouro;
import exception.DAOException;
import exception.EntidadeException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDAO implements GenericDAO<Cliente>{
    
    private String insert = "insert into cliente(cli_nome, cli_cpf, cli_rg, cli_celular, cli_dtnasc, cli_sexo, cli_telefone, cli_email, log_codigo) values(?,?,?,?,?,?,?,?,?)";
    private String select = "select * from cliente c inner join funcionario f on c.cli_codigo<>f.cli_codigo";
    private String update = "update cliente set cli_nome = ?, cli_cpf = ?, cli_rg = ?, cli_celular = ?, cli_dtnasc = ?, cli_sexo = ?, cli_telefone = ?, cli_email = ?, log_codigo = ? where cli_codigo = ?";
    private String delete = "delete from cliente where cli_codigo = ?";
    
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

    @Override
    public int update(Cliente obj, Connection con) throws DAOException {
        PreparedStatement ps = null;
        int cont = 0;
        if(con!=null){
            try{
                ps = con.prepareStatement(update);
                ps.setString(++cont, obj.getNome());
                ps.setString(++cont, obj.getCpf());
                ps.setString(++cont, obj.getRg());
                ps.setString(++cont, obj.getCelular());
                ps.setDate(++cont, new java.sql.Date(obj.getDtNasc().getTime()));
                ps.setString(++cont, obj.getSexo()+"");
                ps.setString(++cont, obj.getTelefone());
                ps.setString(++cont, obj.getEmail());
                ps.setInt(++cont, obj.getLogradouro().getCodigo());
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
    public int delete(Cliente obj, Connection con) throws DAOException {
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
    public Cliente select(Cliente obj, Connection con) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int cont = 0;
        boolean ultimo = false;
        if(con!=null){
            if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0){
                select+=" where cli_codigo = ?";
                ultimo = true;
            }
            
            if(obj!=null && obj.getNome()!=null && !obj.getNome().isEmpty()){
                if(ultimo)
                    select+=" and cli_nome like ?";
                else{
                    select +=" where cli_nome like ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getCpf()!=null && !obj.getCpf().isEmpty()){
                if(ultimo)
                    select+=" and cli_cpf = ?";
                else{
                    select +=" where cli_cpf = ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getRg()!=null && !obj.getRg().isEmpty()){
                if(ultimo)
                    select+=" and cli_rg = ?";
                else{
                    select +=" where cli_rg = ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getCelular()!=null && !obj.getCelular().isEmpty()){
                if(ultimo)
                    select+=" and cli_celular = ?";
                else{
                    select +=" where cli_celular = ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getDtNasc()!=null){
                if(ultimo)
                    select+=" and cli_dtnasc = ?";
                else{
                    select +=" where cli_dtnasc = ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getSexo()!=' '){
                if(ultimo)
                    select+=" and cli_sexo = ?";
                else{
                    select +=" where cli_sexo = ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getTelefone()!=null && !obj.getTelefone().isEmpty()){
                if(ultimo)
                    select+=" and cli_telefone = ?";
                else{
                    select +=" where cli_telefone = ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getEmail()!=null && !obj.getEmail().isEmpty()){
                if(ultimo)
                    select+=" and cli_email = ?";
                else{
                    select +=" where cli_email = ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getLogradouro().getCodigo()!=null && obj.getLogradouro().getCodigo()!=0){
                if(ultimo)
                    select+=" and cli_email = ?";
                else{
                    select +=" where cli_email = ?";
                    ultimo = true;
                }
            }
            try{
                ps = con.prepareStatement(select);
                if(obj!=null && obj.getCodigo()!=null & obj.getCodigo()!=0)
                    ps.setInt(++cont, obj.getCodigo());
                if(obj!=null && obj.getNome()!=null & !obj.getNome().isEmpty())
                    ps.setString(++cont, obj.getNome());
                if(obj!=null && obj.getCpf()!=null & !obj.getCpf().isEmpty())
                    ps.setString(++cont, obj.getCpf());
                if(obj!=null && obj.getRg()!=null & !obj.getRg().isEmpty())
                    ps.setString(++cont, obj.getRg());
                if(obj!=null && obj.getCelular()!=null & !obj.getCelular().isEmpty())
                    ps.setString(++cont, obj.getCelular());
                if(obj!=null && obj.getDtNasc()!=null)
                    ps.setDate(++cont, new java.sql.Date(obj.getDtNasc().getTime()));
                if(obj!=null && obj.getSexo()!=' ')
                    ps.setString(++cont, obj.getSexo()+"");
                if(obj!=null && obj.getTelefone()!=null & !obj.getTelefone().isEmpty())
                    ps.setString(++cont, obj.getTelefone());
                if(obj!=null && obj.getEmail()!=null & !obj.getEmail().isEmpty())
                    ps.setString(++cont, obj.getEmail());
                if(obj!=null && obj.getLogradouro().getCodigo()!=null && obj.getLogradouro().getCodigo()!=0)
                    ps.setInt(++cont, obj.getLogradouro().getCodigo());
                rs = ps.executeQuery();
                if(rs.next()){
                    Cliente c = new Cliente();
                    Logradouro log = new Logradouro();
                    c.setCodigo(rs.getInt("cli_codigo"));
                    c.setNome(rs.getString("cli_nome"));
                    c.setCpf(rs.getString("cli_cpf"));
                    c.setRg(rs.getString("cli_rg"));
                    c.setTelefone(rs.getString("cli_telefone"));
                    c.setCelular(rs.getString("cli_celular"));
                    c.setDtNasc(rs.getDate("cli_dtnasc"));
                    c.setSexo(rs.getString("cli_sexo").charAt(0));
                    c.setEmail(rs.getString("cli_email"));
                    log.setCodigo(rs.getInt("log_codigo"));
                    c.setLogradouro(log.select(con));
                    return c;
                }
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            } catch (EntidadeException ex) {
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
        return null;
    }
    
    public List<Cliente> lista(Cliente obj, Connection con) throws DAOException {
        List<Cliente> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int cont = 0;
        boolean ultimo = false;
        if(con!=null){
            if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0){
                select+=" where cli_codigo = ?";
                ultimo = true;
            }
            
            if(obj!=null && obj.getNome()!=null && !obj.getNome().isEmpty()){
                if(ultimo)
                    select+=" and cli_nome like ?";
                else{
                    select +=" where cli_nome like ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getCpf()!=null && !obj.getCpf().isEmpty()){
                if(ultimo)
                    select+=" and cli_cpf = ?";
                else{
                    select +=" where cli_cpf = ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getRg()!=null && !obj.getRg().isEmpty()){
                if(ultimo)
                    select+=" and cli_rg = ?";
                else{
                    select +=" where cli_rg = ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getCelular()!=null && !obj.getCelular().isEmpty()){
                if(ultimo)
                    select+=" and cli_celular = ?";
                else{
                    select +=" where cli_celular = ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getDtNasc()!=null){
                if(ultimo)
                    select+=" and cli_dtnasc = ?";
                else{
                    select +=" where cli_dtnasc = ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getTelefone()!=null && !obj.getTelefone().isEmpty()){
                if(ultimo)
                    select+=" and cli_telefone = ?";
                else{
                    select +=" where cli_telefone = ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getEmail()!=null && !obj.getEmail().isEmpty()){
                if(ultimo)
                    select+=" and cli_email = ?";
                else{
                    select +=" where cli_email = ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getLogradouro().getCodigo()!=null && obj.getLogradouro().getCodigo()!=0){
                if(ultimo)
                    select+=" and cli_email = ?";
                else{
                    select +=" where cli_email = ?";
                    ultimo = true;
                }
            }
            try{
                ps = con.prepareStatement(select);
                if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0)
                    ps.setInt(++cont, obj.getCodigo());
                if(obj!=null && obj.getNome()!=null && !obj.getNome().isEmpty())
                    ps.setString(++cont, obj.getNome());
                if(obj!=null && obj.getCpf()!=null && !obj.getCpf().isEmpty())
                    ps.setString(++cont, obj.getCpf());
                if(obj!=null && obj.getRg()!=null && !obj.getRg().isEmpty())
                    ps.setString(++cont, obj.getRg());
                if(obj!=null && obj.getCelular()!=null && !obj.getCelular().isEmpty())
                    ps.setString(++cont, obj.getCelular());
                if(obj!=null && obj.getDtNasc()!=null)
                    ps.setDate(++cont, new java.sql.Date(obj.getDtNasc().getTime()));
                if(obj!=null && obj.getTelefone()!=null && !obj.getTelefone().isEmpty())
                    ps.setString(++cont, obj.getTelefone());
                if(obj!=null && obj.getEmail()!=null && !obj.getEmail().isEmpty())
                    ps.setString(++cont, obj.getEmail());
                if(obj!=null && obj.getLogradouro().getCodigo()!=null && obj.getLogradouro().getCodigo()!=0)
                    ps.setInt(++cont, obj.getLogradouro().getCodigo());
                rs = ps.executeQuery();
                while(rs.next()){
                    Cliente c = new Cliente();
                    Logradouro log = new Logradouro();
                    c.setCodigo(rs.getInt("cli_codigo"));
                    c.setNome(rs.getString("cli_nome"));
                    c.setCpf(rs.getString("cli_cpf"));
                    c.setRg(rs.getString("cli_rg"));
                    c.setTelefone(rs.getString("cli_telefone"));
                    c.setCelular(rs.getString("cli_celular"));
                    c.setDtNasc(rs.getDate("cli_dtnasc"));
                    c.setSexo(rs.getString("cli_sexo").charAt(0));
                    c.setEmail(rs.getString("cli_email"));
                    log.setCodigo(rs.getInt("log_codigo"));
                    c.setLogradouro(log.select(con));
                    lista.add(c);
                }
                return lista;
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            } catch (EntidadeException ex) {
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
    }
    
}
