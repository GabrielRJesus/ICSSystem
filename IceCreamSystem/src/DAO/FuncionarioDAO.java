package DAO;

import entidade.Funcionario;
import entidade.Logradouro;
import exception.DAOException;
import exception.EntidadeException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FuncionarioDAO implements GenericDAO<Funcionario>{
    
    private String select = "select * from funcionario f inner join cliente c on c.cli_codigo = f.cli_codigo";

    @Override
    public int insert(Funcionario obj, Connection con) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Funcionario obj, Connection con) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Funcionario obj, Connection con) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Funcionario select(Funcionario obj, Connection con) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int cont = 0;
        boolean ultimo = false;
        if(con!=null){
            if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0){
                select+=" where f.cli_codigo = ?";
                ultimo = true;
            }
            if(obj!=null && obj.getNome()!=null && !obj.getNome().isEmpty()){
                if(ultimo)
                    select+=" and c.cli_nome like ?";
                else{
                    select+=" where c.cli_nome like ?";
                    ultimo = true;
                }
            }
            if(obj!=null && obj.getCpf()!=null && !obj.getCpf().isEmpty()){
                if(ultimo)
                    select+=" and c.cli_cpf = ?";
                else{
                    select+=" where c.cli_cpf = ?";
                    ultimo = true;
                }
            }
            if(obj!=null && obj.getLogin()!=null && !obj.getLogin().isEmpty()){
                if(ultimo)
                    select+=" and f.fun_login = ?";
                else{
                    select+=" where f.fun_login = ?";
                    ultimo = true;
                }
            }
            if(obj!=null && obj.getSalario()!=0){
                if(ultimo)
                    select+=" and f.fun_salario = ?";
                else{
                    select+=" where f.fun_salario = ?";
                    ultimo = true;
                }
            }
            if(obj!=null && obj.getCargo()!=null && !obj.getCargo().isEmpty()){
                if(ultimo)
                    select+=" and f.fun_cargo = ?";
                else{
                    select+=" where f.fun_cargo = ?";
                    ultimo = true;
                }
            }
            
            try{
                ps = con.prepareStatement(select);
                if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0)
                    ps.setInt(++cont, obj.getCodigo());
                if(obj!=null && obj.getNome()!=null && !obj.getNome().isEmpty())
                    ps.setString(++cont, "%"+obj.getNome()+"%");
                if(obj!=null && obj.getCpf()!=null && !obj.getCpf().isEmpty())
                    ps.setString(++cont, obj.getCpf());
                if(obj!=null && obj.getLogin()!=null && !obj.getLogin().isEmpty())
                    ps.setString(++cont, obj.getLogin());
                if(obj!=null && obj.getSalario()!=0)
                    ps.setDouble(++cont, obj.getSalario());
                if(obj!=null && obj.getCargo()!=null && !obj.getCargo().isEmpty())
                    ps.setString(++cont, obj.getCargo());
                rs = ps.executeQuery();
                
                if(rs.next()){
                    Funcionario f = new Funcionario();
                    Logradouro l = new Logradouro();
                    f.setCodigo(rs.getInt("c.cli_codigo"));
                    f.setNome(rs.getString("c.cli_nome"));
                    f.setCpf(rs.getString("c.cli_cpf"));
                    f.setRg(rs.getString("c.cli_rg"));
                    f.setDtNasc(rs.getDate("c.cli_dtnasc"));
                    f.setSexo(rs.getString("c.cli_sexo").charAt(0));
                    f.setTelefone(rs.getString("c.cli_email"));
                    l.setCodigo(rs.getInt("c.log_codigo"));
                    f.setLogradouro(l.select(con));
                    f.setLogin(rs.getString("f.fun_login"));
                    f.setSenha(rs.getString("f.fun_senha"));
                    f.setDtAdmiss(rs.getDate("f.fun_dtadmin"));
                    f.setDtDemiss(rs.getDate("f.fun_dtdemis"));
                    f.setSalario(rs.getDouble("f.fun_salario"));
                    f.setCargo(rs.getString("f.fun_cargo"));
                    return f;
                }
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            } catch (EntidadeException ex) {
                Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
        return null;
    }
    
}
