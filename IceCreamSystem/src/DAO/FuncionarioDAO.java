package DAO;

import entidade.Funcionario;
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


public class FuncionarioDAO implements GenericDAO<Funcionario>{
    
    private String select = "select * from funcionario f inner join cliente c on c.cli_codigo = f.cli_codigo";
    private String insert = "insert into cliente(cli_nome, cli_cpf, cli_rg, cli_celular, cli_dtnasc, cli_sexo, cli_telefone, cli_email, log_codigo) values(?,?,?,?,?,?,?,?,?)";
    private String insertf = "insert into funcionario(cli_codigo, fun_login, fun_senha, fun_dtadmis, fun_dtdemis, fun_nivel, fun_salario, fun_cargo) values(?,?,?,?,?,?,?,?)";
    private String update = "update cliente set cli_nome = ?, cli_cpf = ?, cli_rg = ?, cli_celular = ?, cli_dtnasc = ?, cli_sexo = ?, cli_telefone = ?, log_codigo = ? where cli_codigo = ?";
    private String updatef = "update funcionario set fun_login = ?, fun_senha = ?, fun_dtadmis = ?, fun_dtdemis = ?, fun_nivel = ?, fun_salario = ?, fun_cargo = ? where cli_codigo = ?";
    private String selectsf = " select * from funcionario f inner join cliente c on c.cli_codigo = f.cli_codigo";
    
    
    @Override
    public int insert(Funcionario obj, Connection con) throws DAOException {
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        int cont = 0;
        int chave = -1;
        if(con!=null){
            try{
                ps = con.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(++cont, obj.getNome());
                ps.setString(++cont, obj.getCpf());
                ps.setString(++cont, obj.getRg());
                ps.setString(++cont, obj.getCelular());
                if(obj.getDtNasc()!=null)
                    ps.setDate(++cont, new java.sql.Date(obj.getDtNasc().getTime()));
                else
                    ps.setNull(++cont, java.sql.Types.DATE);
                ps.setString(++cont, obj.getSexo()+"");
                ps.setString(++cont, obj.getTelefone());
                ps.setString(++cont, obj.getEmail());
                ps.setInt(++cont, obj.getLogradouro().getCodigo());
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                if (rs != null && rs.next()) {
                    chave = rs.getInt(1);
                }
                cont = 0;
                ps2 = con.prepareStatement(insertf);
                ps2.setInt(++cont, chave);
                ps2.setString(++cont, obj.getLogin());
                ps2.setString(++cont, obj.getSenha());
                ps2.setDate(++cont, new java.sql.Date(obj.getDtAdmiss().getTime()));
                if(obj.getDtDemiss()!=null)
                    ps2.setDate(++cont, new java.sql.Date(obj.getDtDemiss().getTime()));
                else
                    ps2.setNull(++cont, java.sql.Types.DATE);
                ps2.setInt(++cont, obj.getNivel());
                ps2.setDouble(++cont, obj.getSalario());
                ps2.setString(++cont, obj.getCargo());
                return ps2.executeUpdate();
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
    }

    @Override
    public int update(Funcionario obj, Connection con) throws DAOException {
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        int cont = 0;
        int chave = -1;
        if(con!=null){
            try{
                ps = con.prepareStatement(update);
                ps.setString(++cont, obj.getNome());
                ps.setString(++cont, obj.getCpf());
                ps.setString(++cont, obj.getRg());
                ps.setString(++cont, obj.getCelular());
                if(obj.getDtNasc()!=null)
                    ps.setDate(++cont, new java.sql.Date(obj.getDtNasc().getTime()));
                else
                    ps.setNull(++cont, java.sql.Types.DATE);
                ps.setString(++cont, obj.getSexo()+"");
                ps.setString(++cont, obj.getTelefone());
                ps.setString(++cont, obj.getEmail());
                ps.setInt(++cont, obj.getLogradouro().getCodigo());
                ps.setInt(++cont, obj.getCodigo());
                ps.executeUpdate();
                cont = 0;
                ps2 = con.prepareStatement(updatef);
                ps2.setString(++cont, obj.getLogin());
                ps2.setString(++cont, obj.getSenha());
                ps2.setDate(++cont, new java.sql.Date(obj.getDtAdmiss().getTime()));
                if(obj.getDtDemiss()!=null)
                    ps2.setDate(++cont, new java.sql.Date(obj.getDtDemiss().getTime()));
                else
                    ps2.setNull(++cont, java.sql.Types.DATE);
                ps2.setInt(++cont, obj.getNivel());
                ps2.setDouble(++cont, obj.getSalario());
                ps2.setString(++cont, obj.getCargo());
                ps2.setInt(++cont, obj.getCodigo());
                return ps2.executeUpdate();
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
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
            if(obj!=null && obj.getSenha()!=null && !obj.getSenha().isEmpty()){
                if(ultimo)
                    select+=" and f.fun_senha = ?";
                else{
                    select+=" where f.fun_senha = ?";
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
                if(obj!=null && obj.getSenha()!=null && !obj.getSenha().isEmpty())
                    ps.setString(++cont, obj.getSenha());
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
                    f.setTelefone(rs.getString("c.cli_telefone"));
                    f.setEmail(rs.getString("c.cli_email"));
                    l.setCodigo(rs.getInt("c.log_codigo"));
                    f.setLogradouro(l.select(con));
                    f.setLogin(rs.getString("f.fun_login"));
                    f.setSenha(rs.getString("f.fun_senha"));
                    f.setDtAdmiss(rs.getDate("f.fun_dtadmis"));
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
    
    public List<Funcionario> lista(Funcionario obj, Connection con) throws DAOException {
        List<Funcionario> lista = new ArrayList<>();
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
                
                while(rs.next()){
                    Funcionario f = new Funcionario();
                    Logradouro l = new Logradouro();
                    f.setCodigo(rs.getInt("c.cli_codigo"));
                    f.setNome(rs.getString("c.cli_nome"));
                    f.setCpf(rs.getString("c.cli_cpf"));
                    f.setRg(rs.getString("c.cli_rg"));
                    f.setDtNasc(rs.getDate("c.cli_dtnasc"));
                    f.setSexo(rs.getString("c.cli_sexo").charAt(0));
                    f.setTelefone(rs.getString("c.cli_telefone"));
                    f.setEmail(rs.getString("c.cli_email"));
                    l.setCodigo(rs.getInt("c.log_codigo"));
                    f.setLogradouro(l.select(con));
                    f.setLogin(rs.getString("f.fun_login"));
                    f.setSenha(rs.getString("f.fun_senha"));
                    f.setDtAdmiss(rs.getDate("f.fun_dtadmis"));
                    f.setDtDemiss(rs.getDate("f.fun_dtdemis"));
                    f.setSalario(rs.getDouble("f.fun_salario"));
                    f.setCargo(rs.getString("f.fun_cargo"));
                    lista.add(f);
                }
                return lista;
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
    
    
    public List<Funcionario> listaFuncionarios(Funcionario obj, Connection con) throws DAOException {
        List<Funcionario> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int cont = 0;
        boolean ultimo = false;
        if(con!=null){
            
            
            try{
                ps = con.prepareStatement(selectsf);
                rs = ps.executeQuery();
                while(rs.next()){
                    Funcionario f = new Funcionario();
                    Logradouro l = new Logradouro();
                    f.setCodigo(rs.getInt("c.cli_codigo"));
                    f.setNome(rs.getString("c.cli_nome"));
                    f.setCpf(rs.getString("c.cli_cpf"));
                    f.setRg(rs.getString("c.cli_rg"));
                    f.setDtNasc(rs.getDate("c.cli_dtnasc"));
                    f.setSexo(rs.getString("c.cli_sexo").charAt(0));
                    f.setTelefone(rs.getString("c.cli_telefone"));
                    f.setEmail(rs.getString("c.cli_email"));
                    l.setCodigo(rs.getInt("c.log_codigo"));
                    f.setLogradouro(l.select(con));
                    f.setLogin(rs.getString("f.fun_login"));
                    f.setSenha(rs.getString("f.fun_senha"));
                    f.setDtAdmiss(rs.getDate("f.fun_dtadmis"));
                    f.setDtDemiss(rs.getDate("f.fun_dtdemis"));
                    f.setSalario(rs.getDouble("f.fun_salario"));
                    f.setCargo(rs.getString("f.fun_cargo"));
                    lista.add(f);
                }
                return lista;
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
